<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		<spring:message code="utilisateur.job.titre" />
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<section class="section">
			<h1><spring:message code="utilisateur.job.titre" /></h1>
			
			
			<c:if test="${not empty jobs}">
				<table id="datatable">
				<thead>
				<tr>
					<th width=400><spring:message code="utilisateur.job.label.titre" /></th>
					
					
					
					<th></th>
					<th></th>
					<th></th>
				</tr>
				</thead>
				<tbody>
				 <c:forEach items="${jobs}" var="job" >
					<tr>
						<td>${job.titre}</td>
						
					
						
						<td><a href="${pageContext.request.contextPath}/job/consulter?ref=${job.id}"><button class="button small"><spring:message code="utilisateur.job.consulter" /></button> </a></td>
						<td>
							<a
								href="${pageContext.request.contextPath}/job/modifier/${job.id}"
								class="button small"><spring:message code="utilisateur.job.modifier" /></a></td>
						<td>	
						<button type="button" class="button small" data-reveal-id="supprimer_${job.id}"><spring:message code="utilisateur.job.supprimer" /></button>
							<div id="supprimer_${job.id}" class="reveal-modal" data-reveal>
							<form method="post" action="">
								<h3><spring:message code="utilisateur.job.questionSupp" /> "${job.titre}" ?</h3>
								<input type="hidden" name="ref" value="${job.id}"/>
								<input type="hidden" name="typeAction" value="supprimer"/>
								<button onclick="this.form.submit();"><spring:message code="utilisateur.job.oui" /></button>
								<button class="supprimer" type="button" data-reveal><spring:message code="utilisateur.job.non" /></button>
							</form>
							</div>
						
						</td>
						
					</tr>
					</c:forEach>
					</tbody>
					</table>
				</c:if>
				<c:if test="${empty jobs}">
					<tr>
						<td colspan="2"><spring:message code="utilisateur.job.pasJob" /></td>
					</tr>
				</c:if>
			
			
		</section>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
		<script src="<c:url value="/ressources/js/datatablesLister.js"/>"></script>
		<script type="text/javascript">
	$( document ).ready(function() {
	    $(document).on('click tap touchstart', '.reveal-modal-bg, .supprimer,.republier', function() {
	    	    return $('[data-reveal]').foundation('reveal', 'close');
	    	});
	});
	
	
	</script>
	</tiles:putAttribute>
</tiles:insertDefinition>