<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="global">
	<tiles:putAttribute name="title">Administration Cat�gorie
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
<table>
<tr><th>Nom de la cat�gorie</th></tr>
<c:forEach items="${categories}" var="item">
<tr><td>${item.nom}</td></tr>
</c:forEach>
</table>
<a href="nouvelleCategorie">Ajouter une nouvelle cat�gorie</a>
	</tiles:putAttribute>
</tiles:insertDefinition>