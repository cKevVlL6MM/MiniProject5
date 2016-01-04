<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Vos annonces</title>
		
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
 <link href="Bootstrap/bootstrap.css" rel="stylesheet" type="text/css" />
 

  

	</head>
	<c:import url="header.jsp"/>
	<body style="background-color: rgba(173, 216, 230, 0.19);">
	
<!-- header -->
		

		<div class="container" style="margin-top: -500px;">
			<div class="row" style="border-right: 5px solid rgba(17,105,142,1); ">

<!-- 			right panel -->
				<aside class="col-xs-3" style="background-color:rgba(17,105,142,1); border-left:lightgrey;">
					<form action="annonces" method="post">
						<h3 style="color:white;">Critères de Recherche</h3>
						<hr>
						<div class="content">
						

							<div class="critere-recherche">
								<!-- type de contrat -->
								<label for="idtypecontrat" style="color:white;">Contrat</label>
								<select class="form-control" id="idtypecontrat" name="idtypecontrat">
									<option value="-1"></option>
									<option value="5">Stage</option>
									<option value="3">Alternance</option>
									<option value="4">VIE</option>
									<option value="1">CDD</option>
									<option value="2">CDI</option>
									<option value="6">Bénévolat</option>
								</select>
							</div>
							
							<br/>

							<div class="critere-recherche">
								<!-- secteur -->
								<label for="idtypesecteur" style="color:white;">Secteur</label>
								<select id="idtypesecteur" name="idtypesecteur" class="form-control">
									<option value="-1"></option>
									<option value="1">Informatique</option>
									<option value="2">Aéronautique</option>
									<option value="3">Mécanique</option>
									<option value="4">Agriculture</option>
									<option value="5">Automobile</option>
									<option value="6">Finance</option>
									<option value="7">Assurance</option>
									<option value="8">Alimenatation</option>
									<option value="9">Design/Architecture</option>
									<option value="10">Autres</option>
								</select>
							</div>

							<br/>

							
							<div class="critere-recherche">
								<!-- compétences requises -->
								<label for="idniveauminimum" style="color:white;">Niveau minimum requis</label>
								<select class="form-control" id="idniveauminimum" name="idniveauminimum">
									<option value="-1"></option>
									<option value="1">Développement web</option>
									<option value="2">Développement logiciel</option>
									<option value="3">Développement android/Ios/Windows phone</option>
									<option value="4">Test et vérifications</option>
									<option value="5">Analyse</option>
									<option value="6">Systèmes et Réseaux</option>
									<option value="7">Management/Gestion de projets</option>
									<option value="8">Aucune compétence requise</option>
									<option value="9">Autres</option>
								</select>
							</div>

							<br/>
							
							
							
							<div class="critere-recherche">
								<!-- date de création -->
								<label for="datepublication" style="color:white;">Date de création</label>
								<div class="container">
								    <div class="row">
								        <div style="width:250px">
								        	<div class="form-group">
								           <!--   <div class='input-group date' id='datetimepicker1'>
								                    <input type='text' class="form-control" name="datepublication" id="datepublication" />
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span>
								                </div> -->    
								            </div>
								            <script type="text/javascript">
									            $(function () {
									                $('#datetimepicker1').datetimepicker();
									            });
								       		 </script>
								        </div>
								    </div>
								</div>
							</div>
							<br/>


							<div class="critere-recherche">
								<!-- date de création -->
								<label for="dureeoffre" style="color:white;">Date de fin</label>
								<div class="container">
								    <div class="row">
								        <div style="width:250px">
								        	<div class="form-group">
								             <!--      <div class='input-group date' id='datetimepicker1'>
								                    <input type='text' class="form-control" name="dureeoffre" id="dureeoffre"/>
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span> 
								                </div>-->
								            </div>
								            <script type="text/javascript">
									            $(function () {
									                $('#datetimepicker1').datetimepicker();
									            });
								       		 </script>
								        </div>
								    </div>
								</div>
							</div>
							<br/>
							
							
							 <div class="critere-recherche">
								<!-- mots clés -->
								<label class="side-search-label" for="titreoffre" style="color:white;">Mots-clés</label>
								<input class="form-control" id="titreoffre" name="titreoffre" placeholder="Compétences, poste ..." type="text" style="width:250px">
							</div>

							<br/>


							<div class="submit-field">
								<input class="btn btn-primary" type="submit" value="Rechercher">
							</div>
						</div> <!-- fin content -->
					</form>
				</aside>




				<!-- left panel -->
			<section class="col-xs-9">
					
				<!-- card des annonces -->
				<c:set var="i" value="0"/>
				
				<c:if test="${not empty listeAnnonces}">
					<c:forEach var="i" begin="1" end="${fn:length(listeAnnonces)}" step="1">
						<div class="card" style="border: 1px solid darkgrey;
														background-color: white;
														margin-top: 90px;">
							<div class="alignement_bloc" style="display: inline-block;">
								<img src="jobteaser.png" alt="logoSociete" class="logoSocieteAnnonce" style="width:100px;
																											height: 100px;
																											margin-top: 20px;
																											margin-bottom: 25px;
																											margin-right: 20px;
																											margin-left: 20px;"	>
							</div>
							<div class="alignement_bloc" style="display: inline-block;">
								<h3><c:out value="${search.getTitreoffre()}"/></h3>
								<hr style="border-color:darkgrey">
								<div class="alignement_bloc" style="display: inline-block;">
									<img src="jobteaser.png" alt="logoTypeContrat" class="logoPNGannonce" style="width:15px; height: 15px;float: left;margin-left: 20px;margin-right: 10px;">
									<c:out value="${search.getTypecontrat()}"/>
								</div>
								<div class="alignement_bloc" style="display: inline-block;">
									<img src="jobteaser.png" alt="logoSuitCase" class="logoPNGannonce" style="width:15px; height: 15px;float: left;margin-left: 20px;margin-right: 10px;">
									<c:out value="${search.getIdentreprise()}"/>
								</div>
								<div class="alignement_bloc" style="display: inline-block;">
									<img src="jobteaser.png" alt="logoLieu" class="logoPNGannonce" style="width:15px; height: 15px;float: left;margin-left: 20px;margin-right: 10px;">
									<c:out value="${search.getIdentreprise()}"/>lieu
								</div>
								<div class="alignement_bloc" style="display: inline-block;">
									<img src="jobteaser.png" alt="logoDate" class="logoPNGannonce" style="width:15px; height: 15px;float: left;margin-left: 20px;margin-right: 10px;">
									<c:out value="${search.getDatepublication()}"/>
								</div>
							</div>
						</div>
					</c:forEach>
					</c:if>
					<c:if test="${empty listeAnnonces }">
						<h3 style="text-align: center">Il n'y a pas d'annonce disponible</h3>
					</c:if>
				</section>	
				
				
			</div>
		</div>		
		
				<!-- footer -->
		
	</body>
	<c:import url="footer.jsp"/>
	
</html>