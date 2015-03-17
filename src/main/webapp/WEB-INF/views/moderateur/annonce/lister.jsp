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
				<table id="datatable">
				<thead>
				<tr>
					<th><spring:message code="moderation.lister.label.titre" /></th>
					
					<th><spring:message code="moderation.lister.label.description" /></th>
					<th><spring:message code="moderation.lister.label.signal" /></th>
					
					<th></th>
					<th></th>
					
					<th></th>
				</tr>
				</thead>
				<tbody>
				 <c:forEach items="${annonces}" var="annonce" >
					<tr class="ligne">
						<td>${annonce.titre}</td>
						
						<td>${annonce.description}</td>
						<td>${annonce.signal}</td> 
						
						
						<td><a href="${pageContext.request.contextPath}/annonce/modifier?ref=${annonce.id}"><button><spring:message code="moderation.lister.modifier" /></button> </a></td>
						
						<td>
							<button type="button" data-reveal-id="supprimer_${annonce.id}"><spring:message code="moderation.lister.supprimer" /></button>
							<div id="supprimer_${annonce.id}" class="reveal-modal" data-reveal>
							<form method="post" action="">
								<h3><spring:message code="utilisateur.annonce.questionSupp" /> "${annonce.titre}" ?</h3>
								<input type="hidden" name="ref" value="${annonce.id}"/>
								<input type="hidden" name="typeAction" value="supprimer"/>
								<button onclick="this.form.submit();"><spring:message code="utilisateur.annonce.oui" /></button>
								<button class="supprimer"id="rep_annuler_${annonce.id}"type="button" data-reveal><spring:message code="utilisateur.annonce.non" /></button>
							</form>
							</div>
						</td>
					</tr>
					</c:forEach>
					</tbody>
					</table>
			</c:if>
			<c:if test="${empty annonces}">
				<div><spring:message code="moderation.lister.pasAnnonce" /></div>
			</c:if>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>