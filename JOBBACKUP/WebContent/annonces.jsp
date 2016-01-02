<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
  <link href="Bootstrap/styleAnnonces.css" rel="stylesheet" type="text/css" />
  

	</head>
	<body>
	


		<div class="container" >
			<div class="row">

<!-- 			right panel -->
				<aside class="col-xs-3">
					<form action="annonces" method="post">
						<h3>Critères de Recherche</h3>
						<hr>
						<div class="content">
						

							<div class="critere-recherche">
								<!-- type de contrat -->
								<label for="idtypecontrat">Contrat</label>
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
								<label for="idtypesecteur">Secteur</label>
								<select id="idtypesecteur" name="idtypesecteur">
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
								<label for="idtypecompetence">Compétences Requises</label>
								<select class="form-control" id="idtypecompetence" name="idtypecompetence">
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
							
							
							
							<!-- <div class="critere-recherche"> -->
								<!-- date de création -->
								<!-- 
								<label for="datepublication">Date de création</label>
								<div class="container">
								    <div class="row">
								        <div style="width:250px">
								        	<div class="form-group">
								                <div class='input-group date' id='datetimepicker1'>
								                    <input type='text' class="form-control" />
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span>
								                </div>
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
							<br/> -->

							 <div class="critere-recherche">
								<!-- mots clés -->
								<label class="side-search-label" for="titreoffre">Mots-clés</label>
								<input class="form-control" id="titreoffre" name="titreoffre" placeholder="Compétences, poste ..." type="text" style="width:250px">
							</div>

							<br/>

							<!-- <div class="critere-recherche"> -->
								<!-- ou? -->
								<!-- 
								<div style="width:250px">
									<label class="side-search-label" for="location">Où</label>
									<div class="bootstrap-tagsinput location_tags">
										<input type="text" placeholder="Saisir un lieu" class="form-control" style="" autocomplete="on">
									</div>
									<input class="form-control" data-js-component-opts="{&quot;name&quot;:&quot;GmapsAutocomplete&quot;,&quot;opts&quot;:{&quot;field_name&quot;:&quot;locations&quot;,&quot;value&quot;:null}}" id="gmaps-input-city" name="loc" placeholder="Saisir un lieu" type="text" style="display: none;">
									<div style="display:none;"></div>
								</div>
							</div>
							<br/>-->

							<div class="submit-field">
								<input class="btn btn-primary" type="submit" value="Rechercher">
							</div>
						</div> <!-- fin content -->
					</form>
				</aside>




				<!-- left panel -->
				<section class="col-xs-9">
					
				<!-- card des annonces -->
						<div class="card">
							<div class="alignement_bloc">
								<img src="jobteaser.png" alt="logoSociete" class="logoSocieteAnnonce">
							</div>
							<div class="alignement_bloc">
								<h3>Nom de l'annonce</h3>
								<hr style="border-color:darkgrey">
								<div class="alignement_bloc">
									<img src="jobteaser.png" alt="logoTypeContrat" class="logoPNGannonce">
									type de contrat
								</div>
								<div class="alignement_bloc">
									<img src="jobteaser.png" alt="logoSuitCase" class="logoPNGannonce">
									société
								</div>
								<div class="alignement_bloc">
									<img src="jobteaser.png" alt="logoLieu" class="logoPNGannonce">
									lieu
								</div>
								<div class="alignement_bloc">
									<img src="jobteaser.png" alt="logoDate" class="logoPNGannonce">
									date de publication
								</div>
							</div>
						</div>
				</section>
			</div>
		</div>		
		
		
	</body>
</html>