package rosalind

class FrontEndField {

    String name
    String htmlContent

    /* constraints */
    
    static constraints = {
        name unique: true
        htmlContent widget: 'textarea'
    }

    /* mapping */

    static mapping = {
	htmlContent sqlType: "longText"
    }

}
