package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class ExposureController {

    ExposureService exposureService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond exposureService.list(params), model:[exposureCount: exposureService.count()]
    }

    def show(Long id) {
        respond exposureService.get(id)
    }

    def create() {
        respond new Exposure(params)
    }

    def save(Exposure exposure) {
        if (exposure == null) {
            notFound()
            return
        }

        try {
            exposureService.save(exposure)
        } catch (ValidationException e) {
            respond exposure.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'exposure.label', default: 'Exposure'), exposure.id])
                redirect exposure
            }
            '*' { respond exposure, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond exposureService.get(id)
    }

    def update(Exposure exposure) {
        if (exposure == null) {
            notFound()
            return
        }

        try {
            exposureService.save(exposure)
        } catch (ValidationException e) {
            respond exposure.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'exposure.label', default: 'Exposure'), exposure.id])
                redirect exposure
            }
            '*'{ respond exposure, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        exposureService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'exposure.label', default: 'Exposure'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'exposure.label', default: 'Exposure'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
