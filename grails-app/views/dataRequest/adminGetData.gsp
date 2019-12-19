<!DOCTYPE html>
<html>
  <g:set var="myTitle" value="Available samples" />
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'sampleData.label', default: 'SampleData')}" />
    <title>${myTitle}</title>
  </head>
  <body>
    <a href="#list-sampleData" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
      <div class="nav" role="navigation">
          <ul>
              <li><a class="home" href="${createLink(uri: '/admin/')}">Admin panel</a></li>
          </ul>
      </div>
      <div id="list-sampleData" class="content scaffold-list" role="main">
      <h1>${myTitle}</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>

      <!-- show public samples -->
      <g:form method="POST"
	      action="adminExportData"
      >
        <div class="fieldcontain">
          <label for="exposures">Samples (select none to download all available)</label>
          <g:select from="${rosalind.SampleData.list()}"
	            multiple="true"
	            name="listOfSamples"
	            optionKey="id"
          />

        </div>
        <div class="fieldcontain">
          <label for="exposures">Biomarkers (select none to download all available)</label>
          <g:select from="${rosalind.Biomarker.list()}"
	            multiple="true"
	            name="listOfBiomarkers"
	            optionKey="id"
          />

        </div>

	<!-- agreement and download -->
	<fieldset class="buttons">
          <g:submitButton name="getDataButton" class="save" id="getDataButton"
			  value="Submit" />
	</fieldset>
      </g:form>
    </div>


  </body>
</html>
