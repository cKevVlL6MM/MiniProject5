<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Telecharger Documents</title>

<script src="Bootstrap/jquery-2.1.4.js" type="text/javascript"></script>
 <script src="Bootstrap/creative.js" type="text/javascript"></script>
  <script src="Bootstrap/jquery.fittext.js" type="text/javascript"></script>
   <script src="Bootstrap/jquery.easing.js" type="text/javascript"></script>
   <script src="Bootstrap/wow.min.js" type="text/javascript"></script>
    <link href="Bootstrap/2-col-portfolio.css" rel="stylesheet">
     <link href="Bootstrap/creative.css" rel="stylesheet">
      <link href="Bootstrap/animate.min.css" rel="stylesheet">
        <link href="Bootstrap/bootstrap-theme.css" rel="stylesheet">
           <link href="Bootstrap/font-awesome.min.css" rel="stylesheet">
      <link href="Bootstrap/font-awesome.min.css" rel="stylesheet">
     <script src="Bootstrap/bootstrap.js" type="text/javascript"></script>
 <link href="Bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />







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
                       </button> <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
						    <li style="height: 30px;">
						    
						    <div style="display: flex; margin:10px " class="" >
						        <i class="glyphicon glyphicon-erase"></i>&nbsp;
						    	<a href="motdepasse" style="color:black;font-family: Helvetica Neue,Helvetica,Arial,sans-serif; margin-left:10px">Reinitialiser mot de passe</a>
						    </div>
						    </li>
						    
						    <li style="height: 30px; text-align: center; vertical-align:middle">
						    
						     <div style="display: flex; margin:10px" class="">
							    <i class="glyphicon glyphicon-log-out" style="color:red"></i>&nbsp;
							    <a href="deconnexion" style="color:black; font-family: Helvetica Neue,Helvetica,Arial,sans-serif; margin-left:10px">Deconnexion</a>
						      </div> 
						    </li>
				                     
				       </ul>            
                        
                       
                        


                    </li>
                </ul>
            </div>
            
        </div>
        
    </nav>







<div class="container">
<c:if test="${empty nomsFichiers}">
<div class="alert alert-warning" role="alert"> Aucun document disponible</div>
</c:if>


<c:if test="${not empty nomsFichiers}">

<c:forEach var="i" begin="0" end="${(fn:length(nomsFichiers))-1}" step="1">
<div class="panel panel-primary">

<div class="panel-heading"> <h4><b><c:out value="${nomsFichiers.get(i)}"/></b></h4></div>
<div class="panel-body">
<form method="post" action="documentDownload" ><input type="hidden" name="string" value="${nomsFichiers.get(i)}"/><button class="btn btn-primary" type="submit" >Telecharger</form>
</div>
</div>
</c:forEach>
</c:if>
</div>
</body>
</html>