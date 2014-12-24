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
				<ul class="example-orbit" data-orbit>
					<li><img
						src="${pageContext.request.contextPath}/ressources/img/satelite-orbit.jpg"
						alt="slide 1" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
					<li class="active"><img
						src="${pageContext.request.contextPath}/ressources/img/andromeda-orbit.jpg"
						alt="slide 2" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
					<li><img
						src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg"
						alt="slide 3" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
					<li><img
						src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg"
						alt="slide 4" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
				</ul>
			</div>
		</div>

		<!-- Annonces -->
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
				<ul class="example-orbit" data-orbit>
					<li><img
						src="${pageContext.request.contextPath}/ressources/img/satelite-orbit.jpg"
						alt="slide 1" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
					<li class="active"><img
						src="${pageContext.request.contextPath}/ressources/img/andromeda-orbit.jpg"
						alt="slide 2" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
					<li><img
						src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg"
						alt="slide 3" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
					<li><img
						src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg"
						alt="slide 4" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
				</ul>
			</div>
		</div>

		<!-- Annonces -->
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
				<ul class="example-orbit" data-orbit>
					<li><img
						src="${pageContext.request.contextPath}/ressources/img/satelite-orbit.jpg"
						alt="slide 1" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
					<li class="active"><img
						src="${pageContext.request.contextPath}/ressources/img/andromeda-orbit.jpg"
						alt="slide 2" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
					<li><img
						src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg"
						alt="slide 3" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
					<li><img
						src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg"
						alt="slide 4" />
						<div class="orbit-caption">[Annonce] Tickets Voyage dans
							l'espace !</div></li>
				</ul>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>