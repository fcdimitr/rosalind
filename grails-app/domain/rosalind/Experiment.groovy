package rosalind

class Experiment {

    /* properties */

    String primaryNotes
    String secondaryNotes

    /* files */

    byte[] primaryData
    byte[] secondaryData

    String primaryExt
    String secondaryExt

    /* permission flag */

    // Boolean isPublic

    /* associations */

    Method method

    /* timestamps */

    Date dateCreated
    Date lastUpdated

    /* constraints */

    static constraints = {
	primaryData maxSize: 20 * 1024 * 1024 // (20 MB)
	secondaryData maxSize: 20 * 1024 * 1024 // (20 KB)
        primaryNotes widget: 'textarea', nullable: true
        secondaryNotes widget: 'textarea', nullable: true
        primaryExt nullable: true
        secondaryExt nullable: true
    }

    /* mappings */
    
    static mapping = {
	primaryNotes sqlType: "longtext"
	secondaryNotes sqlType: "longtext"
    }


    static hasMany = [sampleBiomarkers:SampleBiomarker]

    /* mappings */

    // static mapping = {
    // 	dataFile column: 'data_file_bytes', sqlType: 'longblob'
    // 	estimationFile column: 'estimation_file_bytes', sqlType: 'longblob'
    // }

    /* methods */

    String toString() {
	return "[ " + id?.toString() + "] Method: " + method?.toString()
    }

    def toMapKeyVal() {
	return this.properties
	    .findAll { !(it.key in doNotList) }
    }

    /* auxiliary data */

    static private final doNotList = ["lastUpdated", "dateCreated", "isPublic",
                                      "primaryData", "secondaryData"]
    
}
