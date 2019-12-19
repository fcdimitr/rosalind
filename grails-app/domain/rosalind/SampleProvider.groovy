package rosalind

class SampleProvider {

    /* properties */
    
    String name
    String address
    String extLabID

    /* timestamps */

    Date dateCreated
    Date lastUpdated

    /* constraints */

    static constraints = {
        name unique: ['address', 'extLabID']
        address nullable: true
        extLabID nullable: true
    }

    /* methods */

    String toString() {
	return name + " (" + address + ")"
    }

    def toMapKeyVal() {
	return this.properties
	    .findAll { !(it.key in doNotList) }
    }

    static hasMany = [sampleDataList:SampleData]

    /* auxiliary data */

    static private final doNotList = ["lastUpdated", "dateCreated"]
    
}
