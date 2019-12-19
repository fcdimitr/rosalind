<div>
  <table >
    <thead>
      <tr>
        <g:each in="${domainProperties}" var="p" status="i">
          <g:sortableColumn property="${p.property}" title="${p.label}" />
        </g:each>
      </tr>
    </thead>
    <tbody>
      <g:each in="${collection}" var="bean" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
          <g:each in="${domainProperties}" var="p" status="j">
	    <g:if test="${p.name=="name"}">
              <td><g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${p.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link></td>
            </g:if>
            <g:elseif test="${p.name=="image"}">
              <td><img src="/image/download?name=${bean.name}" width='200' /></td>
            </g:elseif>
          </g:each>
        </tr>
      </g:each>
    </tbody>
  </table>
</div>
