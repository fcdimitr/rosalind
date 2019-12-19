package rosalind

class EducationLevel {

    String name
    
    static constraints = {
        name unique: true
    }

    String toString() {
	return name
    }

}
