package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class PublicationTypeController {

    PublicationTypeService publicationTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond publicationTypeService.list(params), model:[publicationTypeCount: publicationTypeService.count()]
    }

    def show(Long id) {
        respond publicationTypeService.get(id)
    }

    def create() {
        respond new PublicationType(params)
    }

    def save(PublicationType publicationType) {
        if (publicationType == null) {
            notFound()
            return
        }

        try {
            publicationTypeService.save(publicationType)
        } catch (ValidationException e) {
            respond publicationType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'publicationType.label', default: 'PublicationType'), publicationType.id])
                redirect publicationType
            }
            '*' { respond publicationType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond publicationTypeService.get(id)
    }

    def update(PublicationType publicationType) {
        if (publicationType == null) {
            notFound()
            return
        }

        try {
            publicationTypeService.save(publicationType)
        } catch (ValidationException e) {
            respond publicationType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'publicationType.label', default: 'PublicationType'), publicationType.id])
                redirect publicationType
            }
            '*'{ respond publicationType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        publicationTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'publicationType.label', default: 'PublicationType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicationType.label', default: 'PublicationType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
