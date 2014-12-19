<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creer une annonce</title>
</head>
<body>
<jsp:include page="bar.jsp" />
	
	
	<div class="row">
		<div class="large-12 columns">
Creer une annonce
<form action="creationAnnonce" method="GET">
<c:forEach items="${lesChamps}" var="item">
<c:choose>
  <c:when test="${item.typeChamp=='TEXTE'}">
    ${item.libelle} : <input type="text" name="${item.libelle}">
  </c:when>
  <c:otherwise>
    sniiiif
  </c:otherwise>
</c:choose>

<br />
</c:forEach>
<input type="submit" value="Publier" />
</form>

</div></div>

</body>
</html>