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
				<table>
				<tr>
					<th width=400>titre</th>
					
					
					
					<th></th>
					<th></th>
					<th></th>
				</tr>
				 <c:forEach items="${jobs}" var="job" >
					<tr>
						<td>${job.titre}</td>
						
					
						
						<td><a href="${pageContext.request.contextPath}/job/consulter?ref=${job.id}"><button class="button small">Consulter</button> </a></td>
						<td>
							<a
								href="${pageContext.request.contextPath}/job/modifier/${job.id}"
								class="button small">Modifier</a></td>
						<td>	<a
								href="${pageContext.request.contextPath}/job/supprimer/${job.id}"
								class="button small">Supprimer</a></td>
						
					</tr>
					</c:forEach>
					</table>
				</c:if>
				<c:if test="${empty jobs}">
					<tr>
						<td colspan="2">aucun job créé</td>
					</tr>
				</c:if>
			
			
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>