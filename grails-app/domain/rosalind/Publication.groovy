package rosalind

class Publication {

    /* properties */

    PublicationType publicationType
    String bibliographyEntry
    String doi
    Boolean isPublic
    Date year		// for sorting

    /* files */

    byte[] pdfFile

    /* associations */

    // static hasMany = [sampleDataList:SampleData]

    /* timestamps */

    Date dateCreated
    Date lastUpdated

    /* constraints */

    static constraints = {
        pdfFile nullable: true, maxSize: 50 * 1024 * 1024 // (50 MB)
        bibliographyEntry widget: 'textarea'
	doi nullable: true
    }

    /* mappings */

    static mapping = {
	pdfFile sqlType: "longblob"
	bibliographyEntry sqlType: "longtext"
    }

    /* methods */

    String toString() {
	return bibliographyEntry + "(" + year + ")"
    }
    
}
