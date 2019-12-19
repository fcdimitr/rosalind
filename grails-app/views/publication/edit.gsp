<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'publication.label', default: 'Publication')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-publication" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin/')}">Admin panel</a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-publication" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.publication}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.publication}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:uploadForm resource="${this.publication}" method="PUT">
              <g:hiddenField name="version" value="${this.publication?.version}" />
              <fieldset class="form">
                <!-- HARCODED -->
                <div class="fieldcontain">
                  <label for="pdfFile_updated">PDF File</label>
                  <input type="file" name="pdfFile_updated" value="" id="pdfFile_updated">
                </div>
                <!-- END: HARDCODED -->
                <f:all bean="publication" except="pdfFile" />
              </fieldset>
              <fieldset class="buttons">
                <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
              </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
