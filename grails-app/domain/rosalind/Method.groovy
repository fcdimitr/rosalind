package rosalind

class Method {

    /* properties */

    ExperimentType experiment
    String shortDescription
    String materialsAndMethods

    /* associations */
    static hasMany = [biomarkers:Biomarker]
    
    /* permission flag */

    // Boolean isPublic

    /* timestamps */
    
    Date dateCreated
    Date lastUpdated

    /* mappings */
    
    static mapping = {
	materialsAndMethods sqlType: "longtext"
	shortDescription sqlType: "text"
    }

    
    /* constraints */

    static constraints = {
        materialsAndMethods widget: 'textarea'
        shortDescription widget: 'textarea'
    }

    /* methods */

    String toString() {
	return shortDescription?.toString() + " (" + experiment?.toString() + ")"
    }

    def toMapKeyVal() {
	return this.properties
	    .findAll { !(it.key in doNotList) }
    }

    /* auxiliary data */

    static private final doNotList = ["lastUpdated", "dateCreated", "isPublic"]
    
}
