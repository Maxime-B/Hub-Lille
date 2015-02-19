<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		liste de mes annonces
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<section class="section">
			<h1>liste des annonces signalées</h1>


			<c:if test="${not empty annonces}">
				<div class="row">
					<strong class="small-3 columns">titre</strong> <strong
						class="small-3 columns">description</strong>
						<strong
						class="small-3 columns">nombre de signalement</strong>
				</div>

				<c:forEach items="${annonces}" var="annonce">
					<div class="row">
						<div class="small-3 columns">${annonce.titre}</div>
						<div class="small-3 columns">
							${annonce.description}
						</div>
						<div class="small-3 columns">
							${annonce.signal}
						</div>
						<div class="small-3 columns">
							<a
								href="${pageContext.request.contextPath}/annonce/modifier/${annonce.id}"
								class="button small">Modifier</a> <a
								href="${pageContext.request.contextPath}/annonce/supprimer/${annonce.id}"
								class="button small">Supprimer</a>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty annonces}">
				<div>aucune annonce n'a été signalé plus de 5 fois</div>
			</c:if>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>