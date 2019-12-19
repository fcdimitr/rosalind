package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class SymptomController {

    SymptomService symptomService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond symptomService.list(params), model:[symptomCount: symptomService.count()]
    }

    def show(Long id) {
        respond symptomService.get(id)
    }

    def create() {
        respond new Symptom(params)
    }

    def save(Symptom symptom) {
        if (symptom == null) {
            notFound()
            return
        }

        try {
            symptomService.save(symptom)
        } catch (ValidationException e) {
            respond symptom.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'symptom.label', default: 'Symptom'), symptom.id])
                redirect symptom
            }
            '*' { respond symptom, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond symptomService.get(id)
    }

    def update(Symptom symptom) {
        if (symptom == null) {
            notFound()
            return
        }

        try {
            symptomService.save(symptom)
        } catch (ValidationException e) {
            respond symptom.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'symptom.label', default: 'Symptom'), symptom.id])
                redirect symptom
            }
            '*'{ respond symptom, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        symptomService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'symptom.label', default: 'Symptom'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'symptom.label', default: 'Symptom'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
