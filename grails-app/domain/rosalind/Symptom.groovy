package rosalind

class Symptom {

    String name
    
    static constraints = {
        name unique: true
    }

    String toString() {
	return name
    }
    
}
