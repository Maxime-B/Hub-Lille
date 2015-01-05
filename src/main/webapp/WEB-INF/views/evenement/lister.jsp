<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="evenement">
	<tiles:putAttribute name="title">
		<spring:message code="evenement.creer.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
	<section class="section">
		<table>
			<tr>
				<th>titre</th>
				<th>description</th>
				<th>lieu</th>
				<th>date de debut</th>
				<th>heure de debut</th>
			</tr>
			
			<c:if test="${not empty evenements}">
			 <c:forEach items="${evenements}" var="evenement" >
				<tr>
					<td>${evenement.titre}</td>
					<td>${evenement.description}</td>
					<td>${evenement.lieu}</td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${evenement.dateDebut}"/></td>
					<td><fmt:formatDate type="time" timeStyle="short" value="${evenement.heureDebut}"/></td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty evenements}">
				<tr>
					<td colspan="5">aucun événement à venir</td>
				</tr>
			</c:if>
		</table>
	</section>
	</tiles:putAttribute>
</tiles:insertDefinition>