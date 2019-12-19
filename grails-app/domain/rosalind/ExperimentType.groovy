package rosalind

class ExperimentType {

    String name

    static constraints = {
        name unique: true
    }

    String toString() {
	return name
    }

}
