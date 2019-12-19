package rosalind

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

// @Secured(['IS_AUTHENTICATED_REMEMBERED'])
@Secured( ['ROLE_ADMIN'] )
class MethodController {

    // name for admin panel
    static final String myName = "Manage method records"

    
    MethodService methodService
    BiomarkerService biomarkerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond methodService.list(params), model:[methodCount: methodService.count()]
    }

    def show(Long id) {
        respond methodService.get(id)
    }

    def create() {
        respond new Method(params)
    }

    def save(Method method) {
        if (method == null) {
            notFound()
            return
        }


        Method.withTransaction{ status -> 

            try {
                methodService.save(method)
            } catch (ValidationException e) {
                respond method.errors, view:'create'
                status.setRollbackOnly()
                return
            }

            
            def biomarkerLabels = params.csvListBiomarkers.split(',')
            
            for (def biomarkerLabel in biomarkerLabels) {

                // regular expression to get name [units]
                def m = biomarkerLabel =~ /(.*)\[(.*)\]/;
                
                try{

                    if (!m.size()){
                        method.errors.reject('biomark', "Incorrect typeset for ${biomarkerLabel}")
                        respond method.errors, view:'create'
                        status.setRollbackOnly()
                        return
                    }
                    
                    Biomarker biomarker = new Biomarker(
                        name: m[0][1].trim(),
                        unit: m[0][2].trim(),
                        method: method,
                        isPublic: false )
                    biomarkerService.save(biomarker)
                } catch (ValidationException e) {
                    method.errors.reject('biomark', "Could not add biomarker ${biomarkerLabel}")
                    respond method.errors, view:'create'
                    status.setRollbackOnly()
                    return
                }
            }
            
            // def city = new City( city: fields[0].trim(),
            //                     description: fields[1].trim() )

            // if (city.hasErrors() || city.save(flush: true) == null) {
            //     log.error("Could not import domainObject  ${city.errors}")
            // }

            // log.debug("Importing domainObject  ${city.toString()}")

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'method.label', default: 'Method'), method.id])
                    redirect method
                }
                '*' { respond method, [status: CREATED] }
            }
            
        }
    }

    def edit(Long id) {
        respond methodService.get(id)
    }

    def update(Method method) {
        if (method == null) {
            notFound()
            return
        }

         Method.withTransaction{ status -> 

            try {
                methodService.save(method)
            } catch (ValidationException e) {
                respond method.errors, view:'edit'
                status.setRollbackOnly()
                return
            }

            if ( params.csvListBiomarkers.trim() ) {
                def biomarkerLabels = params.csvListBiomarkers.split(',')
                
                for (def biomarkerLabel in biomarkerLabels) {

                    // regular expression to get name [units]
                    def m = biomarkerLabel =~ /(.*)\[(.*)\]/;
                    
                    try{

                        if (!m.size()){
                            method.errors.reject('biomark', "Incorrect typeset for ${biomarkerLabel}")
                            respond method.errors, view:'edit'
                            status.setRollbackOnly()
                            return
                        }
                        
                        Biomarker biomarker = new Biomarker(
                            name: m[0][1].trim(),
                            unit: m[0][2].trim(),
                            method: method,
                            isPublic: false )
                        biomarkerService.save(biomarker)
                    } catch (ValidationException e) {
                        method.errors.reject('biomark', "Could not add biomarker ${biomarkerLabel}")
                        respond method.errors, view:'edit'
                        status.setRollbackOnly()
                        return
                    }
                }
            }

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.updated.message', args: [message(code: 'method.label', default: 'Method'), method.id])
                    redirect method
                }
                '*'{ respond method, [status: OK] }
            }
            
        }

        
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        methodService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'method.label', default: 'Method'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'method.label', default: 'Method'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
