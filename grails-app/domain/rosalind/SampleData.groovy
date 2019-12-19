package rosalind

class SampleData {

    /* demographic info */

    String labID
    Integer age
    Gender gender
    Race race
    Float weightLb
    Float heightFt
    Float bmi
    BloodGroup bloodGroup
    RhesusFactor rhFactor
    EducationLevel eduLevel
    String homeState

    Cohort cohort
    Date symptomsOnset
    String treatmentNotes
    String sampleType
    Date collected
    Date received
    String notes

    /* permission flag */

    Boolean isPublic

    /* associations */
    
    static belongsTo = [provider:SampleProvider]
    
    static hasMany = [symptoms:Symptom,
		      exposures:Exposure,
		      medications:Medication]

    /* timestamps */

    Date dateCreated
    Date lastUpdated

    /* constraints */

    static constraints = {
	labID unique: true
	treatmentNotes nullable: true
	notes nullable: true, widget: 'textarea'
        age nullable: true
        weightLb nullable: true
        heightFt nullable: true
        bmi nullable: true
        homeState nullable: true 
        gender nullable: true
        race nullable: true
        bloodGroup nullable: true
        symptomsOnset nullable: true
        collected nullable: true
        received nullable: true
        eduLevel nullable: true
        rhFactor nullable: true
        cohort nullable: true
        provider nullable: true
    }

    /* mapping */

    static mapping = {
	treatmentNotes sqlType: "longtext"
	notes sqlType: "longtext"
    }

    /* methods */

    String toString() {
	return labID
    }

    def toMapKeyVal() {
	return this.properties
	    .findAll { !(it.key in doNotList) }
	    .collectEntries { key, val -> 
	    [key, ((val instanceof Collection) && val.isEmpty()) ? null : val] }
    }
    
    /* auxiliary data */

    static private final doNotList = ["lastUpdated", "dateCreated", "isPublic"]
    
}

