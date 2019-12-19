package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class BiomarkerController {

    BiomarkerService biomarkerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond biomarkerService.list(params), model:[biomarkerCount: biomarkerService.count()]
    }

    def show(Long id) {
        respond biomarkerService.get(id)
    }

    def create() {
        respond new Biomarker(params)
    }

    def save(Biomarker biomarker) {
        if (biomarker == null) {
            notFound()
            return
        }

        try {
            biomarkerService.save(biomarker)
        } catch (ValidationException e) {
            respond biomarker.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'biomarker.label', default: 'Biomarker'), biomarker.id])
                redirect biomarker
            }
            '*' { respond biomarker, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond biomarkerService.get(id)
    }

    def update(Biomarker biomarker) {
        if (biomarker == null) {
            notFound()
            return
        }

        try {
            biomarkerService.save(biomarker)
        } catch (ValidationException e) {
            respond biomarker.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'biomarker.label', default: 'Biomarker'), biomarker.id])
                redirect biomarker
            }
            '*'{ respond biomarker, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        biomarkerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'biomarker.label', default: 'Biomarker'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'biomarker.label', default: 'Biomarker'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
