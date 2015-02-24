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
		<div style="text-align: right">
					<div align="right">
							<td><select id="publication" name="publication"
								style="width: 200px; height: 35px">
									<option value="/hublille1/annonce/listerOffre">annonces-offres</option>
									<option value="/hublille1/annonce/listerDemande">annonces-demandes</option>
									<option value="/hublille1/job/listerJob">jobs</option>
									<option value="/hublille1/evenement">événements</option>
							</select></td>
							&nbsp
							<td><input id="publication-submit" type="submit" value="chercher" class="button small" /></td>
						
					</div>
				</div>
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