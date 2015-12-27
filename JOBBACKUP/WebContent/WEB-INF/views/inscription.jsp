<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S'enregistrer</title>
</head>
<body>
<form method="post" action="registration" >
Identifiant : <input type="text" name="identifiant" required/><br/>
Mot de passe : <input type="password" name="password" required/><br/>
Nom : <input type="text" name="nom" required/><br/>
Prenom : <input type="text" name="prenom" required/><br/>
Civilité : <select name="sexe" size=1 required>
<option>Homme
<option>Femme
</select><br/>
Mail : <input type="text" name="email" required/><br/>
Age : <input type="text" name="age"/><br/>
Telephone : <input type="text" name="telephone"/><br/>
Classe : <SELECT name="classe" size="1" required>
<OPTION>cpi1
<OPTION>cpi2
<OPTION>ing1
<OPTION>ing2
<OPTION>ing3
</SELECT><br/>
<%//if(session.getAttribute("erreur")!=null) {out.println("<p><font color=\"red\">"+session.getAttribute("erreur").toString()+"</font></p>");} %>
<input type="submit" value="S'enregistrer"/>
</form>
</body>
</html>