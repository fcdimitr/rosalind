package rosalind

class Biomarker {
    
    String name
    String unit
    Boolean isPublic

    static belongsTo = [method: Method]

    String toString() {
	return name
    }
    
    static constraints = {
        name unique: ['method']
        unit nullable: true
    }
}
