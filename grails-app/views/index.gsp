<!doctype html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>${rosalind.FrontEndField.findByName("Web title").htmlContent}</title>
  </head>
  <body>

    <div class="row">
      <div class="col-lg-5">
        <h1>${rosalind.FrontEndField.findByName("PI name").htmlContent}</h1>
        
        ${raw(rosalind.FrontEndField.findByName("PI info").htmlContent)}
        <!-- <div class="col"> -->
        
      </div>
      <div class="col-lg-7">
        <h1>${rosalind.FrontEndField.findByName("Title: Project description").htmlContent}</h1>
        <p>
          ${raw(rosalind.FrontEndField.findByName("Project description").htmlContent)}
	</p>
        <h1>${rosalind.FrontEndField.findByName("Title: Research interests").htmlContent}</h1>
        <p>
          ${raw(rosalind.FrontEndField.findByName("Research interests").htmlContent)}
        </p>
      </div>
    </div>
    
    <div class="row">
      <div class="col-lg-5">
        <h1>${rosalind.FrontEndField.findByName("Title: Related links").htmlContent}</h1>
        ${raw(rosalind.FrontEndField.findByName("Related links").htmlContent)}
      </div>
      <div class="col-lg-7">
        <h1>${rosalind.FrontEndField.findByName("Title: Collaborators").htmlContent}</h1>
        ${raw(rosalind.FrontEndField.findByName("Collaborators").htmlContent)}
      </div>

    </div>
    
    

    <div class="row">
      <div class="col">
        <h1>${rosalind.FrontEndField.findByName("Title: Publications").htmlContent}</h1>

        <%
        def allPublications = rosalind.Publication.list()

	def publicationTypes = rosalind.PublicationType.list()

	def publicationListByType = []
	for (t in publicationTypes) {
	  publicationListByType << (allPublications
	    .findAll { it.publicationType == t }.
	    sort { a,b -> b.year <=> a.year })
	}
        %>
        
        <g:each var="t" status="i" in="${publicationTypes}">
	  <g:set var="publicationList" value="${publicationListByType[i]}" />
	  <g:if test="${(!(publicationList.isEmpty()))}">
	    <h2>${t}s</h2>
            <ol class="property-list">
	      <g:each var="p" in="${publicationList}">
                <li class="fieldcontain">
		  ${raw(p.bibliographyEntry)}
		  <g:if test="${p.doi != null}">
		    <a href="https://doi.org/${p.doi}">DOI: ${p.doi}</a>
		  </g:if>
		  <g:if test="${p.pdfFile.size() > 0 && p.isPublic}">
                    <g:link controller="publication" action="downloadPdfFile" id="${p.id}">(PDF)</g:link>
		  </g:if>
                </li>
	      </g:each>
            </ol>
	  </g:if>
        </g:each>
        
      </div>
    </div>

    <div class="row">
      <h1>Data archival & dissemination</h1>
    </div>
    <div class="row">
      <div class="col">
        <p>
          <ul>
            <li>Functionality</li>
            <ul>
              <li>User authentication</li>
              <li>Data selection</li>
              <li><a href="sampleData/showAvailable">Data request</a></li>
              <li>Request notification</li>
              <li>Access permission</li>
              <li>User notification</li>
              <li><a href="dataRequest/myRequests">Data compilation</a></li>
              <li>Activity logging</li>
              <li>De-identification of patient/donor information (data privacy)
      </li>
              <li>Safeguarding maps for ID conversion and integration</li>
            </ul>
          </ul>
        </p>
      </div>
      <div class="col">
        <ul>
          <li>Data architecture</li>
	  <ul>
	    <li>Data modeling</li>
	    <li>Document and record management</li>
	    <li>Data access</li>
            <li>Extraction</li>
            <li>Integration</li>
	    <li>Create, Read, Update, Delete (CRUD)</li>
	    <li>Data integrity</li>
	    <li>Security and privacy</li>
	    <li>Backup and restore</li>
          </ul>
        </ul>
      </div>
      <div class="col">
        <ul>
          <li>Control of full data cycle</li>
          <ul>
            <li>Plan</li>
            <li>Collect</li>
            <li>Assure</li>
            <li>Describe</li>
            <li>Archive</li>
            <li>Analyze & discover</li>
            <li>Integrate</li>
            <li>Disseminate</li>
          </ul>
        </ul>
      </div>
    </div>

    <div class="row">
      <div class="col">
      </div>
    </div>
    
  </body>
</html>
