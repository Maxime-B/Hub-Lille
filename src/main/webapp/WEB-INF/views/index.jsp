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
						<input type="text" placeholder="Value">
					</div>
					<div class="small-3 columns">
						<a href="#" class="button postfix">Go</a>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Annonces -->
		<div class="row">
			<div class="large-8 columns">
				<h4>Les dernieres annonces</h4>
			</div>
			<div class="large-4 columns">
				<a class="small radius button">Deposer une annonce</a>
			</div>
		</div>
		<div class="row">
			<div class="large-10 columns">
				<c:if test="${not empty annonces}">
					<ul class="example-orbit" data-orbit>
						<c:forEach items="${annonces}" var="annonce">
							<li><img
								src="${pageContext.request.contextPath}/ressources/img/imageNotFound.png" />
								<div class="orbit-caption">[${annonce.titre}] ${annonce.description}</div></li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${empty annonces}">
					<tr>
						<td colspan="5">Aucune Annonce</td>
					</tr>
				</c:if>
			</div>
		</div>

		<!-- Jobs -->
		<div class="row">
			<div class="large-8 columns">
				<h4>Les derniers jobs</h4>
			</div>
			<div class="large-4 columns">
				<a class="small radius button">Deposer un job</a>
			</div>
		</div>
		<div class="row">
			<div class="large-10 columns">
				<c:if test="${not empty jobs}">
					<ul class="example-orbit" data-orbit>
						<c:forEach items="${jobs}" var="job">
							<li><img
								src="${pageContext.request.contextPath}/ressources/img/imageNotFound.png" />
								<div class="orbit-caption">[${job.titre}] ${job.description}</div></li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${empty jobs}">
					<tr>
						<td colspan="5">Aucun job</td>
					</tr>
				</c:if>
			</div>
		</div>

		<!-- Events -->
		<div class="row">
			<div class="large-8 columns">
				<h4>Les derniers evenements</h4>
			</div>
			<div class="large-4 columns">
				<a class="small radius button">Deposer un evenement</a>
			</div>
		</div>
		<div class="row">
			<div class="large-10 columns">
				<c:if test="${not empty evenements}">
					<ul class="example-orbit" data-orbit>
						<c:forEach items="${evenements}" var="evenement">
							<li><img
								src="${pageContext.request.contextPath}/ressources/img/imageNotFound.png" />
								<div class="orbit-caption">[${evenement.titre}] ${evenement.description}</div></li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${empty evenements}">
					<tr>
						<td colspan="5">Aucun evenement</td>
					</tr>
				</c:if>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>