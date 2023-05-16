<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search City</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Accueil</a> <br>

<h1>Search City</h1>
<form method="post" action="/country">
    <input type="text" name="countryName" placeholder="Enter a city name" />
    <button type="submit">Search</button>
</form>
    <br>
    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>   
    <h1>Resultats de recherche pour ${param.name}</h1>

    <table border="2">
        <thead>
            <tr>
                <th>Propriété</th>
                <th>Valeur</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${cityDetails}" var="statistics">
            <tr>
                <td>${fn:split(statistics, '-')[0]}</td>
                 <td>${fn:split(statistics, '-')[1]}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
