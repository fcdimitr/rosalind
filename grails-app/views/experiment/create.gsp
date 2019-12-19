<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'experiment.label', default: 'Experiment')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-experiment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin/')}">Admin panel</a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-experiment" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
            <g:uploadForm resource="${this.experiment}" method="POST">
              <fieldset class="form">
                <f:all bean="experiment" 
                       order="primaryData,primaryNotes,
                             secondaryData,secondaryNotes,
                             method" />
                <div class="fieldcontain required">
                  <label for="finalData">Final Data
                    <span class="required-indicator">*</span>
                  </label><input type="file" name="finalData" value="" required="" id="finalData">
                </div>
                <div class="fieldcontain">
                  <label for="overwrite">Overwrite?</label>
                  <input type="hidden" name="_overwrite">
                  <input type="checkbox" name="overwrite" id="overwrite">
                </div>
              </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
