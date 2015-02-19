<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/annonce">Annonces</a>
		<ul style="list-style-type: none;">
			<li><a
				href="${pageContext.request.contextPath}/annonce/categorie/choisir">Creer
					une annonce</a></li>
			<li><a href="${pageContext.request.contextPath}/annonce">Lister
					les annonces</a></li>
		</ul></li>

<!-- evenement -->
	<li><a href="${pageContext.request.contextPath}/evenement">Evenement</a></li>
	<li><ul style="list-style-type: none;">
<sec:authorize url="/evenement/creer">
		<li><a href="${pageContext.request.contextPath}/evenement/creer">Creer
				un evenement</a></li>
</sec:authorize>
		<li><a href="${pageContext.request.contextPath}/evenement">Lister
				les evenements</a></li>
	</ul></li>
<!-- fin evenement -->
	
	<li><a href="${pageContext.request.contextPath}/job/listerJob">Jobs</a></li>
	<ul style="list-style-type: none;">
		<li><a href="${pageContext.request.contextPath}/job/creer">Creer
				un job</a></li>
		<li><a href="${pageContext.request.contextPath}/job/listerJob">Lister
				les jobs</a></li>
	</ul>
	</li>
	<li><a href="${pageContext.request.contextPath}/init"> #
			Initialisation de la BD</a></li>
</ul>

<h6>Administration<h6>
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/admin/droit">Gérer les roles</a>
		
	<li><a
		href="${pageContext.request.contextPath}/admin/categorieAdmin">Categorie</a></li>
	<ul style="list-style-type: none;">
		<li><a
			href="${pageContext.request.contextPath}/admin/categorieAdmin">Administrer
				catégorie</a></li>
	</ul>
	</li>
</ul>

<h6>Modération<h6>
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/moderateur">Modérer les annonces</a></li></ul>