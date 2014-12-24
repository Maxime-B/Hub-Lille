<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">choix catégorie</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				choix catégorie

				<form method="get"
					action="${pageContext.request.contextPath}/annonce/creer">
					<select name="categorieChoisie">
						<c:forEach items="${categories}" var="item">
							<option value="${item.nom}">${item.nom}</option>
						</c:forEach>
					</select> <input type="submit" value="OK ">
				</form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>