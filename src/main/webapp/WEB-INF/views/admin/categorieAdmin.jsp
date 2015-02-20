<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="global">
	<tiles:putAttribute name="title">Administration Catégorie
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
<table>
<tr><th>Nom de la catégorie</th><th>Supression</th></tr>
<c:forEach items="${categories}" var="item">
<tr id="${item.nom}"><td>${item.nom}</td><td><button id="supprCate" onClick="supprimerCategorie('${item.nom}')">Supprimer</button></td></tr>
</c:forEach>
</table>
<a href="nouvelleCategorie">Ajouter une nouvelle catégorie</a>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
		<script src="<c:url value="/ressources/js/supprimerCategorie.js"/>"></script>
		<script type='text/javascript' src='/hublille1/dwr/engine.js'></script> 
		<script type='text/javascript' src='/hublille1/dwr/util.js'></script>
		<script type='text/javascript' src='/hublille1/dwr/interface/JSMetierCategorie.js'></script>
		</tiles:putAttribute>
</tiles:insertDefinition>