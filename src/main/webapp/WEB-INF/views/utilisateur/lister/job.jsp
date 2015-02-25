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
				<table id="datatable">
				<thead>
				<tr>
					<th width=400>titre</th>
					
					
					
					<th></th>
					<th></th>
					<th></th>
				</tr>
				</thead>
				<tbody>
				 <c:forEach items="${jobs}" var="job" >
					<tr>
						<td>${job.titre}</td>
						
					
						
						<td><a href="${pageContext.request.contextPath}/job/consulter?ref=${job.id}"><button class="button small">Consulter</button> </a></td>
						<td>
							<a
								href="${pageContext.request.contextPath}/job/modifier/${job.id}"
								class="button small">Modifier</a></td>
						<td>	
						<button type="button" data-reveal-id="supprimer_${annonce.id}">Supprimer</button>
							<div id="supprimer_${annonce.id}" class="reveal-modal" data-reveal>
							<form method="post" action="">
								<h3>Voulez vous supprimer le job "${job.titre}"</h3>
								<input type="hidden" name="ref" value="${job.id}"/>
								<input type="hidden" name="typeAction" value="supprimer"/>
								<button onclick="this.form.submit();">Oui</button>
								<button class="supprimer"type="button" data-reveal>Non</button>
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
						<td colspan="2">aucun job créé</td>
					</tr>
				</c:if>
			
			
		</section>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
		<script src="<c:url value="/ressources/js/datatablesLister.js"/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>