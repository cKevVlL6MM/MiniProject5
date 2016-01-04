<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mes annonces</title>
</head>
<body>
	<c:if test="${not empty listeOffres}">
		<h3 style="text-align: center">Mes annonces</h3>
		<c:forEach var="i" begin="1" end="${fn:length(listeOffres)}" step="1">
			<h3><c:out value="${listeOffres.getTitreoffre()}"/></h3>
			<h3><c:out value="${listeOffres.getContenu()}"/></h3>
			<h3><c:out value="${listeOffres.getDatepublication()}"/></h3>
			<h3><c:out value="${listeOffres.getDureeoffre()}"/></h3>
			<h3>modifier l'offre</h3>
		</c:forEach>
	</c:if>
	<c:if test="${empty listeOffres }">
		<h3 style="text-align: center">Il n'y a pas d'annonce enregistrée</h3>
	</c:if>
	<h3>Enregistrer une nouvelle annonce</h3>
	<form action="annonceController" method="post">
		<label for="titreoffre">Titre</label><br/>
		<input type="text" name="titreoffre" id="titreoffre" tabindex="1" class="form-control" ><br/>
		<label for="contenu">Contenu de l'annonce</label><br/>
		<textarea name="contenu" rows="10" cols="50"></textarea><br/>
		<label for="dureeoffre">Fin de l'offre</label><br/>
		<input type="text" name="dureeoffre" id="dureeoffre" tabindex="1" class="form-control" value="dd/mm/yyyy"><br/>
		<label for="idtypecontrat">Type de Contrat</label><br/>
		<select class="form-control" id="idtypecontrat" name="idtypecontrat">
									<option value="-1"></option>
									<option value="5">Stage</option>
									<option value="3">Alternance</option>
									<option value="4">VIE</option>
									<option value="1">CDD</option>
									<option value="2">CDI</option>
									<option value="6">Bénévolat</option>
								</select><br/>
		<label for="idniveauminimum">Promotion ciblée</label><br/>
		<SELECT name="idniveauminimum" size="1" required>
			<OPTION value="1">cpi1
			<OPTION value="2">cpi2
			<OPTION value="3">ing1
			<OPTION value="4">ing2
			<OPTION value="5">ing3
		</SELECT><br/>
		<input type="submit" value="Enregistrer"/>						
	</form>
	
</body>
</html>