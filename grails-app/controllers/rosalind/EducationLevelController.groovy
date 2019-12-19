package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured( ['ROLE_ADMIN'] )
class EducationLevelController {

    EducationLevelService educationLevelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond educationLevelService.list(params), model:[educationLevelCount: educationLevelService.count()]
    }

    def show(Long id) {
        respond educationLevelService.get(id)
    }

    def create() {
        respond new EducationLevel(params)
    }

    def save(EducationLevel educationLevel) {
        if (educationLevel == null) {
            notFound()
            return
        }

        try {
            educationLevelService.save(educationLevel)
        } catch (ValidationException e) {
            respond educationLevel.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'educationLevel.label', default: 'EducationLevel'), educationLevel.id])
                redirect educationLevel
            }
            '*' { respond educationLevel, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond educationLevelService.get(id)
    }

    def update(EducationLevel educationLevel) {
        if (educationLevel == null) {
            notFound()
            return
        }

        try {
            educationLevelService.save(educationLevel)
        } catch (ValidationException e) {
            respond educationLevel.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'educationLevel.label', default: 'EducationLevel'), educationLevel.id])
                redirect educationLevel
            }
            '*'{ respond educationLevel, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        educationLevelService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'educationLevel.label', default: 'EducationLevel'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'educationLevel.label', default: 'EducationLevel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
