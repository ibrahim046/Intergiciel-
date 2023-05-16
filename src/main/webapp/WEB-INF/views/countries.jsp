<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des pays</title>
</head>
<body>
<h1> Liste des Pays et leurs capitales</h1>
    <a href="${pageContext.request.contextPath}/">Accueil</a>

    <table border="2">
        <thead>
            <tr>
                <th>Country</th>
                <th>Capital</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="country" items="${countries}">
                <tr>
                    <td>${fn:split(country, '-')[0]}</td>
                    <td>${fn:split(country, '-')[1]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
