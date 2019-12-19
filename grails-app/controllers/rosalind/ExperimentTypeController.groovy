package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class ExperimentTypeController {

    ExperimentTypeService experimentTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond experimentTypeService.list(params), model:[experimentTypeCount: experimentTypeService.count()]
    }

    def show(Long id) {
        respond experimentTypeService.get(id)
    }

    def create() {
        respond new ExperimentType(params)
    }

    def save(ExperimentType experimentType) {
        if (experimentType == null) {
            notFound()
            return
        }

        try {
            experimentTypeService.save(experimentType)
        } catch (ValidationException e) {
            respond experimentType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'experimentType.label', default: 'ExperimentType'), experimentType.id])
                redirect experimentType
            }
            '*' { respond experimentType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond experimentTypeService.get(id)
    }

    def update(ExperimentType experimentType) {
        if (experimentType == null) {
            notFound()
            return
        }

        try {
            experimentTypeService.save(experimentType)
        } catch (ValidationException e) {
            respond experimentType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'experimentType.label', default: 'ExperimentType'), experimentType.id])
                redirect experimentType
            }
            '*'{ respond experimentType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        experimentTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'experimentType.label', default: 'ExperimentType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'experimentType.label', default: 'ExperimentType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
