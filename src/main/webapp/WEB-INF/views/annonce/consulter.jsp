<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">
		<spring:message code="annonce.creer.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">
			<h1>
				Consulter une annonce
			</h1>
			<table>
			<tr>
				<td>Categorie</td>
				<td> ${annonce.categorie.nom }</td>
			</tr>
			<tr>
				<td>Type d'offre</td>
				<td> ${annonce.type }</td>
				
			</tr>
			
				<c:forEach items='${annonce.lesChamps}' var="item">
				
						<tr>
				<td>${item.key}</td>
				<td>${item.value}</td>
				
			</tr>
					
				</c:forEach>
						<tr>
				<td>Contact</td>
				<td><A HREF="mailto:${annonce.utilisateur.email}">${annonce.utilisateur.email}</A></td>
				
			</tr>
			</table>
			
			<a href="/">Retour à la page d'accueil</a>
				
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>