package rosalind

class DataRequest {

    /* properties */
    
    String purpose
    String agreement
    RequestStatus requestStatus

    /* associations */

    User user

    static final hasMany = [sampleDataList:SampleData,
                            biomarkerList:Biomarker]
    
    /* timestamps */

    Date dateCreated
    Date lastUpdated

    /* constraints */
    
    static constraints = {
    }

    /* mappings */

    static mapping = {
	purpose sqlType: "longtext"
	agreement sqlType: "longtext"
    }

    /* constants */

    public static final String prototypeAgreement = 
    	'''
        These data are the property of BM, PhD from the Department of Cancer
    	Biology, Duke University USA. The data are free for non-commercial
    	use. If you use such data for your research or applications, please
    	acknowledge their origin by a sentence like <i>"We acknowledge the use
    	of XXXX database that was funded by YYYYY program (contract no. NNNNNN)
    	for providing data"</i>, and also cite the corresponding publications:
        <ul>
          <li>AAAAAAA</li>
          <li>BBBBBBB</li>
        </ul>
        '''
    
}
