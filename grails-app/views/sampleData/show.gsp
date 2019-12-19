<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'sampleData.label', default: 'SampleData')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>
    <a href="#show-sampleData" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <sec:ifAllGranted roles="ROLE_ADMIN">
      <div class="nav" role="navigation">
        <ul>
          <li><a class="home" href="${createLink(uri: '/admin/')}">Admin panel</a></li>
          <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
          <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        </ul>
      </div>
    </sec:ifAllGranted>
    <div id="show-sampleData" class="content scaffold-show" role="main">
      <h1><g:message code="default.show.label" args="[entityName]" /></h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <f:display bean="sampleData"
 		 order="labID,
		       age,
		       gender,
		       race,
		       weightLb,
		       heightFt,
		       bmi,
		       bloodGroup,
		       rhFactor,
		       eduLevel,
		       homeState,
                       cohort,
                       exposures,
                       medications,
                       symptoms,
                       symptomsOnset,
		       treatmentNotes,
		       sampleType,
		       collected,
		       received,
		       provider,
		       isPublic,
                       notes"/>
      <sec:ifAllGranted roles="ROLE_ADMIN">
        <g:form resource="${this.sampleData}" method="DELETE">
          <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.sampleData}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
          </fieldset>
        </g:form>
      </sec:ifAllGranted>
    </div>
  </body>
</html>
