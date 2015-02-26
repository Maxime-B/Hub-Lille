<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="global">
	<tiles:putAttribute name="title"><spring:message code="admin.categorieAdmin.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
<table style="width: 100%">
<tr><th><spring:message code="admin.categorieAdmin.nomCategorie" /></th><th style="width: 25%;text-align: center;"><spring:message code="admin.categorieAdmin.suppression" /></th></tr>
<c:forEach items="${categories}" var="item">
<tr id="${item.nom}"><td>${item.nom}</td><td style="width: 25%;text-align: center;" valign="center"><button class="button alert radius" id="supprCate"  style="margin:0;" onClick="supprimerCategorie('${item.nom}')"><spring:message code="admin.categorieAdmin.supprimer" /></button></td></tr>
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