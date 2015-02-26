<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="langue">
	<spring:message code="template.langue" />
</c:set>
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
			<aside id="aside" class="small-3 columns "> <nav> <tiles:insertAttribute
				name="nav" /></nav> </aside>

			<main id="main" class="small-9 columns">
			<div style="text-align: right">
				<div align="right">
					<form
						action="${pageContext.servletContext.contextPath }/publication/motcle">
						<table>
							<tr>
								<td><input type="textgeneral" name="motCle"
									style="width: 170px; height: 30px"></td>
								<td><select id="publication" name="publication"
									style="width: 200px; height: 35px">
										<option></option>
										<option value="annonces-offres">annonces-offres</option>
										<option value="annonces-demandes">annonces-demandes</option>
										<option value="jobs">jobs</option>
										<option value="evenements">événements</option>
								</select></td> &nbsp
								<td><input id="publication-submit" type="submit"
									value="<spring:message
												code="template.rechercher" />" class="button small" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<tiles:insertAttribute name="main" /> </main>
		</div>
		<footer id="footer"> <tiles:insertAttribute name="footer" />
		</footer>
		<tiles:insertAttribute name="globalJs" />
		<tiles:insertAttribute name="js" />
	</div>
</body>
</html>