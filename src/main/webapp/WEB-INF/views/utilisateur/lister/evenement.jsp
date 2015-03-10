<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		<spring:message code="evenement.lister.titre" />
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<section class="section">
			<h1><spring:message code="evenement.lister.titre" /></h1>


			<c:if test="${not empty evenements}">
				<table id="datatable">
					<thead>
						<tr>
							<th><spring:message code="evenement.lister.label.titre" /></th>
							<th><spring:message code="evenement.lister.label.dateDebut" /></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${evenements}" var="evenement">
						<tr>
							<td>${evenement.titre}</td>
							<td>${evenement.dateDebut}</td>
							<td><a
								href="${pageContext.request.contextPath}/evenement/modifier/${evenement.id}"
								class="button small"><spring:message code="evenement.lister.modifier" /></a></td>
							<td>
							
								<button type="button" data-reveal-id="supprimer_${evenement.id}"><spring:message code="evenement.lister.supprimer" /></button>
							<div id="supprimer_${evenement.id}" class="reveal-modal" data-reveal>
							<form method="post" action="">
								<h3><spring:message code="evenement.lister.question" /> "${evenement.titre}" ?</h3>
								<input type="hidden" name="ref" value="${evenement.id}"/>
								<input type="hidden" name="typeAction" value="supprimer"/>
								<button onclick="this.form.submit();"><spring:message code="evenement.lister.oui" /></button>
								<button class="supprimer" type="button" data-reveal><spring:message code="evenement.lister.non" /></button>
							</form>
							</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				

			
			</c:if>
			<c:if test="${empty evenements}">
				<div><spring:message code="evenement.lister.pasEve" /></div>
			</c:if>
		</section>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
	<script type="text/javascript">
	$( document ).ready(function() {
	    var length = $(".ligne").length;
	 
	    	$(document).on('click tap touchstart', '.reveal-modal-bg, .supprimer,.republier', function() {
	    	    return $('[data-reveal]').foundation('reveal', 'close');
	    	});
	    	
	});
	
	
	</script>
	
<script src="<c:url value="/ressources/js/datatablesLister.js"/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>
