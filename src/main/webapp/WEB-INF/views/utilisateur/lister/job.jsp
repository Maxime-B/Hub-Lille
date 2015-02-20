<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		liste de mes jobs
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<section class="section">
			<h1>liste de mes jobs</h1>


			<c:if test="${not empty jobs}">
				<div class="row">
					<strong class="small-4 columns">titre</strong> <strong
						class="small-4 columns">date</strong>
				</div>

				<c:forEach items="${jobs}" var="job">
					<div class="row">
						<div class="small-4 columns">${job.titre}</div>
						<div class="small-4 columns">${job.description}</div>
						<div class="small-4 columns">
							<a
								href="${pageContext.request.contextPath}/job/modifier/${job.id}"
								class="button small">Modifier</a>
							<a
								href="${pageContext.request.contextPath}/job/supprimer/${job.id}"
								class="button small">Supprimer</a>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty jobs}">
				<div>aucun job créé</div>
			</c:if>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>