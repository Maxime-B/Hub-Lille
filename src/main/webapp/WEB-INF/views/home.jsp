<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="accueil">
	<tiles:putAttribute name="title">Liens</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<h1>accueil</h1>
				<div><a href="${pageContext.request.contextPath}/init">initialiser la base de données</a></div>
				<div><a href="${pageContext.request.contextPath}/index">vraie page d'accueil</a></div>
				<div>
					<h2>Annonce</h2>
					<a
						href="${pageContext.request.contextPath}/annonce/categorie/choisir">Publier
						une annonce</a>
				</div>
				<div>
					<h2>Evenement</h2>
					<div>
						<a href="${pageContext.request.contextPath}/evenement/creer">Publier
							un événement</a>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>