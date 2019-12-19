package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class FrontEndFieldController {

    FrontEndFieldService frontEndFieldService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond frontEndFieldService.list(params), model:[frontEndFieldCount: frontEndFieldService.count()]
    }

    def show(Long id) {
        respond frontEndFieldService.get(id)
    }

    def create() {
        respond new FrontEndField(params)
    }

    def save(FrontEndField frontEndField) {
        if (frontEndField == null) {
            notFound()
            return
        }

        try {
            frontEndFieldService.save(frontEndField)
        } catch (ValidationException e) {
            respond frontEndField.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'frontEndField.label', default: 'FrontEndField'), frontEndField.id])
                redirect frontEndField
            }
            '*' { respond frontEndField, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond frontEndFieldService.get(id)
    }

    def update(FrontEndField frontEndField) {
        if (frontEndField == null) {
            notFound()
            return
        }

        try {
            frontEndFieldService.save(frontEndField)
        } catch (ValidationException e) {
            respond frontEndField.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'frontEndField.label', default: 'FrontEndField'), frontEndField.id])
                redirect frontEndField
            }
            '*'{ respond frontEndField, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        frontEndFieldService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'frontEndField.label', default: 'FrontEndField'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'frontEndField.label', default: 'FrontEndField'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
