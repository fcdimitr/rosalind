<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'experiment.label', default: 'Experiment')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-experiment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin/')}">Admin panel</a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-experiment" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.experiment}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.experiment}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:uploadForm resource="${this.experiment}" method="PUT">
                <g:hiddenField name="version" value="${this.experiment?.version}" />
                <fieldset class="form">
                  <!-- HARCODED -->
                  <div class="fieldcontain">
                    <label for="primaryData_updated">Data File</label>
                    <input type="file" name="primaryData_updated" value="" id="primaryData_updated">
                  </div>
                  <div class="fieldcontain">
                    <label for="secondaryData_updated">Estimation File</label>
                    <input type="file" name="secondaryData_updated" value="" id="secondaryData_updated">
                  </div>
                  <!-- END: HARDCODED -->
                  <f:all bean="experiment" except="primaryData, secondaryData" />
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
