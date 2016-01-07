<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Telecharger Documents</title>
</head>
<body>
<c:if test="${not empty nomsFichiers}">
<c:forEach var="i" begin="0" end="${(fn:length(nomsFichiers))-1}" step="1">
<h4><b><c:out value="${nomsFichiers.get(i)}"/></b></h4>
<form method="post" action="documentDownload" ><input type="hidden" name="string" value="${nomsFichiers.get(i)}"/><input class="btn btn-primary" type="submit" value="Telecharger"/></form>
</c:forEach>
</c:if>
</body>
</html>