<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choix catégorie</title>
</head>
<body>
<jsp:include page="bar.jsp" />
	
	
	<div class="row">
		<div class="large-12 columns">
choix catégorie

<FORM  METHOD="get" ACTION="vueCreerAnnonce">
<select name="categorieChoisie" >
<c:forEach items="${categories}" var="item">
<option value="${item.nom}">${item.nom}</option>
</c:forEach>
</select>
<input type="submit" value="OK " >
</FORM>
</div></div>
</body>
</html>