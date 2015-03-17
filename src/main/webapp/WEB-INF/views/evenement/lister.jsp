<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="evenement">
	<tiles:putAttribute name="title">
		<spring:message code="evenement.lister.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">


			<h3>
				<spring:message code="evenement.lister.lesEve" />
			</h3>
			<br />


			<div
				style="padding-top: 10px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; margin: 0 2% 5% 0;">
				<br />

				<c:if test="${not empty evenements}">
					<c:forEach items="${evenements}" var="evenement">

						<table
							style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; width: 100%;">
							<tbody>
								<tr>
									<td style="padding-left: 15%; padding-top: 2%;"><h5
											class="titre upper">${evenement.titre}<small> -
												${evenement.lieu}</small>
										</h5></td>
									<td><p class="description upper"
											style="word-wrap: break-word;font-size: small;font-weight: bold;font-style: normal;">
											<fmt:formatDate value="${evenement.dateDebut}"
												pattern="dd- MM- yyyy" />
											<fmt:formatDate value="${evenement.heureDebut}"
												pattern=" @ HH:mm" />
										</p></td>
								</tr>
								<tr>
									<td rowspan="2" style="padding-left: 15%;background-color: #FFF"><p class="description upper"
											style="word-wrap: break-word; width: 450px">${evenement.description}</p></td>




								</tr>
							</tbody>
						</table>
					</c:forEach>
				</c:if>
				<c:if test="${empty evenements}">
					<h5> <spring:message code="evenement.lister.aucun"/> </h5>
				</c:if>
			</div>
		</section>

	</tiles:putAttribute>
</tiles:insertDefinition>