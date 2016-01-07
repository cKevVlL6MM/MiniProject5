<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter un évènement</title>
</head>
<body>
	<form action="addEvent" method="post">
		<label for="nomevenement">Nom de l'événement</label><br/>
		<input type="text" name="nomevenement" id="nomevenement" tabindex="1" class="form-control" ><br/>
		<label for="datedebut">Date du début de l'événement (jj/mm/aaaa)</label><br/>
		<input type="text" name="datedebut" id="datedebut" tabindex="1" class="form-control" value="dd/mm/yyyy" ><br/>
		<label for="datefin">Date de fin de l'événement (jj/mm/aaaa)</label><br/>
		<input type="text" name="datefin" id="datefin" tabindex="1" class="form-control" value="dd/mm/yyyy" ><br/>
		<input type="submit" value="Enregistrer"/>						
	</form>
</body>
</html>