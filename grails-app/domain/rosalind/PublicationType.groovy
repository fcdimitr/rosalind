package rosalind

class PublicationType {

    String name
    
    static constraints = {
        name unique: true
    }

    String toString() {
	return name
    }
    
}
