<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title" value="${annonce.titre}" />

	<tiles:putAttribute name="main">
		<section class="section">
			<h1>${annonce.titre}</h1>

			<!-- message si redirection -->
			<c:if test="${!empty param['estUnSucces']}">
				<div class="alert-box success radius">L'annonce ${annonce.titre} est maintenant consultable.</div>
			</c:if>

			<table>
				<tr>
					<td>Description</td>
					<td>${annonce.description}</td>
				</tr>

				<tr>
					<td>Categorie</td>
					<td>${annonce.categorie.nom }</td>
				</tr>

				<c:forEach items='${annonce.lesChamps}' var="item">
					<tr>
						<td>${item.key}</td>
						<td>${item.value}</td>
					</tr>
				</c:forEach>
			</table>

			<br />

			<table>
				<tr>
					<td>Nature de l'annonce</td>
					<td>${annonce.type }</td>
				</tr>

				<tr>
					<td>Contact</td>
					<td><A HREF="mailto:${annonce.utilisateur.email}">${annonce.utilisateur.email}</A>
					</td>
				</tr>
			</table>

			<br /> <a href="/">Retour à la page d'accueil</a>

		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>