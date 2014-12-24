<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title"><spring:message
				code="annonce.creer.titre" /></tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">
				Creer une annonce
				<form method="post">
					<c:forEach items="${lesChamps}" var="item">
						<c:choose>
							<c:when test="${item.typeChamp=='TEXTE'}">
    ${item.libelle} : <input type="text" name="${item.libelle}">
							</c:when>
							<c:otherwise>
    sniiiif
  </c:otherwise>
						</c:choose>

						<br />
					</c:forEach>
					<input type="submit" value="Publier" />
				</form>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>