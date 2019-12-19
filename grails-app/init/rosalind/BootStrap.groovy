package rosalind

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

        // CLIENT
        if ( !Role.findByAuthority('ROLE_CLIENT') ) {
            Role.withTransaction{
                new Role(authority: 'ROLE_CLIENT').save()
            }
            User.withTransaction{
                new User(username: "client",
                         password: "client",
			 email: "foo@bar.com",
			 affiliation: "Affiliation").save()
            }
            
            UserRole.withTransaction{
                new UserRole(user: User.findByUsername("client"),
                             role: Role.findByAuthority("ROLE_CLIENT")).save()
            }
        }

        // ADMIN
        if ( !Role.findByAuthority('ROLE_ADMIN') ) {
            Role.withTransaction{
                new Role(authority: 'ROLE_ADMIN').save()
            }
            User.withTransaction{
                new User(username: "admin",
                         password: "admin",
			 email: "admin@bar.edu",
			 affiliation: "Affiliation").save()
            }

            UserRole.withTransaction{
                new UserRole(user: User.findByUsername("admin"),
                             role: Role.findByAuthority("ROLE_ADMIN")).save()
            }
        }

        // ============================== CONSTANT ENUM-TYPES

        // POPULATE DATA WITH SAMPLE ELEMENTS IF DEVELOPMENT
        if (Environment.current == Environment.DEVELOPMENT) {
            
            def cohorts = [
                'HC',
                'GWV',
                'GWI',
                'CFS',
                'PD',
                'AD',
                'PTSD',
                'TBI',
                'SLE',
                'MS'
            ]

            def exposures = [
                'American vehicle',
                'Anthrax vaccine',
                'Biological ',
                'CARC paint',
                'Chemical ',
                'Contact with prisoners',
                'Cream',
                'Desert sand wind',
                'Enemy veh',
                'Fleacollar',
                'Fogged',
                'Herbicides/insecticides',
                'Missile',
                'Napp - pill',
                'Oil fire',
                'PB',
                'Pesticide treated Uniform',
                'Pesticides',
                'Raising plumes',
                'Smoke',
                'Others'
            ]
            
            def medications = [
                'Antibiotics',
                'Antimycotic drugs',
                'Antiviral drugs',
                'Glucocorticoides',
                'Others'
            ]

            def symptoms = [
                'Cardiovascular ailments',
                'Cognitive problems',
                'Dizziness',
                'Emotional problems',
                'Forgetfulness',
                'Headaches',
                'Indigestion',
                'Insomnia',
                'Joint pain',
                'Memory problems',
                'Menstrual disorders',
                'Muscle or joint pain',
                'Neurological problems',
                'Psychological problems',
                'Respiratory disorders',
                'Unexplained weight loss',
                'Unexplained fatigue'
            ]
            
            def experimentTypes = [
                'Confocal image',
                'Gel readouts',
                'Zymogram',
                'ELISPOT',
                'Dot blots',
                'Immunoblots',
                'ELISA',
                'Immuno-Fluorometric Assay',
                'Mass Spec',
                'HPLC'
            ]

            def publicationTypes = [
                "Journal article" ,
                "Conference proceedings article" ,
                "Poster" ,
                "Abstract" ,
                "Project report" ,
                "Lecture note"
            ]

            def races = [
                "American Indian or Alaska Native" ,
                "Asian" ,
                "Black or African American" ,
                "Native Hawaiian or Pacific Islander" ,
                "Caucasian" ,
                "Hispanic" ,
                "Multiracial" ,
                "N/a"
            ]

            def educationLevels = [
                "No schooling completed" ,
                "Primary education degree" ,
                "Secondary education degree" ,
                "College degree" ,
                "Post-graduate degree"
            ]

            // ~~~~~~~~~~~~~~~~~~~~ Cohorts
            cohorts.each{
                if( !Cohort.findByName(it) ){
                    new Cohort(name: it).save()
                }
            }
            
            // ~~~~~~~~~~~~~~~~~~~~ Exposures
            exposures.each{
                if( !Exposure.findByName(it) ){
                    new Exposure(name: it).save()
                }
            }
            
            // ~~~~~~~~~~~~~~~~~~~~ Medications
            medications.each{
                if( !Medication.findByName(it) ){
                    new Medication(name: it).save()
                }
            }

            // ~~~~~~~~~~~~~~~~~~~~ Symptoms
            symptoms.each{
                if( !Symptom.findByName(it) ){
                    new Symptom(name: it).save()
                }
            }

            // ~~~~~~~~~~~~~~~~~~~~ Experiments
            experimentTypes.each{
                if (!ExperimentType.findByName(it)){
                    new ExperimentType(name: it).save()
                }
            }

            // ~~~~~~~~~~~~~~~~~~~~ Publication type
            publicationTypes.each{
                if (!PublicationType.findByName(it)){
                    new PublicationType(name: it).save()
                }
            }
            
            // ~~~~~~~~~~~~~~~~~~~~ Race
            races.each{
                if (!Race.findByName(it)){
                    new Race(name: it).save()
                }
            }
            
            // ~~~~~~~~~~~~~~~~~~~~ Education level
            educationLevels.each{
                if (!EducationLevel.findByName(it)){
                    new EducationLevel(name: it).save()
                }
            }
            

            // ============================== DUMMY DATA

            // ~~~~~~~~~~~~~~~~~~~~ SAMPLE PROVIDER

            if (!SampleProvider.findByName("dummy")){
                SampleProvider.withTransaction{
                    new SampleProvider(name: "dummy").save()
                }
            }
            
            
            // ~~~~~~~~~~~~~~~~~~~~ SAMPLES

            def sampleIDs = [1..20, 1001..1106].flatten()


            sampleIDs.each{
                def labID = it.toString()
                def strLen = labID.size()
                for( def i = 0; i<(4-strLen);i++ )
                    {
	            labID = "0" + labID 
                }

                if (!SampleData.findByLabID(labID)){

                    SampleData.withTransaction{
                        new SampleData(labID: labID,
                                       isPublic: false,
                                       provider: SampleProvider.findByName("dummy"),
                                       sampleType: "dummy").save(failOnError:true)
                    }
                    
                }
            }

            // ============================== FRONT-END INFO PLACEHOLDERS

            def htmlContent =
                """Add content here <b>bold</b> <i>italic</i> <u>underline</u> <a href="google.com">link</a>
<ul>
<li>Item 1</li>
<li>Item 2</li>
</ul>
<ol>
<li>Item 1</li>
<li>Item 2</li>
</ol>
"""

            // ~~~~~~~~~~~~~~~~~~~~ PI info

            if (!FrontEndField.findByName("PI name")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "PI name",
                                      htmlContent: "PI name").save()
                }
            }
            
            if (!FrontEndField.findByName("PI info")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "PI info",
                                      htmlContent: htmlContent).save()
                }
            }
            
            
            // ~~~~~~~~~~~~~~~~~~~~ Project description

            if (!FrontEndField.findByName("Title: Project description")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Title: Project description",
                                      htmlContent: "Project description").save()
                }
            }
            
            if (!FrontEndField.findByName("Project description")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Project description",
                                       htmlContent: htmlContent).save()
                }
            }

            // ~~~~~~~~~~~~~~~~~~~~ Research interests

            if (!FrontEndField.findByName("Title: Research interests")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Title: Research interests",
                                      htmlContent: "Research interests").save()
                }
            }
            
            if (!FrontEndField.findByName("Research interests")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Research interests",
                                       htmlContent: htmlContent).save()
                }
            }

            // ~~~~~~~~~~~~~~~~~~~~ Related links

            if (!FrontEndField.findByName("Title: Related links")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Title: Related links",
                                      htmlContent: "Related links").save()
                }
            }
            
            if (!FrontEndField.findByName("Related links")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Related links",
                                       htmlContent: htmlContent).save()
                }
            }

            // ~~~~~~~~~~~~~~~~~~~~ Collaborators

            if (!FrontEndField.findByName("Title: Collaborators")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Title: Collaborators",
                                      htmlContent: "Collaborators").save()
                }
            }

            
            if (!FrontEndField.findByName("Collaborators")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Collaborators",
                                       htmlContent: htmlContent).save()
                }
            }

            // ~~~~~~~~~~~~~~~~~~~~ Publications

            if (!FrontEndField.findByName("Title: Publications")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Title: Publications",
                                      htmlContent: "Relevant publications").save()
                }
            }

            // ~~~~~~~~~~~~~~~~~~~~ Web-page banner

            def htmlBanner = """
<div class="container-fluid" id="content" role="main">
  <div class="row">
    <div class="d-none d-md-block col-md-2 text-center">
      <img src="/assets/grails.svg" width="70%;">
    </div>
    <div class="col-sm-12 col-md-8 text-center" style="margin-top: -30px; margin-bottom: 10px">
      <h2><b>PROJECT TITLE</b>
      </h2>
      <p>
        The work is supported in part by<br>
        <h4><font size="+1">SUPPORTERS</font></h4>
      </p>
    </div>
    <div class="d-none d-md-block col-md-2 text-center">
      <img src="/assets/grails.svg" width="100%;">
    </div>
  </div>
</div>
"""
            
            if (!FrontEndField.findByName("Web page banner")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Web page banner",
                                      htmlContent: htmlBanner).save()
                }
            }

            // ~~~~~~~~~~~~~~~~~~~~ Navigation

            // ---------- Web title
            if (!FrontEndField.findByName("Web title")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Web title",
                                      htmlContent: "PROJECT TITLE").save()
                }
            }
            
            // ---------- Home
            if (!FrontEndField.findByName("Nav: Home")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Nav: Home",
                                      htmlContent: "Home").save()
                }
            }

            // ---------- Publications
            if (!FrontEndField.findByName("Nav: Publications")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Nav: Publications",
                                      htmlContent: "Publications").save()
                }
            }

            // ---------- Data request
            if (!FrontEndField.findByName("Nav: Data requests")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Nav: Data requests",
                                      htmlContent: "Data requests").save()
                }
            }

            // ---------- My requests
            if (!FrontEndField.findByName("Nav: My requests")){
                FrontEndField.withTransaction{
                    new FrontEndField(name: "Nav: My requests",
                                      htmlContent: "My requests").save()
                }
            }


        } // IF DEVELOPMENT
        
    }
    def destroy = {
    }
}
