<table>
    <thead>
	<tr>
	    <g:each in="${domainProperties}" var="p" status="i">
		<g:sortableColumn property="${p.property}" title="${p.label}" />
	    </g:each>
	    <th>Download</th>
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
		<td>
		    <g:if test="${bean.requestStatus.toString() == "APPROVED"}">
			<input type="radio" name="downloadId" value="${bean.id}"/>
		    </g:if>
		    <g:else>
			<input type="radio" name="downloadId" value="${bean.id}"
			       disabled="true"/>
		    </g:else>
		</td>
	    </tr>
	</g:each>
    </tbody>
</table>
