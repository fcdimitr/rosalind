<!DOCTYPE html>
<html>
  <g:set var="myTitle" value="Select Data Records to Request" />
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'sampleData.label', default: 'SampleData')}" />
    <title>${myTitle}</title>
  </head>
  <body>
    <a href="#list-sampleData" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div id="list-sampleData" class="content scaffold-list" role="main">
      <h1>${myTitle}</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>

      <!-- show public samples -->
      <g:form method="POST"
	      action="submitDataRequest"
	      onsubmit="if(document.getElementById('agreeChk').checked) {return true; } else { alert('Please indicate that you have read and agree to the Terms and Conditions and Privacy Policy'); return false; }">
	<f:table collection="${sampleDataList}"
		 maxProperties="0"
		 template="tableCheckbox"
		 myProperty="Template:view/templates/_fields/_tableCheckbox.gsp"
	/>
        <div class="fieldcontain">
          <label for="exposures">Biomarkers (select none to download all available)</label>
          <g:select from="${rosalind.Biomarker.findAllWhere(isPublic: true)}"
	            multiple="true"
	            name="listOfBiomarkers"
	            optionKey="id"
          />

        </div>


	<div class="col-6">
          <span class='required-indicator'>*</span>
	  Please provide a brief description of the data use for your project/purpose.
          <textarea name="purpose" rows="10" id="minle" maxlength="500" required cols="40"></textarea>
	</div>
	<!-- agreement and download -->
	<fieldset class="buttons">
          <span class='required-indicator'>*</span>
          <a href="#exampleModalCenter" data-toggle="modal"
	     data-target="#exampleModalCenter">
	    Click here to read and agree to the Terms and
	    Conditions and Privacy Policy
          </a>
          <!-- <input type="checkbox" name="checkbox"
	       value="check" disabled="" id="agreeChk" /> -->
	  
          <g:submitButton name="submitDataRequest" class="btn btn-lg btn-secondary" disabled="" id="downloadButton"
			  value="${message(
				 code: 'default.button.download.label', 
				 default: 'Request data for selected samples'
				 )}" />
	</fieldset>
      </g:form>
    </div>

    <!-- Modal: Terms and conditions aggrement -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
	<div class="modal-content">
	  <div class="modal-header">
	    <h5 class="modal-title" id="exampleModalCenterTitle">Terms and Conditions Aggrement</h5>
	    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	      <span aria-hidden="true">&times;</span>
	    </button>
	  </div>
	  <div class="modal-body">
	    ${raw(textAgreement)}
	  </div>
	  <div class="modal-footer">
	    <button type="button" class="btn btn-default" data-dismiss="modal" id="disagreeBtn">Disagree</button>
	    <button type="button" class="btn btn-primary" data-dismiss="modal" id="agreeBtn">Agree</button>
	  </div>
	</div>
      </div>
    </div>

  </body>
</html>
