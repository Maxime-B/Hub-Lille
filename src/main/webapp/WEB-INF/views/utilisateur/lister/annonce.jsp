<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		<spring:message code="utilisateur.annonce.titre" />
	</tiles:putAttribute>
	
	<tiles:putAttribute name="main">
		<section class="section">
			<h1><spring:message code="utilisateur.annonce.titre" /></h1>
		
			<c:if test="${!empty republierSucces}">
				<div class="alert-box success radius"><spring:message code="utilisateur.annonce.motAnnonce" /> ${annonce.titre} <spring:message code="utilisateur.annonce.aRepublie" /> ${annonce.finpublication} <br/>
				</div>
				
			</c:if>
			
				
				<c:if test="${not empty annonces}">
				<table id="datatable">
				<thead>
				<tr>
					<th><spring:message code="utilisateur.annonce.label.titre" /></th>
					
					<th><spring:message code="utilisateur.annonce.label.datePub" /></th>
					<th><spring:message code="utilisateur.annonce.label.dateFin" /></th>
					
					<th></th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				</thead>
				<tbody>
				 <c:forEach items="${annonces}" var="annonce" >
					<tr class="ligne">
						<td>${annonce.titre}</td>
						
						<td>${annonce.datepublication}</td>
						<td>${annonce.finpublication}</td> 
						
						<td><a href="${pageContext.request.contextPath}/annonce/consulter?ref=${annonce.id}"><button><spring:message code="utilisateur.annonce.consulter" /></button> </a></td>
						<td><a href="${pageContext.request.contextPath}/annonce/modifier?ref=${annonce.id}"><button><spring:message code="utilisateur.annonce.modifier" /></button> </a></td>
						<td>
							<button type="button" data-reveal-id="publier_${annonce.id}"><spring:message code="utilisateur.annonce.republier" /></button>
							<div id="publier_${annonce.id}" class="reveal-modal" data-reveal>
							<form method="post" action="">
							<h3><spring:message code="utilisateur.annonce.questionRep" /> "${annonce.titre}"</h3>
								<input type="hidden" name="ref" value="${annonce.id}"/>
								<input type="hidden" name="typeAction" value="republier"/>
								<button onclick="this.form.submit();"><spring:message code="utilisateur.annonce.oui" /></button>
								<button class="republier" id="rep_annuler_${annonce.id}" type="button"><spring:message code="utilisateur.annonce.non" /></button>
							</form>
							</div>
							
						</td>
						<td>
							<button type="button" data-reveal-id="supprimer_${annonce.id}"><spring:message code="utilisateur.annonce.supprimer" /></button>
							<div id="supprimer_${annonce.id}" class="reveal-modal" data-reveal>
							<form method="post" action="">
								<h3><spring:message code="utilisateur.annonce.questionSupp" /> "${annonce.titre}"</h3>
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
					<tr>
						<td colspan="2"><spring:message code="utilisateur.annonce.pasAnnonce" /></td>
					</tr>
				</c:if>
			
		</section>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
	<script type="text/javascript">
	$( document ).ready(function() {
	    var length = $(".ligne").length;
	    for(i = 1 ; i < length+1;i++)
	    	{
	    	$(document).on('click tap touchstart', '.reveal-modal-bg, .supprimer,.republier', function() {
	    	    return $('[data-reveal]').foundation('reveal', 'close');
	    	});
	    	}
	});
	
	
	</script>
	
<script src="<c:url value="/ressources/js/datatablesLister.js"/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>