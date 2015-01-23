<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="accueil">
	<tiles:putAttribute name="title">Hub Lille 1</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<h1>Hub Lille 1</h1>
			</div>
		</div>

		<div class="row">
			<div class="large-6 columns">
				<div class="row collapse postfix-round">
					<div class="small-9 columns">
						<input type="text" placeholder="Mot a rechercher">
					</div>
					<div class="small-3 columns">
						<a href="#" class="button postfix">Rechercher</a>
					</div>
				</div>
			</div>
		</div>

		<div class="row">

			<!-- Main Content Section -->
			<!-- This has been source ordered to come first in the markup (and on small devices) but to be to the right of the nav on larger screens -->
				<!-- Announces -->
				<p>
				<h3>
					<div class="large-8 columns">
						Dernieres Annonces 
					</div>
					<div class="large-4 columns">
						<a class="small radius button" href="${pageContext.request.contextPath}/annonce/categorie/choisir">Deposer une annonce</a>
					</div>
				</h3>
				
				<c:if test="${not empty annonces}">
					<ul class="example-orbit" data-orbit>
						<c:forEach items="${annonces}" var="a">

							<li>
							<img src="${pageContext.request.contextPath}/ressources/img/imageNotFound.png" />
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
						<td colspan="5">Aucune annonce trouvee</td>
					</tr>
				</c:if>
				</p>
				<br/>
				
				<!-- Events -->
				
				<p>
				<h3>
					<div class="large-8 columns">
						Derniers evenements 
					</div>
					<div class="large-4 columns">
						<a class="small radius button" href="${pageContext.request.contextPath}/evenement/creer">Deposer un evenement</a>
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
						<td colspan="5">Aucun evenement trouve</td>
					</tr>
				</c:if>
				</p>
				<br/>
				
				<!-- Jobs -->
				
				<p>
				<h3>
					<div class="large-8 columns">
						Derniers jobs 
					</div>
					<div class="large-4 columns">
						<a class="small radius button" href="${pageContext.request.contextPath}/job/creer">Deposer un jobs</a>
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
				<c:if test="${empty evenements}">
					<tr>
						<td colspan="5">Aucun job trouve</td>
					</tr>
				</c:if>
				</p>
				<br/>
				

		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>