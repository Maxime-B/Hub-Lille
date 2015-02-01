<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">
		liste de mes annonces
	</tiles:putAttribute>
	
	<tiles:putAttribute name="main">
		<section class="section">
			<h1>liste de mes annonces</h1>
		
			<table>
				<tr>
					<th>titre</th>
					<th>description</th>
				</tr>
				
				<c:if test="${not empty annonces}">
				 <c:forEach items="${annonces}" var="annonce" >
					<tr>
						<td><a href="${pageContext.request.contextPath}/annonce/consulter?ref=${annonce.id}">${annonce.titre}</a></td>
						<td>${annonce.description}</td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty annonces}">
					<tr>
						<td colspan="2">aucune annonce créée</td>
					</tr>
				</c:if>
			</table>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>