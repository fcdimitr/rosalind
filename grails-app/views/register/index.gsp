<html>
  <head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title>Register</title>
  </head>

  <body>
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Register Here</h5>
            <g:if test='${flash.message}'>
              <div class="alert alert-danger" role="alert">${flash.message}</div>
            </g:if>
            <form class="form-signin" action="register" method="POST" id="loginForm" autocomplete="off">
              
              <div class="form-group">
                <label for="username">Username</label>
                <input type="text" placeholder="Your username" class="form-control" name="username" id="username" autocapitalize="none"/>
              </div>

              <div class="form-group">
                <label for="password">Password</label>
                <input type="password" placeholder="Your password" class="form-control" name="password" id="password"/>
              </div>

              <div class="form-group">
                <label for="password">Re-Enter Password</label>
                <input type="password" placeholder="Re-enter password" class="form-control" name="repassword" id="repassword"/>
              </div>
              
              <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" placeholder="Enter email" class="form-control" name="email" id="email" aria-describedby="emailHelp">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
              </div>
              
              <div class="form-group">
                <label for="affiliation">Affiliation</label>
                <input type="text" placeholder="Your affiliation" class="form-control" name="affiliation" id="affiliation" autocapitalize="none"/>
              </div>

              <button id="submit" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Register</button>
              <hr class="my-4">
              <p>Already have an account? <g:link controller="login" action="auth">Login</g:link></p>
            </form>
          </div>
        </div>
      </div>
    </div>
    <script type="text/javascript">
     document.addEventListener("DOMContentLoaded", function(event) {
       document.forms['loginForm'].elements['username'].focus();
     });
    </script>
  </body>
</html>
