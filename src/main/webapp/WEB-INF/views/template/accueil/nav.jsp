<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/annonce">Annonces</a>
		<ul style="list-style-type: none;">
			<li><a
				href="${pageContext.request.contextPath}/annonce/categorie/choisir">Creer
					une annonce</a></li>
			<li><a href="${pageContext.request.contextPath}/annonce">Lister
					les annonces</a></li>
		</ul></li>
	<li><a href="${pageContext.request.contextPath}/evenement">Evenement</a></li>
	<ul style="list-style-type: none;">
		<li><a href="${pageContext.request.contextPath}/evenement/creer">Creer
				un evenement</a></li>
		<li><a href="${pageContext.request.contextPath}/evenement">Lister
				les evenements</a></li>
	</ul>
	</li>
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
	<li><a
		href="${pageContext.request.contextPath}/admin/categorieAdmin">Administration</a></li>
	<ul style="list-style-type: none;">
		<li><a
			href="${pageContext.request.contextPath}/admin/categorieAdmin">Administrer
				catégorie</a></li>
	</ul>
	</li>
</ul>