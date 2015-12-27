<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page isELIgnored="false" %> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script src="Bootstrap/bootstrap.js" type="text/javascript"></script>
 <link href="Bootstrap/bootstrap.css" rel="stylesheet" type="text/css" />
 <script src="Bootstrap/jquery-2.1.4.js" type="text/javascript"></script>
 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<br/>

<div class="container">
<div class="panel panel-default">
<div class="row">
<div style="padding: 30px; left:-50px; text-align: center;">
<img alt="Image" src="<c:url value="img/LOGO.png"/>"/>
</div>

</div>

    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Se connecter</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">S'enregistrer</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="SignIn" method="post" style="display: block;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" value="Identifiant" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" value="Mot de passe">
									</div>
									
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-success" value="Valider">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="http://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>
												</div>
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" action="registration" method="post" style="display: none;">
									<div class="form-group">
									<label for="username">Identifiant</label>
										<input type="text" name="username" id="username" tabindex="1" class="form-control" value="Identifiant" value="">
									</div>
									
									<div class="form-group">
									<label for="password">mot de passe</label>
										<input type="password" name="password" id="password" tabindex="2" class="form-control" value="mot de passe">
									</div>
									<div class="form-group">
									<label for="confirm-password">Confirmer mot de passe</label>
										<input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" value="">
									</div>
									<div class="form-group">
									<label for="email">E-mail</label>
										<input type="text" name="email" id="email" tabindex="1" class="form-control" value="E-mail" value="">
									</div>
									<div class="form-group">
									<label for="Téléphone">Numéro</label>
										<input type="text" name="numero" id="numero" tabindex="1" class="form-control" value="Telephone" value="">
									</div>
									<div class="form-group">
		<label for="myButtons">Type de compte :</label>						
<div id="myButtons" class="btn-group"  data-toggle="buttons">
  <label class="btn btn-primary">
    <input type="radio" name="options" id="option1"> Elève
  </label>
  <label class="btn btn-primary">
    <input type="radio" name="options" id="option2" > Entreprise
  </label>      
</div>

<div id="register-eleve" style="display: none;">
									<div class="form-group">
									<label for="username">age</label>
										<input type="text" name="age" id="age" tabindex="1" class="form-control" >
									</div>
									</div>
									
									<div id="register-entreprise" style="display: none;">
									<div class="form-group">
									<label for="username">Adresse siège social</label>
										<input type="text" name="adresseEntreprise" id="adresseEntreprise" tabindex="1" class="form-control">
									</div>
									</div>
									
									















									</div>
									
								
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-default" value="S'inscrire">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	<script type=text/javascript>

	$(function() {


		$('#option1').parent().on("click", function () {
			document.getElementById("register-eleve").style.display="block";
			document.getElementById("register-entreprise").style.display="none";
			  
			});
		$('#option2').parent().on("click", function () {
			document.getElementById("register-entreprise").style.display = "block";
			document.getElementById("register-eleve").style.display = "none";
			  
			});

		

	    $('#login-form-link').click(function(e) {
			$("#login-form").delay(100).fadeIn(100);
	 		$("#register-form").fadeOut(100);
			$('#register-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});
		$('#register-form-link').click(function(e) {
			$("#register-form").delay(100).fadeIn(100);
	 		$("#login-form").fadeOut(100);
			$('#login-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});

	});


	
	</script>


</body>
</html>