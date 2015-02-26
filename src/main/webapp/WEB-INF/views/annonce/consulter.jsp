<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title" value="${annonce.titre}" />

	<tiles:putAttribute name="main">
		<section class="section">
			<h1>${annonce.titre}</h1>
			<table style="border: solid 1px #FFFFFF">
				<tr>
					<td></td>
				</tr>
			</table>

			<!-- message si redirection -->
			<c:if test="${!empty param['estUnSucces']}">
				<div class="alert-box success radius">
					<spring:message code="annonce.consulter.motAnnonce" />
					${annonce.titre}
					<spring:message code="annonce.consulter.estPublie" />
					<br />
					<spring:message code="annonce.consulter.envoiMessage" />
					${annonce.utilisateur.email}
				</div>
			</c:if>
			<c:if test="${!empty param['estModifie']}">
				<div class="alert-box success radius">
					<spring:message code="annonce.consulter.motAnnonce" />
					${annonce.titre}
					<spring:message code="annonce.consulter.estModifie" />
				</div>
			</c:if>
			<div
				style="padding-top: 10px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; margin: 2% 3% 2% 0%;">
				<div class="row">
					<div class="row">

						<div class="small-8 columns">
							<div class="row">

								<b><spring:message code="annonce.consulter.typeAnnonce" /></b>
								<p>
									<spring:message code="annonce.${annonce.type }" />
								</p>
							</div>
							<div class="row">
								<b><spring:message code="annonce.consulter.description" /></b>
								<p>${annonce.description}</p>
							</div>

							<div class="row">
								<b><spring:message code="annonce.consulter.categorie" /></b>
								<c:set var="tmp" value="${annonce.categorie.nom }" />
								<c:set var="libelle" value="${fn:split(tmp, '-')}" />
								<c:set var="langue">
									<spring:message code="template.langue" />
								</c:set>
								<c:if test="${langue=='fr'}">
									<p>${libelle[0]}</p>
								</c:if>
								<c:if test="${langue=='en'}">
									<p>${libelle[1]}</p>
								</c:if>
							</div>
							<c:forEach items='${annonce.lesChamps}' var="item">
								<c:set var="tmp" value="${item.key}" />
								<c:set var="libelle" value="${fn:split(tmp, '-')}" />
								<c:if test="${langue=='fr'}">
									<div class="row">
										<b>${libelle[0]}</b>
										<p>${item.value}</p>
									</div>
								</c:if>
								<c:if test="${langue=='en'}">
									<div class="row">
										<b>${libelle[1]}</b>
										<p>${item.value}</p>
									</div>
								</c:if>
							</c:forEach>
							<div class="row">
								<spring:message code="annonce.consulter.publieLe" />
								${annonce.datepublication} (
								<spring:message code="annonce.consulter.valable" />
								${annonce.finpublication})
							</div>
						</div>
						<div class="small-4 columns">
							<div class="row">
								<c:if test="${fn:length(annonce.images) gt 0}">
									<ul class="clearing-thumbs" data-clearing>
										<c:forEach items='${annonce.images}' var="image">
											<li><a
												href="${pageContext.request.contextPath}/ressources/photos/${image }"><img
													class="th"
													src="${pageContext.request.contextPath}/ressources/photos/${image }"></a></li>

										</c:forEach>
									</ul>

								</c:if>
								<CENTER>
									<c:if test="${fn:length(annonce.images) lt 1}">
										<img src="/hublille1/ressources/img/pas-dimage.png"
											width="200" height="450" style="border: solid 1px #EAEAEA;" />

									</c:if>
									<CENTER>
							</div>
							<br /> <br />
							<CENTER>

								<CENTER>
									<br /> <br />

									<form action="contacter" method="get">
										<input type="hidden" name="ref" value="${annonce.id}" />
										<CENTER>
											<input type="submit" name="Contacter"
												value="<spring:message code="annonce.consulter.contacter" />"
												class="radius button" style="padding: 10px 50px" />
											<CENTER>
									</form>
									<CENTER>
										<a href="signaler?ref=${annonce.id}"><spring:message
												code="annonce.consulter.signaler" /></a>
									</CENTER>
						</div>
					</div>

				</div>
			</div>




			<br />
		</section>
	</tiles:putAttribute>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title" value="${annonce.titre}" />

	<tiles:putAttribute name="main">
		<section class="section">
			<h1>${annonce.titre}</h1>
			<table style="border: solid 1px #FFFFFF">
				<tr>
					<td></td>
				</tr>
			</table>

			<!-- message si redirection -->
			<c:if test="${!empty param['estUnSucces']}">
				<div class="alert-box success radius">
					<spring:message code="annonce.consulter.motAnnonce" />
					${annonce.titre}
					<spring:message code="annonce.consulter.estPublie" />
					<br />
					<spring:message code="annonce.consulter.envoiMessage" />
					${annonce.utilisateur.email}
				</div>
			</c:if>
			<c:if test="${!empty param['estModifie']}">
				<div class="alert-box success radius">
					<spring:message code="annonce.consulter.motAnnonce" />
					${annonce.titre}
					<spring:message code="annonce.consulter.estModifie" />
				</div>
			</c:if>
			<div
				style="padding-top: 10px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; margin: 2% 3% 2% 0%;">
				<div class="row">
					<div class="row">

						<div class="small-8 columns">
							<div class="row">

								<b><spring:message code="annonce.consulter.typeAnnonce" /></b>
								<p>
									<spring:message code="annonce.${annonce.type }" />
								</p>
							</div>
							<div class="row">
								<b><spring:message code="annonce.consulter.description" /></b>
								<p>${annonce.description}</p>
							</div>

							<div class="row">
								<b><spring:message code="annonce.consulter.categorie" /></b>
								<c:set var="tmp" value="${annonce.categorie.nom }" />
								<c:set var="libelle" value="${fn:split(tmp, '-')}" />
								<c:set var="langue">
									<spring:message code="template.langue" />
								</c:set>
								<c:if test="${langue=='fr'}">
									<p>${libelle[0]}</p>
								</c:if>
								<c:if test="${langue=='en'}">
									<p>${libelle[1]}</p>
								</c:if>
							</div>
							<c:forEach items='${annonce.lesChamps}' var="item">
								<c:set var="tmp" value="${item.key}" />
								<c:set var="libelle" value="${fn:split(tmp, '-')}" />
								<c:if test="${langue=='fr'}">
									<div class="row">
										<b>${libelle[0]}</b>
										<p>${item.value}</p>
									</div>
								</c:if>
								<c:if test="${langue=='en'}">
									<div class="row">
										<b>${libelle[1]}</b>
										<p>${item.value}</p>
									</div>
								</c:if>
							</c:forEach>
							<div class="row">
								<spring:message code="annonce.consulter.publieLe" />
								${annonce.datepublication} (
								<spring:message code="annonce.consulter.valable" />
								${annonce.finpublication})
							</div>
						</div>
						<div class="small-4 columns">
							<div class="row">
								<c:if test="${fn:length(annonce.images) gt 0}">
									<ul class="clearing-thumbs" data-clearing>
										<c:forEach items='${annonce.images}' var="image">
											<li><a
												href="${pageContext.request.contextPath}/ressources/photos/${image }"><img
													class="th"
													src="${pageContext.request.contextPath}/ressources/photos/${image }"></a></li>

										</c:forEach>
									</ul>

								</c:if>
								<CENTER>
									<c:if test="${fn:length(annonce.images) lt 1}">
										<img src="/hublille1/ressources/img/pas-dimage.png"
											width="200" height="450" style="border: solid 1px #EAEAEA;" />

									</c:if>
									<CENTER>
							</div>
							<br /> <br />
							<CENTER>

								<CENTER>
									<br /> <br />

									<form action="contacter" method="get">
										<input type="hidden" name="ref" value="${annonce.id}" />
										<CENTER>
											<input type="submit" name="Contacter"
												value="<spring:message code="annonce.consulter.contacter" />"
												class="radius button" style="padding: 10px 50px" />
											<CENTER>
									</form>
									<CENTER>
										<a href="signaler?ref=${annonce.id}"><spring:message
												code="annonce.consulter.signaler" /></a>
									</CENTER>
						</div>
					</div>

				</div>
			</div>




			<br />
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>