<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
    <%@page isELIgnored="false" %> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>

    <meta charset="utf-8">
    

    <title>Accueil Admin</title>

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



<section class="container" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 text-center">
                 <img src="<c:url value="img/LOGO.png"/>" class="img-responsive" alt="">
                    <h2 class="section-heading">Admin : Gestion de l'application actuelle</h2>
                    <hr class="light">
                    
                    
                </div>
                <div class="col-lg-8 ">
              <table class="table table-hover">
				   <tr class="warning">
				       <td>Événement</td>
				       <td>Début</td>
				       <td>Fin</td>
				   </tr>
				   <c:if test="${not empty listEvents }">
				<c:forEach var="i" begin="0" end="${(fn:length(listEvents))-1}" step="1">
				   <tr>
					<td><c:out value="${listEvents.get(i).getNomevenement()}"/></td>
					<td><c:out value="${listEvents.get(i).getDatedebut()}"/></td>
					<td><c:out value="${listEvents.get(i).getDatefin()}"/></td>
				   </tr>
				   </c:forEach>
				   </c:if>
				   <c:if test="${empty listEvents }">
				   <tr><td colspan=3>Aucun evenement de prevu</td></tr>
				   </c:if>
				   
			</table>
                    
                    
         
      </div>
               
            </div>
        </div>
    </section>




<section class="bg-primary" style="height:10px !important;  background:rgba(17,105,142,1)" >
        <div class="container">
        
        
        
         
        
           
                <div class=" text-center">
                
                    <h2>Choisissez une option ci-dessous</h2>
                    <hr class="star-light">
                </div>
        
       
  
            
            
        </div>
    </section>






    
    <div class="container">


<section class="no-padding" id="portfolio">
        <div class="container-fluid">
            <div class="row no-gutter">
                <div class="col-lg-4 col-sm-6">
                    <a href="newsletter" class="portfolio-box">
                        <img src="<c:url value="img/Newsletter.png"/>" class="img-responsive" alt=""/>
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                   Newsletter
                                </div>
                                <div class="project-name">
                                   Gérer les newsletters
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <a href="gererAnnonceAdmin" class="portfolio-box">
                        <img src="<c:url value="img/Annonce.png"/>" class="img-responsive" alt="">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                  Annonces  
                                </div>
                                <div class="project-name">
                                  Gerer les annonces
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <a href="addEvent" class="portfolio-box">
                        <img src="<c:url value="img/Evenement.png"/>" class="img-responsive" alt="">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                    Actualité et événements 
                                </div>
                                <div class="project-name">
                                  Ajouter des evenements
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </section><br/>
    <section class="no-padding" id="portfolio">
        <div class="container-fluid">
            <div class="row no-gutter">
                <div class="col-lg-4 col-sm-6">
                    <a href="documentDownload" class="portfolio-box">
                        <img src="<c:url value="img/bworld.jpg"/>" class="img-responsive" alt=""/>
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                   Documents
                                </div>
                                <div class="project-name">
                                   Télécharger des documents
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <a href="documentUpload" class="portfolio-box">
                        <img src="<c:url value="img/myads.jpg"/>" class="img-responsive" alt="">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                 Documents   
                                </div>
                                <div class="project-name">
                                  Ajouter des documents
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <a href="stat" class="portfolio-box">
                        <img src="<c:url value="img/actualite.jpg"/>" class="img-responsive" alt="">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                   Statistiques 
                                </div>
                                <div class="project-name">
                                  Consulter les statistiques
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
               
                
              
            </div>
        </div>
    </section>
        
       </div>
       
       <br/>
       
  

</body>
 
<footer>
 <div class="bg-primary"  style=" padding:0; margin:0;  background:rgba(17,105,142,1)">
        
        <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        Copyright &copy; JobEisti 2k17 - Delin Gaspard Gundag Henry Pelletier
                    </div>
                </div>
            </div>
        </div>
    </div>
  </footer>

<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
});
</script>

</html>