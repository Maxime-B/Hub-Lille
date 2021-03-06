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
							<tbody>
								<tr>
									<td style="padding-left: 15%; padding-top: 2%;"><h5
											class="titre upper">${a.titre}
										</h5></td>
									<td><p class="description upper"
											style="text-align: justify;word-wrap: break-word;font-size: small;font-weight: bold;font-style: normal;">
											${a.description}
										</p></td>
								</tr>
								<tr>
									<td rowspan="2" style="padding-left: 15%;background-color: #FFF"><p class="description upper"
											style="word-wrap: break-word; width: 450px;text-align: justify;">${a.remuneration}</p></td>
								</tr>
								<tr><td></td><td></td></tr>
								<tr>
								<td width=80% bgcolor="#FFFFFF"></td>
									<td bgcolor="#FFFFFF">
									<form method="get" action="consulter">
									 <input
										type="hidden" name="ref" value="${a.id}"> <input
										type="submit" value="<spring:message
											code="job.listerJob.submitPostuler"/>" style="padding: 7px 25px"
										class="radius button" /></td>
									</form>
								</tr>
							</tbody>
							</table>
						</c:forEach>
					</c:if>
					<c:if test="${empty jobs}">
						<tr>
							<td colspan="2"><spring:message code="utilisateur.job.pasJob" /></td>
						</tr>
					</c:if>

				</div>

				<br />

			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>