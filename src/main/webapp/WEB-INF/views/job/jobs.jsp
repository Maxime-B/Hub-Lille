<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title">Confirmation de la pulication du job</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
		
				<br />
				<B></B><spring:message code="job.jobs.confirmation" /></B>
				<br />
			

					
						<B><spring:message code="job.creer.label.titre" /> : </B>
							<td>${titre}</td>
							<br/>
						<B><spring:message code="job.creer.label.description" /> : </B>
							<td>${description}</td>
							<br/>
						<B> <spring:message code="job.creer.label.remuneration" /> : </B>
							<td>${remuneration}</td>
							<br/>

			

				

					

					
				
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
