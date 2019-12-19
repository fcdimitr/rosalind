<!DOCTYPE html>
<html>
    <g:set var="myTitle" value="Publications" />
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'publication.label', default: 'Publication')}" />
        <title>${myTitle}</title>
    </head>
    <body>
        <a href="#list-publication" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin/')}">Admin panel</a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-publication" class="content scaffold-list" role="main">
            <h1>${myTitle}</h1>
            <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${publicationList}"
                     template="tablePublicationIndex"
		     myProperty="Template:view/templates/_fields/_tablePublicationIndex.gsp" />

            <div class="pagination">
                <g:paginate total="${publicationCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
