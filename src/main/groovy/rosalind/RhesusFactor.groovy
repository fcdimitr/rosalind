package rosalind

enum RhesusFactor {

    PRESENT( '+' ),
    ABSENT(  '-' )

    final String val
    RhesusFactor( String val ) { this.val = val }

    String toString() {	return val }
    String getKey() { return name() }
    
}
