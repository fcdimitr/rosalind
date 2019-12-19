package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

// CSV
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Secured( ['ROLE_ADMIN'] )
class ExperimentController {

    // name for admin panel
    static final String myName = "Manage experiment data"

    
    ExperimentService experimentService
    // BiomarkerService biomarkerService
    // SampleDataService sampleDataService
    SampleBiomarkerService sampleBiomarkerService
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond experimentService.list(params), model:[experimentCount: experimentService.count()]
    }

    def show(Long id) {
        respond experimentService.get(id)
    }

    def create() {
        respond new Experiment(params)
    }

    def downloadDataFile(Long id) {
        Experiment experiment = experimentService.get(id)
        if (!experiment || experiment.primaryData == null) {
            notFound()
            return
        }

        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment;filename=\"primary-data.${experiment.primaryExt}\"")
        response.outputStream << experiment.primaryData
    }

    def downloadEstimationFile(Long id) {
        Experiment experiment = experimentService.get(id)
        if (!experiment || experiment.secondaryData == null) {
            notFound()
            return
        }
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment;filename=\"secondary-data.${experiment.secondaryExt}\"")
        response.outputStream << experiment.secondaryData
    }

    def save(Experiment experiment) {

        if (experiment == null) {
            notFound()
            return
        }
        Boolean overwrite = params.overwrite

        if (!experiment.validate()){
            respond experiment.errors, view:'create'
            return
        }

        def matcherPrimary = (params.primaryData.originalFilename =~ /.*\.(.*)$/)
        def matcherSecondary = (params.secondaryData.originalFilename =~ /.*\.(.*)$/)

        if (matcherPrimary.matches()){
            experiment.primaryExt = matcherPrimary[0][1]
        }

        if (matcherSecondary.matches()){
            experiment.secondaryExt = matcherSecondary[0][1]
        } 
        
        // ~~~~~~~~~~~~~~~~~~~~ PARSE CSV FOR DATA MATRIX UPDATE
        
        CSVParser csvParser = new CSVParser(new InputStreamReader(request.getFile('finalData').inputStream),
                                            CSVFormat.DEFAULT
                                            .withFirstRecordAsHeader()
                                            .withIgnoreSurroundingSpaces() );

        def biomarkerNames = csvParser.getHeaderNames()

        // drop Sample ID from first column and get label in different var
        def labelSampleID = biomarkerNames.take(1)
        biomarkerNames = biomarkerNames.drop(1)

        // get all biomarkers
        Method method = experiment.method
        List<Biomarker> biomarkers = Biomarker.findAllByMethodAndNameInList( method, biomarkerNames )

        if (!biomarkers.name.equals(biomarkerNames)){
            experiment.errors.reject('assoc', "Could not find biomarkers ${biomarkerNames - biomarkers.name} for method ${method}")
            experiment.id = null
            experiment.method = null
            respond experiment.errors, view:'create'
            return
        }

        // keep list of new biomarkers
        def listSampleBiomarker = []
        
        for (CSVRecord csvRecord in csvParser) {

            def sampleDataID = csvRecord.get(labelSampleID).trim()
            SampleData sample = SampleData.findByLabID(sampleDataID)

            if (!sample) {
                experiment.errors.reject('assoc', "Could not find sample with lab ID: ${sampleDataID}")
                experiment.id = null
                experiment.method = null
                respond experiment.errors, view:'create'
                return
            }
            
            for (def biomarker in biomarkers) {

                SampleBiomarker sb

                def value = csvRecord.get(biomarker.name)
                
                if (overwrite) {        // ..... override previous if existing
                    sb = SampleBiomarker.findOrCreateByBiomarkerAndSampleData(biomarker,sample)
                    sb.value = Float.parseFloat( value )
                    sb.experiment = experiment
                } else {                // ..... create new, no matter what
                    sb = new SampleBiomarker(
                        experiment: experiment,
                        biomarker: biomarker,
                        sampleData: sample,
                        value: value )
                }

                if (sb.validate()){
                    listSampleBiomarker.add( sb )
                } else {
                    experiment.errors.reject('assoc', "Duplicate value of ${biomarker.name} for sample ${sampleDataID} and method ${method}. Select override to proceed")
                    experiment.id = null
                    experiment.method = null
                    respond experiment.errors, view:'create'
                    return
                }
                    
                

            }

        }

        // ============================== SAVE EVERYTHING

        try {
            experimentService.save(experiment)
        } catch (ValidationException e) {
            respond experiment.errors, view:'create'
            return
        }
        
        try {
            for (SampleBiomarker sb in listSampleBiomarker){
                sampleBiomarkerService.save(sb)
            }
        } catch (ValidationException e) {
            experiment.errors.reject('err', "Error adding values")
            experiment.id = null
            experiment.method = null
            respond experiment.errors, view:'create'
            return
        }


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'experiment.label', default: 'Experiment'), experiment.id])
                redirect experiment
            }
            '*' { respond experiment, [status: CREATED] }
        }
        
    }

    def edit(Long id) {
        respond experimentService.get(id)
    }

    def update(Experiment experiment) {
        if (experiment == null) {
            notFound()
            return
        }

        // validate no change to files
        def fd = request.getFile('primaryData_updated')
        def fe = request.getFile('secondaryData_updated')
        
        if (fd.empty){
            experiment.primaryData = experimentService.get(experiment.id).primaryData
        } else {
            experiment.primaryData = fd.getBytes()
        }
        
        if (fe.empty){
            experiment.secondaryData = experimentService.get(experiment.id).secondaryData
        } else {
            experiment.secondaryData = fe.getBytes()
        }


        try {
            experimentService.save(experiment)
        } catch (ValidationException e) {
            respond experiment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'experiment.label', default: 'Experiment'), experiment.id])
                redirect experiment
            }
            '*'{ respond experiment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        experimentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'experiment.label', default: 'Experiment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'experiment.label', default: 'Experiment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
