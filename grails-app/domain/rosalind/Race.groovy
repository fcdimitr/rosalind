package rosalind

class Race {

    String name
    
    static constraints = {
        name unique: true
    }

    String toString() {
	return name
    }

}
