package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class CohortController {

    CohortService cohortService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond cohortService.list(params), model:[cohortCount: cohortService.count()]
    }

    def show(Long id) {
        respond cohortService.get(id)
    }

    def create() {
        respond new Cohort(params)
    }

    def save(Cohort cohort) {
        if (cohort == null) {
            notFound()
            return
        }

        try {
            cohortService.save(cohort)
        } catch (ValidationException e) {
            respond cohort.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cohort.label', default: 'Cohort'), cohort.id])
                redirect cohort
            }
            '*' { respond cohort, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond cohortService.get(id)
    }

    def update(Cohort cohort) {
        if (cohort == null) {
            notFound()
            return
        }

        try {
            cohortService.save(cohort)
        } catch (ValidationException e) {
            respond cohort.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cohort.label', default: 'Cohort'), cohort.id])
                redirect cohort
            }
            '*'{ respond cohort, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        cohortService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cohort.label', default: 'Cohort'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cohort.label', default: 'Cohort'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
