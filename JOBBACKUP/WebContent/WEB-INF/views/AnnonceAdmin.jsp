<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mes annonces</title>


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
<div class="page-header">
  <h1>JOBEISTI : Admin <small>Gérer les annonces</small></h1>
</div>


<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
    $("#inserer").hide();
});
</script>



<script  type=text/javascript>
$(function() {
$("#ajouter").on('click', (function(){
   

	$("#inserer").toggle();
}));




$("#canceller").on('click', (function(){
	   

	$("#inserer").hide();
}));



});


</script>








	<c:if test="${not empty listeOffres}">
	
	<div class="panel panel-primary" style="width:1000px; margin:10px ">
	<div class="panel panel-heading">Annonces <div class="pull-right"> 
	
	
	
	
	
	
	
	
	
	      </div>   </div>
	
	
		
		<c:forEach var="i" begin="0" end="${(fn:length(listeOffres))-1}" step="1">
		<div class="panel panel-body" >
		<div style="display: flex; align-content:left;  ">
			<h4><b><c:out value="${listeOffres.get(i).getTitreoffre()}"/></b></h4>  <c:if test="${not empty nbValide}"> &nbsp;
			<c:if test="${i<nbValide}">
				<i class="glyphicon glyphicon-ok-sign" style="color:green" data-toggle="tooltip" title="Statut de l'offre : Validée"   >          </i>
			</c:if>
			<c:if test="${i>=nbValide}">
				<i class="glyphicon glyphicon-repeat gly-spin" style="color:black; " data-toggle="tooltip" title="Statut de l'offre : En Attente"   >          </i>
			</c:if>
			</c:if>
	
	</div>
			<h4><c:out value="${listeOffres.get(i).getContenu()}"/></h4>
			
			<div style="display: flex; align-content:left;  ">
		<div style="display: flex; align-content:left;  "><i class="glyphicon glyphicon-calendar" style="color:green" data-toggle="tooltip" title="Date de publication"></i>	&nbsp; <c:out value="${listeOffres.get(i).getDatepublication()}"/></div>
		&nbsp; &nbsp; &nbsp;
		<div style="display: flex; align-content:left "><i class="glyphicon glyphicon-calendar" style="color:red" data-toggle="tooltip" title="Date de fin"></i> &nbsp;	<c:out value="${listeOffres.get(i).getDureeoffre()}"/></div>
			</div>
			<br/>
			<div style="display: flex; align-content:left;  ">
			<c:if test="${i>=nbValide}">
			<form method="post" action="validAnnonceController" ><input type="hidden" name="valeur" value="${listeOffres.get(i).getIdoffre()}"/><input class="btn btn-primary" type="submit" value="Valider l'offre"/></form>
			&nbsp;
			</c:if>
			<form method="post" action="refusAnnonceController" ><input type="hidden" name="valeur" value="${listeOffres.get(i).getIdoffre()}"/><input class="btn btn-warning" type="submit" value="Refuser l'offre"/></form>
			</div>
			
			</div>
	 	</c:forEach> 
	 	
	 	</div>
	 	
	 	
	 	
	</c:if>
	<c:if test="${empty listeOffres }">
	
		<h3 style="text-align: center">Il n'y a pas d'annonce enregistrée</h3>
	</c:if>
	
	</div>
	
	
	</div>
	</div>
</body>
</html>