<!DOCTYPE html>
<html>
  <g:set var="myTitle" value="Relevant Publications" />
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'publication.label', default: 'Publication')}" />
    <title>${myTitle}</title>
  </head>
  <body>
    <!-- <a href="#list-publication" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a> -->
    <div id="list-publication" class="content scaffold-list" role="main">
      <h1>${myTitle}</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>

      <!-- render each publication list (grouped by type) -->

      <%
      def allPublications = rosalind.Publication.list()

      def publicationTypes = rosalind.PublicationType.list()

      def publicationListByType = []
      for (t in publicationTypes) {
	publicationListByType << (allPublications
	  .findAll { it.publicationType == t }.
	  sort { a,b -> b.year <=> a.year })
      }
      %>
      
      <g:each var="t" status="i" in="${publicationTypes}">
	<g:set var="publicationList" value="${publicationListByType[i]}" />
	<g:if test="${(!(publicationList.isEmpty()))}">
	  <h2>${t}s</h2>
          <ol class="property-list">
	    <g:each var="p" in="${publicationList}">
              <li class="fieldcontain">
		${raw(p.bibliographyEntry)}
		<g:if test="${p.doi != null}">
		  <a href="https://doi.org/${p.doi}">DOI: ${p.doi}</a>
		</g:if>
		<g:if test="${p.pdfFile.size() > 0 && p.isPublic}">
                  <g:link action="downloadPdfFile" id="${p.id}">(PDF)</g:link>
		</g:if>
              </li>
	    </g:each>
          </ol>
	</g:if>
      </g:each>

      <!-- end publication list rendering -->
      
    </div>
  </body>
</html>
