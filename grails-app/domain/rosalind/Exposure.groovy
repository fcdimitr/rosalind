package rosalind

class Exposure {

    String name
    
    static constraints = {
        name unique: true
    }

    String toString() {
	return name
    }

    
}
