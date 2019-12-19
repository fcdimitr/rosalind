<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'frontEndField.label', default: 'FrontEndField')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-frontEndField" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin/')}">Admin panel</a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-frontEndField" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.frontEndField}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.frontEndField}" var="error">
                  <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <h2>Example template</h2>
            <xmp>
              Add content here <b>bold</b> <i>italic</i> <u>underline</u> <a href="https://www.google.com">link</a>
              <ul>
                <li>Item 1</li>
                <li>Item 2</li>
              </ul>
              <ol>
                <li>Item 1</li>
                <li>Item 2</li>
              </ol>
            </xmp>
            <g:form resource="${this.frontEndField}" method="PUT">
                <g:hiddenField name="version" value="${this.frontEndField?.version}" />
                <fieldset class="form">
                    <f:all bean="frontEndField" except="name" />
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
