package rosalind

// http://docs.grails.org/3.0.2/guide/GORM.html#compositePrimaryKeys
import org.apache.commons.lang.builder.HashCodeBuilder

class SampleBiomarker implements Serializable{

    Biomarker biomarker
    SampleData sampleData
    Float value

    boolean equals(other) {
        if (!(other instanceof SampleBiomarker)) {
            return false
        }

        other.biomarker == biomarker && other.sampleData == sampleData
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        builder.append biomarker
        builder.append sampleData
        builder.toHashCode()
    }
    
    static constraints = {
        // each sample can have only one instance of each NIB
        biomarker unique: ['sampleData']
    }

    static belongsTo = [experiment:Experiment]

    static mapping = {
        id composite: ['biomarker', 'sampleData']
    }
    
    String toString() {
	return sampleData.toString() + "(" + biomarker.toString() + " - " + value.toString() + ")"
    }
    
}
