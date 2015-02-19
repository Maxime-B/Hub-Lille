<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		liste de mes evenements
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<section class="section">
			<h1>liste de mes evenements</h1>


			<c:if test="${not empty evenements}">
				<div class="row">
					<strong class="small-4 columns">titre</strong> <strong
						class="small-4 columns">date</strong>
				</div>

				<c:forEach items="${evenements}" var="evenement">
					<div class="row">
						<div class="small-4 columns">${evenement.titre}</div>
						<div class="small-4 columns"><fmt:formatDate type="date" dateStyle="long" value="${evenement.dateDebut}"/></div>
						<div class="small-4 columns">
							<a
								href="${pageContext.request.contextPath}/evenement/modifier/${evenement.id}"
								class="button small">Modifier</a>
							<a
								href="${pageContext.request.contextPath}/evenement/supprimer/${evenement.id}"
								class="button small">Supprimer</a>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty evenements}">
				<div>aucun evenement créé</div>
			</c:if>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>