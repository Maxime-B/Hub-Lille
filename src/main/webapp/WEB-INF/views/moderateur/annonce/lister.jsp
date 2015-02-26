<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		<spring:message code="moderation.lister.titre" />
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<section class="section">
			<h1><spring:message code="moderation.lister.sstitre" /></h1>


			<c:if test="${not empty annonces}">
				<div class="row">
					<strong class="small-3 columns"><spring:message code="moderation.lister.label.titre" /></strong> <strong
						class="small-3 columns"><spring:message code="moderation.lister.label.description" /></strong>
						<strong
						class="small-3 columns"><spring:message code="moderation.lister.label.signal" /></strong>
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
								class="button small"><spring:message code="moderation.lister.modifier" /></a> <a
								href="${pageContext.request.contextPath}/annonce/supprimer/${annonce.id}"
								class="button small"><spring:message code="moderation.lister.supprimer" /></a>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty annonces}">
				<div><spring:message code="moderation.lister.pasAnnonce" /></div>
			</c:if>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>