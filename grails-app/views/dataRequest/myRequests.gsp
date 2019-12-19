<!DOCTYPE html>
<html>
  <g:set var="myTitle" value="Data requests" />
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'dataRequest.label', default: 'DataRequest')}" />
    <title>${myTitle}</title>
  </head>
  <body>
    <a href="#list-dataRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div id="list-dataRequest" class="content scaffold-list" role="main">
      <h1>${myTitle}</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>

      <!-- show requests -->
      <g:form method="GET"
	      action="exportSampleData">
	<f:table collection="${dataRequestList}"
		 order="sampleDataList, requestStatus"
		 template="tableDataRequest"
		 myProperty="Template:view/templates/_fields/_tableDataRequest.gsp"
	/>
	<fieldset class="buttons">
	  <g:submitButton name="exportSampleData"
			  class="btn btn-lg btn-secondary"
			  id="downloadButton"
			  value="${message(
				 code: 'default.button.download.label',
				 default: 'Download selected'
				 )}"
	  />
	</fieldset>
      </g:form>
      <!-- end show requests -->

      <div class="pagination">
        <g:paginate total="${dataRequestCount ?: 0}" />
      </div>
    </div>
  </body>
</html>
