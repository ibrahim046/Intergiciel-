<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
    <h1>Bienvenue Dans notre projet ${name} ! </h1>
    <a href="${pageContext.request.contextPath}/countries">Visualiser un ensemble de pays et leur capitales</a> <br>
    <a href="${pageContext.request.contextPath}/country">Visualiser les informations d'un pays</a> <br>
    <%-- <a href="${pageContext.request.contextPath}/city">Visualiser les informations d'un pays</a> <br> --%>
</body>
</html>
