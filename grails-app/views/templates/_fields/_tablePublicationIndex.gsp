<table>
  <thead>
    <tr>
      <th></th>
      <g:each in="${domainProperties}" var="p" status="i">
        <g:if test="${p.name!="pdfFile"}">
	  <g:sortableColumn property="${p.property}" title="${p.label}" />
        </g:if>
      </g:each>
    </tr>
  </thead>
  <tbody>
    <g:each in="${collection}" var="bean" status="i">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
        <td><g:link method="GET" resource="${bean}">Show details</g:link></td>
	<g:each in="${domainProperties}" var="p" status="j">
          <g:if test="${p.name=="pdfFile"}">
	    <!-- <td><g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${p.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link></td> -->
	  </g:if>
	  <g:else>
	    <td><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
	  </g:else>
	</g:each>
      </tr>
    </g:each>
  </tbody>
</table>
