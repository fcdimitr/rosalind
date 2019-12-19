package rosalind

class Medication {

    String name
    
    static constraints = {
        name unique: true
    }

    String toString() {
	return name
    }


}
