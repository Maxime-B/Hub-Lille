<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="global">
	<tiles:putAttribute name="title"><spring:message code="admin.nouvelleCategorie.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
	<c:if test="${erreur=='erreur'}">
	<span class=" alert round label"><spring:message code="admin.nouvelleCategorie.erreur" /></span>
	
	</c:if>
		<form action="creationCategorie">
			<spring:message code="admin.nouvelleCategorie.nomCategorie" /> <input maxlength="30" type="text" name="nomCategorie" required/>
			<spring:message code="admin.nouvelleCategorie.nomCategorie2" /> <input maxlength="30" type="text" name="nomCategorie2" required/>
			<table style="width: 100%" id="tableau">
				<tr>
					<th><spring:message code="admin.nouvelleCategorie.nomChamp" /></th>
					<th><spring:message code="admin.nouvelleCategorie.nomChamp2" /></th>
					<th><spring:message code="admin.nouvelleCategorie.type" /></th>
					<th><spring:message code="admin.nouvelleCategorie.obligatoire" /></th>
					<th><spring:message code="admin.nouvelleCategorie.choix" /></th>
				</tr>
				<c:forEach items="${champs}" var="item">
					<c:set var="tmp" value="${item.libelle}" />
					<c:set var="libelle" value="${fn:split(tmp, '-')}" />
					<tr>
						<td>${libelle[0]}</td>
						<td>${libelle[1]}</td>
						<td>${item.typeChamp}</td>
						<td>${item.obligatoire}</td>
						<td><input type="checkbox" value="${item.libelle}"
							onClick="test(this)" /></td>
					</tr>
				</c:forEach>
			</table>
			<input class="radius button" type="button" value="<spring:message code="admin.nouvelleCategorie.ajouterChamp" />"  onClick="ajouterChamp()"/>
			<br>
			<br>
			<spring:message code="admin.nouvelleCategorie.ordreAffichage" />
			<div id="champsChoisi">
				<ul id="sortable" class="no-bullet">
				</ul>
			</div>
			<br> <input class="radius button" type="submit" value="<spring:message code="admin.nouvelleCategorie.valider" />" />
		</form>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="js">
		<script src="<c:url value="/ressources/js/creerCategorie.js"/>"></script>
		<script src="<c:url value="/ressources/js/ajouterChamp.js"/>"></script>
				<script type='text/javascript' src='/hublille1/dwr/engine.js'></script> 
		<script type='text/javascript' src='/hublille1/dwr/util.js'></script>
		<script type='text/javascript' src='/hublille1/dwr/interface/JSMetierCategorie.js'></script>
		<script type='text/javascript' src='/hublille1/dwr/interface/JSMetierChamp.js'></script>
		
		<script>
			$(function() {
				$("#sortable").sortable();
				$("#sortable").disableSelection();
			});
		</script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="css">
		<style>#sortable li {cursor:move}</style>
	</tiles:putAttribute>
</tiles:insertDefinition>

