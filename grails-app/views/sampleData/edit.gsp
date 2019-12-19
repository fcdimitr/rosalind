<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'sampleData.label', default: 'SampleData')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-sampleData" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin/')}">Admin panel</a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-sampleData" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.sampleData}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.sampleData}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.sampleData}" method="PUT">
                <g:hiddenField name="version" value="${this.sampleData?.version}" />
                <fieldset class="form">
                  <f:all bean="sampleData"
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
		               notes" />
                </fieldset>
                <fieldset class="buttons">
                  <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
