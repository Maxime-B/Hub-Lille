<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">Les annonces</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<br />
				<h3><spring:message code="annonce.lister.titre" /></h3>
				<br />
					<form method="get" action= "">
				<!--<form method="get" action= "annonce/listerCategorie">-->
						<div  style="text-align:right">
						 <select  name="categorie" style="width:130px">
						<option> Biens </option>
						<option> Covoiturage </option>
						</select>
					<!-- 	<select name="categorie">
						<c:forEach items="${lesCategories}" var="item">
							<option value="${item.nom}">${item.nom}</option>
						</c:forEach>
					</select> -->
				&nbsp
				<input type="submit" value="chercher"  class="radius button" />
				</div>
				</form>
				
				${categorie}
				<br />
				<br />
				<c:forEach items="${annonces}" var="a">
					<table>
						<tr>
							<th>#${a.id}</th>
						</tr>
						<c:forEach items="${a.lesChamps}" var="entry">
							<tr>
								<td>${entry.key}
								<td />
								<td>${entry.value}
								<td />
							</tr>
						</c:forEach>
					</table>
					<br />
				</c:forEach>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>