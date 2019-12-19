package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

// @Secured(['IS_AUTHENTICATED_REMEMBERED'])
@Secured( ['ROLE_ADMIN'] )
class PublicationController {

    // name for admin panel
    static final String myName = "Manage publications"

    PublicationTypeService publicationTypeService
    PublicationService publicationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond publicationService.list(params), model:[publicationCount: publicationService.count()]
	
    }

    @Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'] )
    def showPublications() {
		    
    }

    @Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'] )
    def downloadPdfFile(Long id) {
        Publication publication = publicationService.get(id)
        if (!publication || publication.pdfFile == null) {
            notFound()
            return
        }
	response.setHeader("Content-disposition",
			   "attachment; filename=pub_" + id + ".pdf")
        render file: publication.pdfFile,
            contentType: "text/pdf"
    }

    def show(Long id) {
        respond publicationService.get(id)
    }

    def create() {
        respond new Publication(params)
    }

    def save(Publication publication) {
        if (publication == null) {
            notFound()
            return
        }

        try {
            publicationService.save(publication)
        } catch (ValidationException e) {
            respond publication.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'publication.label', default: 'Publication'), publication.id])
                redirect publication
            }
            '*' { respond publication, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond publicationService.get(id)
    }

    def update(Publication publication) {
        if (publication == null) {
            notFound()
            return
        }

        // validate no change to files
        def fp = request.getFile('pdfFile_updated')
        
        if (fp.empty){
            publication.pdfFile = publicationService.get(publication.id).pdfFile
        } else {
            publication.pdfFile = fp.getBytes()
        }

        try {
            publicationService.save(publication)
        } catch (ValidationException e) {
            respond publication.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'publication.label', default: 'Publication'), publication.id])
                redirect publication
            }
            '*'{ respond publication, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        publicationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'publication.label', default: 'Publication'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'publication.label', default: 'Publication'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
