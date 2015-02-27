<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="tilesSearch">
	<tiles:insertAttribute name="search" />
</c:set>

<c:set var="langue">
	<spring:message code="template.langue" />
</c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${langue}">
<head>
<title><tiles:insertAttribute name="title" /></title>
<tiles:insertAttribute name="globalCss" />
<tiles:insertAttribute name="css" />
<tiles:insertAttribute name="globalMeta" />
<tiles:insertAttribute name="meta" />
<tiles:insertAttribute name="globalJsTop" />
<tiles:insertAttribute name="jsTop" />
</head>
<body>
	<div id="container">
		<header id="header"> <tiles:insertAttribute name="header" />
		</header>
		<div class="row">
			<aside id="aside" class="small-3 columns ">
			<div>${tilesSearch}</div>
			<nav> <tiles:insertAttribute name="nav" /></nav> </aside>

			<main id="main" class="small-9 columns"> <tiles:insertAttribute
				name="main" /> </main>
		</div>

		<footer id="footer"> <tiles:insertAttribute name="footer" />
		</footer>
	</div>

	<tiles:insertAttribute name="globalJs" />

	<tiles:insertAttribute name="js" />
</body>
</html>