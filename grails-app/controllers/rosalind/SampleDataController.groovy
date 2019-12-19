package rosalind


import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured


@Secured( ['ROLE_ADMIN'] )
class SampleDataController {

    // name for admin panel
    static final String myName = "Manage sample data"
    
    SampleDataService sampleDataService
    DataRequestService dataRequestService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sampleDataService.list(params), model:[sampleDataCount: sampleDataService.count()]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def show(Long id) {
        respond sampleDataService.get(id)
    }

    def create() {
        respond new SampleData(params)
    }

    def save(SampleData sampleData) {
        if (sampleData == null) {
            notFound()
            return
        }

        println params
        println "OK"
        
        try {
            sampleDataService.save(sampleData)
        } catch (ValidationException e) {
            respond sampleData.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sampleData.label', default: 'SampleData'), sampleData.id])
                redirect sampleData
            }
            '*' { respond sampleData, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sampleDataService.get(id)
    }

    def update(SampleData sampleData) {
        if (sampleData == null) {
            notFound()
            return
        }

	// // obsolete -- kept as guide, just in case
        // // validate no change to files
        // def fd = request.getFile('finalEstimatesFile_updated')
        // if (fd.empty){
        //     sampleData.finalEstimatesFile = sampleDataService.get(sampleData.id).finalEstimatesFile
        // } else {
        //     sampleData.finalEstimatesFile = fd.getBytes()
        // }
        	

        try {
            sampleDataService.save(sampleData)
        } catch (ValidationException e) {
            respond sampleData.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sampleData.label', default: 'SampleData'), sampleData.id])
                redirect sampleData
            }
            '*'{ respond sampleData, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sampleDataService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sampleData.label', default: 'SampleData'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sampleData.label', default: 'SampleData'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /* ================================================== */

    // // obsolete -- kept as guide, just in case
    
    // def downloadFile(Long id) {
    //     SampleData sampleData = sampleDataService.get(id)
    //     if (!sampleData || sampleData.finalEstimatesFile == null) {
    //         notFound()
    //         return
    //     }
    //     render file: sampleData.finalEstimatesFile,
    //     contentType: "text/plain"
    // }

    // ------------------------------

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def showAvailable() {
	respond sampleDataList: SampleData.findAllIsPublic(),
	        textAgreement: DataRequest.prototypeAgreement
    }

    // ------------------------------

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def submitDataRequest() {

	// empty selection?
	if ( params.downloadEntry == null ) {
            request.withFormat {
                form multipartForm {
                    flash.message = "No samples were selected"
                    redirect action:"showAvailable", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
            return
	}

	// get list of selected samples
	def sampleDataList = SampleData.getAll( params.downloadEntry )

        for (def sampleData in sampleDataList){
            if (!sampleData.isPublic){
                request.withFormat {
		    form multipartForm {
                        flash.message = "Could not process request.  Please contact the administrator."
                        redirect action: "showAvailable", method: "GET"
		    }
		    '*'{ render status: NONE_SELECTED }
                }
	        return
            }
        }

        def biomarkerList
        if (params.listOfBiomarkers) biomarkerList = Biomarker.getAll( params.listOfBiomarkers )
        else biomarkerList = Biomarker.findAllByIsPublic( true )

        for (def biomarker in biomarkerList){
            if (!biomarker.isPublic){
                request.withFormat {
		    form multipartForm {
                        flash.message = "Could not process request.  Please contact the administrator."
                        redirect action: "showAvailable", method: "GET"
		    }
		    '*'{ render status: NONE_SELECTED }
                }
	        return
            }
        }

        
	// log request in DB
	DataRequest dataRequest = new DataRequest(
	    purpose: params.purpose,
	    agreement: DataRequest.prototypeAgreement,
	    requestStatus: RequestStatus.PENDING,
	    user: User.get(springSecurityService.principal.id),
	    sampleDataList: sampleDataList,
            biomarkerList: biomarkerList
	)
        println params
        println dataRequest
	try {
            dataRequestService.save(dataRequest)
        } catch (ValidationException e) {
            println dataRequest.errors
	    request.withFormat {
		form multipartForm {
                    flash.message = "Could not process request.  Please contact the administrator."
                    redirect action: "showAvailable", method: "GET"
		}
		'*'{ render status: NONE_SELECTED }
            }
	    return
	}

        // send e-mail to admin
        // sendMail {
        //     from "Rosalind SQL<email@domain.com>"
        //     to "admin@email.com"
        //     subject "New request"
        //     text "A new request has been submitted."
        // }

	// success
	flash.message = "Your request has been submitted.  You will be notified via e-mail upon approval."
	redirect( action: "showAvailable", method: "GET" )
	
    }

    
}
