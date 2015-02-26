<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title">
		<spring:message code="job.listerJob.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
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
				<br />
				<h3>
					<spring:message code="job.listerJob.titre" />
				</h3>
				<br />

				<div
					style="padding-top: 10px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; margin: 0 2% 5% 0;">
					<form method="get" action="">
						<table style="border: solid 1px #FFFFFF">
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td style="text-align: right">
									<table>
										<tr>
											<td><input type="text" name="mot"
												style="width: 170px; height: 30px"></td>


											<td><spring:message code="job.listerJob.submitChercher"
													var="submit" /> <input type="submit" value="${submit}"
												class="radius button" style="padding: 10px 50px" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>


					</form>

					<c:if test="${not empty jobs}">
						<c:forEach items="${jobs}" var="a">



							<table
								style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; width: 100%;">
								<tr>
									<td style="padding-left: 15%;padding-top: 2%;"><h5 class="titre upper" >${a.titre}</h5>
										<p class="description upper" style="word-wrap : break-word;width:450px" >${a.description}</p></td>
									<td rowspan="2"></td>



									<td><table style="border: solid 1px #FFFFFF">
											<tr>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</table></td>


								</tr>

								<tr>
								<td width=80% bgcolor="#FFFFFF"></td>
									<td bgcolor="#FFFFFF">
									<form method="get" action="consulter">
									<spring:message
											code="job.listerJob.submitPostuler" var="submit" /> <input
										type="hidden" name="ref" value="${a.id}"> <input
										type="submit" value="${submit}" style="padding: 7px 25px"
										class="radius button" /></td>
									</form>
								</tr>

							</table>

						</c:forEach>
					</c:if>
					<c:if test="${empty jobs}">
						<tr>
							<td colspan="2">aucun job créé</td>
						</tr>
					</c:if>

				</div>

				<br />

			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>