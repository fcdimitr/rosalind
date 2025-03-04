<div>
  <table class="table-responsive">
    <thead>
      <tr>
        <g:each in="${domainProperties}" var="p" status="i">
          <g:if test="${p.type.toString() == "interface java.util.Set"}">
            <th class="admin-list">
              ${p.label}
            </th>
          </g:if>
          <g:else>
            <g:sortableColumn property="${p.property}" title="${p.label}" class="admin-list" />
          </g:else>
        </g:each>
      </tr>
    </thead>
    <tbody>
      <g:each in="${collection}" var="bean" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
          <g:each in="${domainProperties}" var="p" status="j">
            <g:if test="${j==0}">
              <td><g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${p.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link></td>
            </g:if>
            <g:else>
              <td><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
            </g:else>
          </g:each>
        </tr>
      </g:each>
    </tbody>
  </table>
</div>
