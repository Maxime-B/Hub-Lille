<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title" value="${job.titre}" />

	<tiles:putAttribute name="main">
		<section class="section">
			<c:if test="${!empty param['estUnSucces']}">
				<div class="alert-box success radius">
					<spring:message code="job.consulter.jobMot" /> ${job.titre} <spring:message code="job.consulter.estConsultable" />. <br /> <spring:message code="job.consulter.envoi" />
					${job.utilisateur.email}
				</div>
			</c:if>
			<h1>${job.titre}</h1><br/>

			<!-- message si redirection -->
			<!--<c:if test="${!empty param['estUnSucces']}">
				<div class="alert-box success radius">Le job ${job.titre} est maintenant consultable.</div>
			</c:if>-->
			<div class="row">
				<div style="border: 2px double #ddd;padding: 5%;"class="row">
					<div class="small-8 columns" style="width: 100%;">
						<div class="row">
							<p class="titre upper"><spring:message code="job.consulter.description" /></p>
							<p class="upper description" style="word-wrap: break-word;margin-left: 2%;text-align: justify;">${job.description}</p>
						</div>
						<br/>
						<div class="row">
							<p class="titre upper"><spring:message code="job.consulter.modalite" /></p>
							<p class="upper description" style="word-wrap: break-word;margin-left: 2%;text-align: justify;">${job.modalite}</p>
						</div>
						<br/>
						
						<div>
							<form action="contacter" method="get">
								<input type="hidden" name="ref" value="${job.id}" /> <input
									type="submit" name="Contacter" value="<spring:message code="job.consulter.postuler" />"
									class="radius button" style="padding: 10px 50px;float: right;">
							</form>
						</div>
						

					</div>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>
