package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

// @Secured(['IS_AUTHENTICATED_REMEMBERED'])
@Secured( ['ROLE_ADMIN'] )
class SampleProviderController {

    // name for admin panel
    static final String myName = "Manage sample providers"

    
    SampleProviderService sampleProviderService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sampleProviderService.list(params), model:[sampleProviderCount: sampleProviderService.count()]
    }

    def show(Long id) {
        respond sampleProviderService.get(id)
    }

    def create() {
        respond new SampleProvider(params)
    }

    def createAndAttach() {
        respond new SampleProvider(params)
    }

    def saveAndAttach(SampleProvider sampleProvider) {
        if (sampleProvider == null) {
            notFound()
            return
        }

        def samples = SampleData.getAll( params.listOfSamples )
        sampleProvider.addToSampleDataList(samples)
        
        try {
            sampleProviderService.save(sampleProvider)
        } catch (ValidationException e) {
            respond sampleProvider.errors, view:'create'
            return
        }
            

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sampleProvider.label', default: 'SampleProvider'), sampleProvider.id])
                redirect sampleProvider
            }
            '*' { respond sampleProvider, [status: CREATED] }
        }
    }


    def save(SampleProvider sampleProvider) {
        if (sampleProvider == null) {
            notFound()
            return
        }

        try {
            sampleProviderService.save(sampleProvider)
        } catch (ValidationException e) {
            respond sampleProvider.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sampleProvider.label', default: 'SampleProvider'), sampleProvider.id])
                redirect sampleProvider
            }
            '*' { respond sampleProvider, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sampleProviderService.get(id)
    }

    def update(SampleProvider sampleProvider) {
        if (sampleProvider == null) {
            notFound()
            return
        }

        try {
            sampleProviderService.save(sampleProvider)
        } catch (ValidationException e) {
            respond sampleProvider.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sampleProvider.label', default: 'SampleProvider'), sampleProvider.id])
                redirect sampleProvider
            }
            '*'{ respond sampleProvider, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sampleProviderService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sampleProvider.label', default: 'SampleProvider'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sampleProvider.label', default: 'SampleProvider'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
