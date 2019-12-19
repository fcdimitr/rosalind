package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class SampleBiomarkerController {

    SampleBiomarkerService sampleBiomarkerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sampleBiomarkerService.list(params), model:[sampleBiomarkerCount: sampleBiomarkerService.count()]
    }

    def show(Long id) {
        respond sampleBiomarkerService.get(id)
    }

    def create() {
        respond new SampleBiomarker(params)
    }

    def save(SampleBiomarker sampleBiomarker) {
        if (sampleBiomarker == null) {
            notFound()
            return
        }

        try {
            sampleBiomarkerService.save(sampleBiomarker)
        } catch (ValidationException e) {
            respond sampleBiomarker.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sampleBiomarker.label', default: 'SampleBiomarker'), sampleBiomarker.id])
                redirect sampleBiomarker
            }
            '*' { respond sampleBiomarker, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sampleBiomarkerService.get(id)
    }

    def update(SampleBiomarker sampleBiomarker) {
        if (sampleBiomarker == null) {
            notFound()
            return
        }

        try {
            sampleBiomarkerService.save(sampleBiomarker)
        } catch (ValidationException e) {
            respond sampleBiomarker.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sampleBiomarker.label', default: 'SampleBiomarker'), sampleBiomarker.id])
                redirect sampleBiomarker
            }
            '*'{ respond sampleBiomarker, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sampleBiomarkerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sampleBiomarker.label', default: 'SampleBiomarker'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sampleBiomarker.label', default: 'SampleBiomarker'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
