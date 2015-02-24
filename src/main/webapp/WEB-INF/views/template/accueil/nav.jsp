<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
		<li><a href="${pageContext.request.contextPath}/init">Initialiser la BD</a></li>
</ul>

<h6>Annonces</h6>
<ul class="four side-nav">
	<li><a
				href="${pageContext.request.contextPath}/annonce/categorie/choisir">Déposer une Annonce </a></li>
			<li><a
				href="${pageContext.request.contextPath}/annonce/listerOffre">Les offres</a></li>

			<li><a href="${pageContext.request.contextPath}/annonce/listerDemande">Les demandes</a></li>
</ul>
<sec:authorize url="/evenement/**">
<h6>Evénements</h6>
<!-- evenement -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/evenement/creer">Créer un événement</a></li>
	<li><a href="${pageContext.request.contextPath}/evenement">Les événements </a></li>
</ul>
</sec:authorize>
<sec:authorize url="/job/**">
<h6>Jobs</h6>
<!-- job -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/job/creer">Créer un job</a></li>
	<li><a href="${pageContext.request.contextPath}/job/listerJob">Les jobs </a></li>
</ul>
</sec:authorize>
<sec:authorize url="/moderateur/**">
<h6>Modération</h6>
<!-- Moderation -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/moderateur">Modérer les annonces</a></li>
</ul>
</sec:authorize>
<sec:authorize url="/admin/**">
<h6>Administration</h6>
<!-- Administration -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/admin/droit">Gérer les rôles</a></li>
	<li><a href="${pageContext.request.contextPath}/admin/categorieAdmin">Administrer les catégories</a></li>
</ul>
</sec:authorize>

