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
					Le job ${job.titre} est maintenant consultable. <br /> Tous les
					messages seront addressés à l'adresse suivante :
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
							<p class="titre upper">Description</p>
							<p class="upper description" style="word-wrap: break-word;margin-left: 2%;">${job.description}</p>
						</div>
						<br/>
						<div class="row">
							<p class="titre upper">Modalités pour postuler :</p>
							<p class="upper description" style="word-wrap: break-word;margin-left: 2%;">${job.modalite}</p>
						</div>
						<br/>
						
						<div>
							<form action="contacter" method="get">
								<input type="hidden" name="ref" value="${job.id}" /> <input
									type="submit" name="Contacter" value="Postuler"
									class="radius button" style="padding: 10px 50px;float: right;">
							</form>
						</div>
						

					</div>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>
