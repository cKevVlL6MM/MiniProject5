<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mot De Passe Oublié</title>



 <script src="Bootstrap/jquery-2.1.4.js" type="text/javascript"></script>
 <script src="Bootstrap/creative.js" type="text/javascript"></script>
  <script src="Bootstrap/jquery.fittext.js" type="text/javascript"></script>
   <script src="Bootstrap/jquery.easing.min.js" type="text/javascript"></script>
   <script src="Bootstrap/wow.min.js" type="text/javascript"></script>
    <link href="Bootstrap/2-col-portfolio.css" rel="stylesheet">
     <link href="Bootstrap/creative.css" rel="stylesheet">
      <link href="Bootstrap/animate.min.css" rel="stylesheet">
        <link href="Bootstrap/bootstrap-theme.css" rel="stylesheet">
           <link href="Bootstrap/font-awesome.min.css" rel="stylesheet">
      <link href="Bootstrap/font-awesome.min.css" rel="stylesheet">
     <script src="Bootstrap/bootstrap.js" type="text/javascript"></script>
 <link href="Bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
 <link href="Bootstrap/bootstrap.css" rel="stylesheet" type="text/css" />
 <link href="Bootstrap/jquery-ui.css" rel="stylesheet">


</head>
<body>



<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header pull">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand pull-right" href="Accueil">Accueil</a>
            </div>
           
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav pull-right">
                    
                    <li>
                        <label style="color:white; margin-top:12px">JOB<span style="color: SteelBlue ">EISTI</span>  </label>
                    </li>
                   <li>
               &nbsp;
                       <button type="button" style="margin-top:10px;font-size: 20px; color:white; background-color:transparent; border:none"  class="glyphicon glyphicon-align-justify dropdown-toggle" data-toggle="dropdown" title="Mes options" >
                       </button>  <ul class="dropdown-menu">
    <li>
    
    <div style="display: flex; align-content:left ">
       <i class="glyphicon glyphicon-erase"></i>&nbsp;
    <a href="motdepasse" style="color:black;">Reinitialiser mot de passe</a>
    </div>
    </li>
    
    <li>
    
     <div style="display: flex; align-content:left ">
    <i class="glyphicon glyphicon-log-out" style="color:red"></i>&nbsp;
    <a href="deconnexion" style="color:black;">Deconnexion</a>
      </div> 
    </li>
                     
            </ul>            
                        
                       
                        


                    </li>
                </ul>
            </div>
            
        </div>
        
    </nav>





<div class="container">
<div class="panel panel-default">

	<div class="panel-heading">Reinitialiser le mot de passe ?</div> 
	
	<div class="panel-body">
	
	<label for="email">Entrez votre adresse mail, un nouveau mot de passe vous y sera envoyé</label> 
	<form method="post" action="motdepasse"><input type="text" name="email"/><br/>
	
	<button class="btn btn-danger" type="submit" value="Reinitialiser"/>Reinitialiser</form>
	
	</div>
	</div>
	</div>
</body>
</html>