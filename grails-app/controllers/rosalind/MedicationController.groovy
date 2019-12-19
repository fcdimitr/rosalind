package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class MedicationController {

    MedicationService medicationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond medicationService.list(params), model:[medicationCount: medicationService.count()]
    }

    def show(Long id) {
        respond medicationService.get(id)
    }

    def create() {
        respond new Medication(params)
    }

    def save(Medication medication) {
        if (medication == null) {
            notFound()
            return
        }

        try {
            medicationService.save(medication)
        } catch (ValidationException e) {
            respond medication.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'medication.label', default: 'Medication'), medication.id])
                redirect medication
            }
            '*' { respond medication, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond medicationService.get(id)
    }

    def update(Medication medication) {
        if (medication == null) {
            notFound()
            return
        }

        try {
            medicationService.save(medication)
        } catch (ValidationException e) {
            respond medication.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'medication.label', default: 'Medication'), medication.id])
                redirect medication
            }
            '*'{ respond medication, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        medicationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'medication.label', default: 'Medication'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'medication.label', default: 'Medication'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
