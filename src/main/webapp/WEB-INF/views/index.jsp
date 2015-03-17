<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
				  		
  								<div class="slide-acceuil" >
								 <img class="slide-acceuil-image" src="${pageContext.request.contextPath}/ressources/photos/${a.images[0] }">
								</div>
				</c:if>
				<c:if test="${fn:length(a.images) lt 1}">
								<div class="slide-acceuil" >
								 <img class="slide-acceuil-image"src="/hublille1/ressources/img/imageNotFound.png"/>
								</div>
				</c:if>
								<div class="orbit-caption">
									<a href="annonce/consulter?ref=${a.id }" style="color: white;"><b>${a.titre}</b> <br/> ${a.description}</a>
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
					<sec:authorize url="/evenement/**">
						<a class="small radius button" href="${pageContext.request.contextPath}/evenement/creer"><spring:message code="accueil.deposeEve" /></a>
					</sec:authorize>
					</div>
				</h3>
				
				<c:if test="${not empty evenements}">
					<ul class="example-orbit" data-orbit>
						<c:forEach items="${evenements}" var="e">

							<li>
							<div class="slide-acceuil" >
								 <img class="slide-acceuil-image"src="${pageContext.request.contextPath}/ressources/img/imageNotFound.png" />
								</div>
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
					<sec:authorize url="/job/**">
						<a class="small radius button" href="${pageContext.request.contextPath}/job/creer"><spring:message code="accueil.deposeJob" /></a>
					</sec:authorize>
					</div>
				</h3>
				
				<c:if test="${not empty jobs}">
					<ul class="example-orbit" data-orbit>
						<c:forEach items="${jobs}" var="j">

							<li>
							<div class="slide-acceuil" >
								 <img class="slide-acceuil-image" src="${pageContext.request.contextPath}/ressources/img/imageNotFound.png" />
								 </div>
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