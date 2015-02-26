<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tiles:insertDefinition name="accueil">
	<tiles:putAttribute name="title">Hub Lille 1</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<h1>Hub Lille 1</h1>
			</div>
		</div>

		
		<div class="row">

			<!-- Main Content Section -->
			<!-- This has been source ordered to come first in the markup (and on small devices) but to be to the right of the nav on larger screens -->
				<!-- Announces -->
				<p>
				<h3>
					<div class="large-8 columns">
						<spring:message code="accueil.dernAnn" /> 
					</div>
					<div class="large-4 columns">
						<a class="small radius button" href="${pageContext.request.contextPath}/annonce/categorie/choisir"><spring:message code="accueil.deposeAnn" /></a>
					</div>
				</h3>
				
				<c:if test="${not empty annonces}">
					<ul class="example-orbit" data-orbit>
						<c:forEach items="${annonces}" var="a">

							<li>
							<c:if test="${fn:length(a.images) gt 0}">
				  		
  							
								 <img class="th"src="${pageContext.request.contextPath}/ressources/photos/${a.images[0] }">
	
				</c:if>
				<c:if test="${fn:length(a.images) lt 1}">
							  <img src="/hublille1/ressources/img/pas-dimage.png" width="200" height="450" style="border: solid 1px;"/>

				</c:if>
								<div class="orbit-caption">
									<c:forEach items="${a.lesChamps}" var="entry">
										<%--${entry.key}--%>${entry.value}
									</c:forEach>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${empty annonces}">
					<tr>
						<td colspan="5"><spring:message code="accueil.pasAnnonce" /></td>
					</tr>
				</c:if>
				</p>
				<br/>
				
				<!-- Events -->
				
				<p>
				<h3>
					<div class="large-8 columns">
						<spring:message code="accueil.dernEve" /> 
					</div>
					<div class="large-4 columns">
						<a class="small radius button" href="${pageContext.request.contextPath}/evenement/creer"><spring:message code="accueil.deposeEve" /></a>
					</div>
				</h3>
				
				<c:if test="${not empty evenements}">
					<ul class="example-orbit" data-orbit>
						<c:forEach items="${evenements}" var="e">

							<li>
							<img src="${pageContext.request.contextPath}/ressources/img/imageNotFound.png" />
								<div class="orbit-caption">
								[${e.titre}] ${e.description}
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${empty evenements}">
					<tr>
						<td colspan="5"><spring:message code="accueil.pasEvenement" /></td>
					</tr>
				</c:if>
				</p>
				<br/>
				
				<!-- Jobs -->
				
				<p>
				<h3>
					<div class="large-8 columns">
						<spring:message code="accueil.dernJob" />
					</div>
					<div class="large-4 columns">
						<a class="small radius button" href="${pageContext.request.contextPath}/job/creer"><spring:message code="accueil.deposeJob" /></a>
					</div>
				</h3>
				
				<c:if test="${not empty jobs}">
					<ul class="example-orbit" data-orbit>
						<c:forEach items="${jobs}" var="j">

							<li>
							<img src="${pageContext.request.contextPath}/ressources/img/imageNotFound.png" />
								<div class="orbit-caption">
									[${j.titre}] ${j.description}
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${empty jobs}">
					<tr>
						<td colspan="5"><spring:message code="accueil.pasJob" /></td>
					</tr>
				</c:if>
				</p>
				<br/>
				

		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>