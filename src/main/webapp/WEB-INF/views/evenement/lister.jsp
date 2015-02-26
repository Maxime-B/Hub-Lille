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
					</select></td> &nbsp
					<td><input id="publication-submit" type="submit"
						value="chercher" class="button small" /></td>

				</div>
			</div>

			<h3>Les evenements</h3>
			<br />


			<div
				style="padding-top: 10px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; margin: 0 2% 5% 0;">
				<br/>
			
			<c:if test="${not empty evenements}">
				<c:forEach items="${evenements}" var="evenement">

					<table
						style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; width: 100%;">
						<tbody>
							<tr>
								<td style="padding-left: 15%; padding-top: 2%;"><h5
										class="titre upper">${evenement.titre}</h5>
									<p class="description upper">${evenement.description}</p></td>
								<td rowspan="2"></td>



								<td><table style="border: solid 1px #FFFFFF">
										<tbody>
											<tr>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table></td>
							</tr>
						</tbody>
					</table>
				</c:forEach>
			</c:if>
			<c:if test="${empty evenements}">
				<h5>Aucun événement à venir</h5>
			</c:if>
			</div>
		</section>

	</tiles:putAttribute>
</tiles:insertDefinition>