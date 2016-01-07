<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
 <link href="Bootstrap/jquery-ui.css" rel="stylesheet">
  
	</head>
	<c:import url="header.jsp"/>
	<body style="background-color: white">
	
	<br/>
	<div class="container">
	<table class="table table-hover">
	<tr>
	<td> Titre de l'annonce </td>
	<td> Contenu </td>
	<td> Date de publication </td>
	</tr>
	<c:if test="${empty listeFavorites }">
	
	<tr>
	<td colspan="3">
	Aucune Annonce épinglée
	</td>
	</tr>
	
	
	</c:if>
	<c:if test="${not empty listeFavorites }">
	
	<c:forEach var="i" begin="0" end="${(fn:length(listeFavorites))-1}" step="1">
	<tr>
	<td><c:out value="${listeFavorites.get(i).getTitreoffre()}"/></td>
	<td><c:out value="${listeFavorites.get(i).getContenu()}"/></td>
	<td><c:out value="${listeFavorites.get(i).getDatepublication()}"/></td>
	</tr>
	</c:forEach>
	
	
	
	
	
	</c:if>
	
	
	
	
	
	
	</table>
	
	
	
	</div>
	

</body>
</html>