package rosalind

class Image {

    /* image content */
    byte[] image

    /* image name & type (read from file) */
    String name
    String type
    
    /* constraints */

    static constraints = {
	image maxSize: 20 * 1024 * 1024 // (20 MB)
    }

}
