<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title"><spring:message code="job.listerJob.titre" /></tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<br />
				<h3><spring:message code="job.listerJob.titre" /></h3>
				<br />
	
			<div align="right"
					style="padding-top: 10px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px; border: solid 1px #000000; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px;">
					<form method="get" action="">
					<table>
								<tr>
									<td><input type="text" name="mot"
										style="width: 170px; height: 30px"></td>

							
									<td><spring:message code="job.listerJob.submitChercher" var="submit" />
				<input type="submit" value="${submit}" class="radius button" style="padding: 10px 50px" /></td>
								</tr>
							</table>
						
	
${mot}
					</form>
				
					<c:if test="${not empty jobs}">
						<c:forEach items="${jobs}" var="a">
							<div 
								style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #000000; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px;">
								<div align ="left">
									<u><span
											style="font-family: Comic Sans MS; font-weight: bold; color: #000000;">
												${a.titre} </span></u>
								</div>
								</br>
								<div align ="left">
									<td>${a.description}</td>
									

								</div>
								
									<td width=50% bgcolor="#FFFFFF"></td>
									<td bgcolor="#FFFFFF">
										<form method="get" action="consulter">
											<td><spring:message code="job.listerJob.submitPostuler" var="submit" />
											<input type="hidden" name="ref" value="${a.id}"> <input
												type="submit" value="${submit}" style="padding: 7px 25px"
												class="radius button" />

										</form>
									</td>
								
							</div>
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