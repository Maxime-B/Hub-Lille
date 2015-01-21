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
				<spring:message code="annonce.creer.titre" />
			</h1>

			<form:form action="creer" method="post" enctype="multipart/form-data"
				modelAttribute="annonce">
				<c:if test='${estUnSucces}'>
					<div class="alert-box success radius">
						<spring:message code="annonce.creer.succes" />
					</div>
				</c:if>
				<c:if test='${!estUnSucces}'>
					<span>&nbsp;</span>
				</c:if>

				<form:input type="hidden" path="categorie" />

				<c:forEach items='${annonce.categorieObject.champs}' var="item"><c:set var="path" value="lesChamps['${item.libelle}']" />
					<c:set var="path" value="lesChamps['${item.libelle}']" />
				
					<form:label path="${path}" cssErrorClass="error">
						<spring:message code="annonce.creer.label.${item.libelle}" />
						<c:if test='${item.obligatoire}'>
							<small><spring:message code="annonce.creer.requis" /></small>
						</c:if>
					 	 :
					</form:label>

					<c:choose>
						<c:when test="${item.typeChamp=='TEXTEAREA'}">
							<form:textarea rows="15" path="${path}" cssErrorClass="error" />
						</c:when>

						<c:when test="${item.typeChamp=='TEXTE'}">
							<form:input type="text" path="${path}" cssErrorClass="error" />
						</c:when>

						<c:when test="${item.typeChamp=='DATE'}">
							<form:input type="date" path="${path}" cssErrorClass="error" />
						</c:when>

						<c:when test="${item.typeChamp=='NUMERIQUE'}">
							<form:input type="number" min="0" path="${path}"
								cssErrorClass="error" />
						</c:when>

						<c:when test="${item.typeChamp=='IMAGE'}">
							<input type="file" accept="image/*" name="${item.libelle}" />
						</c:when>
					</c:choose>

					<form:errors path="${path}" cssClass="error" />
					<br />
				</c:forEach>

				<input type="submit" value="Valider" />
			</form:form>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>