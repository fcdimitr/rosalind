package rosalind


import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured


import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.io.FileUtils
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream


@Secured( ['ROLE_ADMIN'] )
class DataRequestController {

    static final String myName = "Manage data requests"

    DataRequestService dataRequestService
    DownloadLogService downloadLogService
    SampleDataService sampleDataService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dataRequestService.list(params), model:[dataRequestCount: dataRequestService.count()]
    }

    def show(Long id) {
        respond dataRequestService.get(id)
    }

    def create() {
        respond new DataRequest(params)
    }

    def save(DataRequest dataRequest) {
        if (dataRequest == null) {
            notFound()
            return
        }

        try {
            dataRequestService.save(dataRequest)
        } catch (ValidationException e) {
            respond dataRequest.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataRequest.label', default: 'DataRequest'), dataRequest.id])
                redirect dataRequest
            }
            '*' { respond dataRequest, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond dataRequestService.get(id)
    }

    def update(DataRequest dataRequest) {
        if (dataRequest == null) {
            notFound()
            return
        }

        try {
            dataRequestService.save(dataRequest)

            // send e-mail to user
            // sendMail {
            //     from "Rosalind SQL<email@domain.com>"
            //     to dataRequest.user.email
            //     subject "Your request has been processed"
            //     text "The status of your request is: " + dataRequest.requestStatus
            // }

            
        } catch (ValidationException e) {
            respond dataRequest.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataRequest.label', default: 'DataRequest'), dataRequest.id])
                redirect dataRequest
            }
            '*'{ respond dataRequest, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dataRequestService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataRequest.label', default: 'DataRequest'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataRequest.label', default: 'DataRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /* ================================================== */

    @Secured( ['IS_AUTHENTICATED_REMEMBERED'] )
    def myRequests() {
	def currentUser = User.get(springSecurityService.principal.id)
	respond (DataRequest
		 .findAllByUser( currentUser )
		 .sort { a,b -> b.dateCreated <=> a.dateCreated })
    }

    // ------------------------------

    protected void noApprovedSetSelected() {
	request.withFormat {
            form multipartForm {
                flash.message = "Please select an approved set for download"
                redirect action:"myRequests", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def adminGetData() {
        
    }

    def adminExportData() {

	// get list of samples & biomarkers
	def samples
        if (params.listOfSamples) samples = SampleData.getAll( params.listOfSamples )
        else samples = SampleData.list( )

        def biomarkers
        if (params.listOfBiomarkers) biomarkers = Biomarker.getAll( params.listOfBiomarkers )
        else biomarkers = Biomarker.list(  )


	// prepare CSV and return
        def outs = response.outputStream
        response.status = OK.value()
	response.setContentType('text/csv;charset=UTF-8')
        response.setHeader('Content-Disposition',
			   'Attachment; filename="requested_data.csv"')

        // headers
        outs << "Sample ID,"
        for (def biomarker in biomarkers){
            outs << "${biomarker.name},"
        }
        outs << "\n"

        // units
        outs << ","
        for (def biomarker in biomarkers){
            if (biomarker.unit) outs << "${biomarker.unit},"
            else outs << ","
        }
        outs << "\n"
        
        // get all samples and biomarkers requested
        for (def sample in samples){
            outs << "${sample.labID},"
            for (def biomarker in biomarkers){
                def sb = SampleBiomarker.findBySampleDataAndBiomarker(sample, biomarker)
                if (sb) outs << "${sb.value},"
                else outs << ","
            }
            outs << "\n"
        }
	
    }
    

    // ------------------------------

    @Secured( ['IS_AUTHENTICATED_REMEMBERED'] )
    def exportSampleData() {

	// empty selection?
	if ( params.downloadId == null ) {
	    noApprovedSetSelected()
            return
	}

	// get selected data request and ensure it is approved
	DataRequest dr = dataRequestService.get( params.downloadId )
	if (!( dr.requestStatus == RequestStatus.APPROVED )) {
	    noApprovedSetSelected()
            return
	}

	// log download
	DownloadLog downloadLog = new DownloadLog(
	    user: User.get(springSecurityService.principal.id),
            dataRequest: dr
	)

	try {
            downloadLogService.save(downloadLog)
        } catch (ValidationException e) {
	    println e
	    request.withFormat {
		form multipartForm {
                    flash.message = "Could not process request due to download logging error.  Please contact the administrator."
                    redirect action: "myRequests", method: "GET"
		}
		'*'{ render status: NOT_FOUND }
            }
	    return
	}

	// prepare CSV and return
        def outs = response.outputStream
        response.status = OK.value()
	response.setContentType('text/csv;charset=UTF-8')
        response.setHeader('Content-Disposition',
			   'Attachment; filename="requested_data.csv"')

	// get list of samples & biomarkers associated with request
	def samples = dr.sampleDataList
        def biomarkers = dr.biomarkerList
        
        // headers
        outs << "Sample ID,"
        for (def biomarker in biomarkers){
            outs << "${biomarker.name},"
        }
        outs << "\n"

        // units
        outs << ","
        for (def biomarker in biomarkers){
            if (biomarker.unit) outs << "${biomarker.unit},"
            else outs << ","
        }
        outs << "\n"
        
        // get all samples and biomarkers requested
        for (def sample in samples){
            outs << "${sample.labID},"
            for (def biomarker in biomarkers){
                def sb = SampleBiomarker.findBySampleDataAndBiomarker(sample, biomarker)
                if (sb) outs << "${sb.value},"
                else outs << ","
            }
            outs << "\n"
        }
	
    }
    
}
