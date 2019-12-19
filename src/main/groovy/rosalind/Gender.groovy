package rosalind

enum Gender {

    MALE("Male"),
    FEMALE("Female")

    final String val
    Gender( String val ) { this.val = val }

    String toString() { return val }
    String getKey() { return name() }

}
