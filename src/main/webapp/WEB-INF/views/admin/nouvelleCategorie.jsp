<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="global">
	<tiles:putAttribute name="title">Nouvelle catégorie
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<form action="creationCategorie">
			Nom de la catégorie : <input maxlength="26" type="text" name="nomCategorie" />
			<table style="width: 100%" id="tableau">
				<tr>
					<th>Nom du champ</th>
					<th>Type</th>
					<th>Obligatoire</th>
					<th>Choix</th>
				</tr>
				<c:forEach items="${champs}" var="item">
					<tr>
						<td>${item.libelle}</td>
						<td>${item.typeChamp}</td>
						<td>${item.obligatoire}</td>
						<td><input type="checkbox" value="${item.libelle}"
							onClick="test(this)" /></td>
					</tr>
				</c:forEach>
			</table>
			<input class="radius button" type="button" value="Ajouter un champ"  onClick="ajouterChamp()"/>
			<br>
			<br>
			Ordre d'affichage des champs de la cat�gorie :
			<div id="champsChoisi">
				<ul id="sortable" class="no-bullet">
				</ul>
			</div>
			<br> <input class="radius button" type="submit" value="Valider" />
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

