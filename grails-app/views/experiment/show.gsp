<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'experiment.label', default: 'Experiment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-experiment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin/')}">Admin panel</a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-experiment" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="experiment" except="primaryData,secondaryData" />
            <!-- HARDCODED FOR THESE FILES -->
            <ol class="property-list experiment">
              <li class="fieldcontain">
                <span id="dataFile-label" class="property-label">
		  Data File
	        </span>
                <div class="property-value" aria-labelledby="dataFile-label">
		  
		  <a href="/experiment/downloadDataFile/${experiment.id}">
		    Open file
		  </a>
		  
	        </div>
              </li>
              
              <li class="fieldcontain">
                <span id="estimationFile-label" class="property-label">
		  Estimation File
	        </span>
                <div class="property-value" aria-labelledby="estimationFile-label">
		  
		  <a href="/experiment/downloadEstimationFile/${experiment.id}">
		    Open file
		  </a>
		  
	        </div>
              </li>
            </ol>
            <!-- END: HARDCODED -->

            <g:form resource="${this.experiment}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.experiment}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
