<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">
		<spring:message code="annonce.creer.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">

			<div style="text-align: left">
				<h3>
					<spring:message code="annonce.creer.titre" />
				</h3>

			</div>

			<form:form action="creer" method="post" enctype="multipart/form-data"
				modelAttribute="annonce">
				<c:if test='${estUnSucces}'>
					<div class="alert-box success radius">
						<spring:message code="annonce.creer.succes" />
					</div>
				</c:if>
				<c:if test='${!estUnSucces}'>
					<span> </span>
				</c:if>

				<form:input type="hidden" path="categorie"
					value="${annonce.categorieObject.nom}" />
				<form:errors path="categorie" cssClass="error" />

				<form:input type="hidden" path="type" value="${annonce.type}" />

				<form:label path="titre" cssErrorClass="error">
					<spring:message code="annonce.creer.label.titre" />
				</form:label>
				<form:input type="text" path="titre" placeholder="50 char max"
					cssErrorClass="error" maxlength="50"/>
				<form:errors path="titre" cssClass="error" />

				<form:label path="description" cssErrorClass="error">
					<spring:message code="annonce.creer.label.description" />
				</form:label>
				<form:textarea path="description" rows="5" cols="30"
					placeholder="200 caractère max" maxlength="200" cssErrorClass="error"></form:textarea>
				<form:errors path="description" cssClass="error" />

				<c:forEach items='${annonce.categorieObject.champs}' var="item">
					<c:set var="path"
						value="${fn:toLowerCase(item.typeChamp)}['${item.libelle}']" />
					<div>
						<form:label path="${path}" cssErrorClass="error">
							<c:set var="tmp" value="${item.libelle}" />
							<c:set var="libelle" value="${fn:split(tmp, '-')}" />
							<c:set var="langue">
								<spring:message code="template.langue" />
							</c:set>
							<c:if test="${langue=='fr'}">
							${libelle[0]}
							</c:if>
							<c:if test="${langue=='en'}">
							${libelle[1]}
						</c:if>
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
								<form:hidden class="datepicker" required="required"
									path="${path}" />
								<noscript>
									<form:input class="datepicker" required="required"
										path="${path}" type="date"/>
								</noscript>
							</c:when>

							<c:when test="${item.typeChamp=='NUMERIQUE'}">
								<form:input min="0" path="${path}" cssErrorClass="error"
									cssClass="spinner" />
							</c:when>


						</c:choose>

						<form:errors path="${path}" cssClass="error" />
					</div>
					<br />
				</c:forEach>
				<div>
					<div id="photo"></div>
					<div class="row">
						<button type='button' class="radius button"
							onclick="ajouterPhoto();">
							<spring:message code="annonce.creer.photo" />
						</button>
					</div>
				</div>


				<div style="text-align: right">
					<spring:message code="annonce.creer.submit" var="submit" />

					<input type="submit" value="${submit}" class="radius button" />
				</div>
			</form:form>
		</section>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
		<script src="<c:url value="/ressources/js/creerAnnonce.js"/>"></script>

	</tiles:putAttribute>
</tiles:insertDefinition>