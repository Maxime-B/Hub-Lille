<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		Liste des utilisateurs speciaux
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<h1>Lister des utilisateurs speciaux</h1>
		<section class="section">
			<table>
				<tr>
					<th>login</th>
					<th>droit</th>
				</tr>

				<c:if test="${not empty utilisateurs}">
					<c:forEach items="${utilisateurs}" var="utilisateur">
						<tr>
							<td>${utilisateur.login}</td>
							<td>${utilisateur.droit}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty utilisateurs}">
					<tr>
						<td colspan="2">aucun utilsateur n'a de droit spécial</td>
					</tr>
				</c:if>
			</table>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>