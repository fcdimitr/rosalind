<ol class="property-list ${domainClass.decapitalizedName}">
    <g:each in="${domainProperties}" var="p">
        <li class="fieldcontain">
            <span id="${p.name}-label" class="property-label">
		<g:message code="${domainClass.decapitalizedName}.${p.name}.label"
			   default="${p.defaultLabel}" />
	    </span>
            <div class="property-value" aria-labelledby="${p.name}-label">
		<g:if test="${p.type.equals(byte[])}">
		    <g:link class="edit" action="downloadFile"
			    resource="${objbuf}">
			Open file
		    </g:link>
		</g:if>
		<g:else>
		    ${body(p)}
		</g:else>
	    </div>
        </li>
    </g:each>
</ol>
