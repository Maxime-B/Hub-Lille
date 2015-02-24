<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
		<li><a href="${pageContext.request.contextPath}/init">Initialiser la BD</a></li>
</ul>

<h6>Annonces</h6>
<ul class="four side-nav">
	<li><a
				href="${pageContext.request.contextPath}/annonce/categorie/choisir">Creer Annonce </a></li>
			<li><a
				href="${pageContext.request.contextPath}/annonce/listerOffre">Les offres</a></li>

			<li><a href="${pageContext.request.contextPath}/annonce/listerDemande">Les demandes</a></li>
</ul>
<sec:authorize url="/evenement/**">
<h6>Ev�nements</h6>
<!-- evenement -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/evenement/creer">Cr�er un �v�nement</a></li>
	<li><a href="${pageContext.request.contextPath}/evenement">Les �v�nements </a></li>
</ul>
</sec:authorize>
<sec:authorize url="/job/**">
<h6>Jobs</h6>
<!-- job -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/job/creer">Cr�er un job</a></li>
	<li><a href="${pageContext.request.contextPath}/job/listerJob">Les jobs </a></li>
</ul>
</sec:authorize>
<sec:authorize url="/moderateur/**">
<h6>Mod�ration</h6>
<!-- Moderation -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/moderateur">Mod�rer les annonces</a></li>
</ul>
</sec:authorize>
<sec:authorize url="/admin/**">
<h6>Administration</h6>
<!-- Administration -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/admin/droit">G�rer les r�les</a></li>
	<li><a href="${pageContext.request.contextPath}/admin/categorieAdmin">Administrer cat�gorie</a></li>
</ul>
</sec:authorize>

