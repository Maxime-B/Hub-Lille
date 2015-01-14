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
				<form method="get" action= "">
				
				<input type ="text" name ="mot">
				<spring:message code="job.listerJob.submitChercher" var="submit" />
				<input type="submit" value="${submit}" class="radius button" />
				
				</form>
				${mot}
				<br />
				<br />
				<br />
				<table>
				<tr>
						<th><spring:message code="job.listerJob.label.titre" /></th>
						<th><spring:message code="job.listerJob.label.description" /></th>
						
					</tr>
				<c:forEach items="${jobs}" var="a">
					
							<tr>
								<td>${a.titre}
								</td>
								<td>${a.description}
								</td>
								
							</tr>
					</c:forEach>
					</table>
					<br />
				
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>