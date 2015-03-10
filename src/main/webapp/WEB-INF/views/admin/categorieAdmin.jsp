<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="global">
	<tiles:putAttribute name="title"><spring:message code="admin.categorieAdmin.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
<table style="width: 100%">
<tr><th><spring:message code="admin.categorieAdmin.nomCategorie" /></th><th><spring:message code="admin.categorieAdmin.nomCategorie2" /></th><th style="width: 25%;text-align: center;"><spring:message code="admin.categorieAdmin.suppression" /></th></tr>
<c:forEach items="${categories}" var="item">
<c:set var="tmp" value="${item.nom}" />
<c:set var="libelle" value="${fn:split(tmp, '-')}" />
<tr id="${item.nom}"><td>${libelle[0]}</td><td>${libelle[1]}</td><td style="width: 25%;text-align: center;" valign="center"><button class="button alert radius" id="supprCate"  style="margin:0;" onClick="supprimerCategorie('${item.nom}')"><spring:message code="admin.categorieAdmin.supprimer" /></button></td></tr>
</c:forEach>
</table>
<a href="nouvelleCategorie"><button class="radius button"><spring:message code="admin.categorieAdmin.ajouterCategorie" /></button></a>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
		<script src="<c:url value="/ressources/js/supprimerCategorie.js"/>"></script>
		<script type='text/javascript' src='/hublille1/dwr/engine.js'></script> 
		<script type='text/javascript' src='/hublille1/dwr/util.js'></script>
		<script type='text/javascript' src='/hublille1/dwr/interface/JSMetierCategorie.js'></script>
		</tiles:putAttribute>
</tiles:insertDefinition>