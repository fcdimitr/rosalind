<!doctype html>
<html lang="en" class="no-js">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
      <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
  </head>

  <body>

    <header class="header">

      <!-- <div id="inner-header" class="wrap cf"> -->
      ${raw(rosalind.FrontEndField.findByName("Web page banner").htmlContent)}

        <!-- </div> -->
      <!--       ALERT BAR JS -->
      <!-- <script src="https://alertbar.oit.duke.edu/alert.html" type="text/javascript"></script> -->

      
      <nav class="navbar navbar-expand-lg navbar-dark " style="background: #001a57;" role="navigation">
	<!-- <nav class="navbar navbar-expand-lg navbar-light bg-light"> -->
	<!-- <a class="navbar-brand" href="/">SAMPLES</a> -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a class="nav-link" href="/">${rosalind.FrontEndField.findByName("Nav: Home").htmlContent}</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/publication/showPublications">${rosalind.FrontEndField.findByName("Nav: Publications").htmlContent}</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/sampleData/showAvailable">${rosalind.FrontEndField.findByName("Nav: Data requests").htmlContent}</a>
            </li>
          </ul>
          <ul class="navbar-nav ml-auto">
            <!-- <g:pageProperty name="page.nav"/> -->
            <sec:ifLoggedIn>
	      <li class="nav-item">
		<a class="nav-link" href="/dataRequest/myRequests">${rosalind.FrontEndField.findByName("Nav: My requests").htmlContent}</a>
	      </li>
	    </sec:ifLoggedIn>
	    <sec:ifAllGranted roles="ROLE_ADMIN">
	      <li class="nav-item">
		<a class="nav-link" href="/admin">DB Management (admin)</a>
	      </li>
	    </sec:ifAllGranted>
            <sec:ifLoggedIn> <!-- logout button -->
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <sec:loggedInUserInfo field='username'/>
                </a>
                <div class="dropdown-menu" >
                  <g:form controller="logout">
                    <g:submitButton class="dropdown-item" name="Submit" value="Logout" />
                  </g:form>
                </div>
              </li>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn> <!-- login button -->
              <li class="nav-item">
                <a class="nav-link" href="/login/auth">Login</a>
              </li>
            </sec:ifNotLoggedIn>
          </ul>
        </div>
      </nav>
    </header>

    <!-- <div class="container-fluid no-padding" id="content" role="main"> -->
        <g:layoutBody/>
        <!-- </div> -->

    <footer>
      
      <div class="footer-copyright text-center py-3">© <g:formatDate format="yyyy" date="${new Date()}"/> Copyright &mdash;
        Developed by <a href="https://www.cs.duke.edu/people/faculty/28">Prof. Xiaobai Sun's</a> team,
        <a href="https://www.cs.duke.edu">Department of Computer
        Science, Duke University</a>
      </div>
      
    </footer>
    
    <div id="spinner" class="spinner" style="display:none;">
      <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>
    
  </body>
</html>
