package rosalind

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
// import grails.plugin.mail.MailService


@Transactional
// @Secured(['ROLE_ADMIN'])
@Secured('permitAll')
class RegisterController {

    // name for admin panel
    static final String myName = "Register new user"

    static allowedMethods = [register: "POST"]

    def index() { }

    def register() {
        if(!params.password.equals(params.repassword)) {
            flash.message = "Password and Re-Password not match"
            redirect action: "index"
            return
        } else {
            try {
                def user = User.findByUsername(params.username)?:
                    new User(username: params.username,
                             password: params.password,
                             affiliation: params.affiliation,
                             email: params.email).save()
                def role = Role.findByAuthority( "ROLE_CLIENT" )
                if(user && role) {
                    UserRole.create user, role

                    UserRole.withSession {
                        it.flush()
                        it.clear()
                    }

                    // sendMail {
                    //     from "Rosalind SQL<email@domain.com>"
                    //     to user.email
                    //     subject "Welcome"
                    //     text "Your accound has been created successfully."
                    // }
                    
                    flash.message = "You have registered successfully. Please login."
                    redirect controller: "login", action: "auth"
                } else {
                    flash.message = "Register failed"
                    render view: "index"
                    return
                }
            } catch (ValidationException e) {
                flash.message = "Register Failed"
                redirect action: "index"
                return
            }
        }
    }
}
