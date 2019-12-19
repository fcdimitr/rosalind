package rosalind

class Cohort {

    String name
    
    static constraints = {
        name unique: true
    }

    String toString() {
	return name
    }
}
