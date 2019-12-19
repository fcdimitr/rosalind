package rosalind

import grails.plugin.springsecurity.annotation.Secured


@Secured( ['ROLE_ADMIN'] )
// @Secured(['IS_AUTHENTICATED_REMEMBERED']) 
class AdminController {

    // // name for admin panel
    // static final String myName = "Admin panel"
    
    def index() { }
    
}
