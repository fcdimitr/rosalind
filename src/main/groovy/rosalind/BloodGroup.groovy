package rosalind

enum BloodGroup {

    A(  "A" ),
    B(  "B" ),
    AB( "AB" ),
    O(  "O" )

    final String val
    BloodGroup( String val ) { this.val = val }

    String toString() { return val }
    String getKey() { return name() }

}
