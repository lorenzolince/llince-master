<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script src="resources/js/jquery.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="mobile-web-app-capable" content="yes">
  <title>Login</title>
  <!-- Latest compiled and minified CSS -->
  <link href="resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
  <!-- Optional theme -->
  <link href="resources/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet">
  <link href="resources/css/styles.css" type="text/css" rel="stylesheet">
         <script type="text/javascript">
var host=window.location.origin;
  function login(){
      
    const form = document.getElementById('form');
     var parce ={};
     for (var i = 0; i < 2; i++){
    parce[form[i]['name']] = form[i]['value'];
  }
   var data = JSON.stringify(parce);  
   
        
     
       $.ajax({
			type : "POST",
			contentType : "application/json; charset=utf-8", 
                        crossDomain: true,
			url : host+"/llince-rest-spring-security/rest/login",
                        data : data,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
  function display(datos){
     
  //document.acceder.submit();
if(datos.loggedIn){
       var str = JSON.stringify(datos);
  var jsonPretty = JSON.stringify(JSON.parse(str),null,2);  
  
     var loggedIn = datos.loggedIn;
     document.getElementById('loggedIn').value=loggedIn;
      var username = datos.username;
     document.getElementById('username').value=username;
      var token = datos.token;
     document.getElementById('token').value=token;
      
     var idUser = datos.userDetail.idUser;
     document.getElementById('userDetail.idUser').value=idUser;
     
      var lastName = datos.userDetail.lastName;
     document.getElementById('userDetail.lastName').value=lastName;
      var firstName = datos.userDetail.firstName;
      
     document.getElementById('userDetail.firstName').value=firstName;
     
      var idComapanie = datos.userDetail.idComapanie;
     document.getElementById('userDetail.idComapanie').value=idComapanie;
      var companieName = datos.userDetail.companieName;
     document.getElementById('userDetail.companieName').value=companieName;
    // alert(jsonPretty);
 document.form.submit();   
  } else {
   alert("FALLIDO");   
  }
     }
     }
</script>

</head>

<body>
  <div class="content mtef-body">
    <div class="row">
      <div class="col-sm-8 col-sm-offset-2">
        <img src="resources/images/login2.png" class="img-fluid mx-auto logo" alt="logo">
      </div>
    </div>
    <br>
    <div class="panel panel-primary">
      <div class="panel-heading panel-header-bg">Iniciar Sesión</div>
      <div class="panel-body">
        <form name="form" id="form" action="Welcome" class="form" method="POST" >
          <div class="row">
            <div class="col-sm"><br>
              <br></div>
          </div>
          <div class="row">
            <div class="col-sm-10 col-sm-offset-1 input-group">
              <div class="input-group-addon div-icon-user"></div>
              <input name="j_username" type="text" class="form-control" id="j_username" aria-describedby="j_username" placeholder="Usuario">
            </div>

          </div>
          <div class="row">
            <div class="col-sm"><br>
              <br></div>
          </div>
          <div class="row">
            <div class="col-sm-10 col-sm-offset-1 input-group">
              <div class="input-group-addon div-icon-pwd"></div>
              <input type="password" name="j_password" class="form-control" id="j_password" placeholder="contraseña">
            </div>
          </div>
          <div class="row">
            <div class="col-sm"><br>
              <br></div>
          </div>
          <div class="row">
            <div class="col-sm-10 col-sm-offset-1 rm-ph">
              <select class="form-control">
                <option>Seleccionar Idioma</option>
                <option>Español</option>
                <option>Portugués</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-10 col-sm-offset-9">
              <br>
              <button type="button"  onclick="login();" class="btn btn-login btn-primary">Ingresar</button>
			   <input type="hidden" name="loggedIn" id="loggedIn"/>
      <input type="hidden" name="username" id="username"/>
      <input type="hidden" name="token" id="token"/>
      <input type="hidden" name="userDetail.idUser" id="userDetail.idUser"/>
      <input type="hidden" name="userDetail.lastName" id="userDetail.lastName"/>
      <input type="hidden" name="userDetail.firstName" id="userDetail.firstName"/>
      <input type="hidden" name="userDetail.idComapanie" id="userDetail.idComapanie"/>
      <input type="hidden" name="userDetail.companieName" id="userDetail.companieName"/>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-11 col-sm-offset-1">
              <br>
              <a>Olvido su contraseña</a>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>


</body></html>