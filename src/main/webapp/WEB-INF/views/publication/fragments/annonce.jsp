<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="annonce" value="${publication}" />
<div id="annonce-champs" class="row">
	<c:forEach items="${annonce.categorie.champs}" var="champ">
		<c:set var="label" value="${champ.libelle}" />
		<c:set var="value" value="${annonce.lesChamps[champ.libelle]}" />

		<c:if test="${not empty value}">
			<c:set var="frEn" value="${fn:split(label, '-')}" />
			<c:set var="langue">
				<spring:message code="template.langue" />
			</c:set>

			<c:set var="label" value="${frEn[0]}" />
			<c:if test="${langue eq 'en'}">
				<c:set var="label" value="${frEn[1]}" />
			</c:if>

			<c:choose>
				<c:when test="${champ.typeChamp=='TEXTE'}">
					<div class="small-4 columns">
						<strong>${label} : </strong>${value}</div>
				</c:when>

				<c:when test="${champ.typeChamp=='TEXTEAREA'}">
					<div class="small-12 columns">
						<strong>${label} : </strong>${value}</div>
				</c:when>

				<c:when test="${champ.typeChamp=='DATE'}">
					<div class="small-4 columns">
						<strong>${label} : </strong>
						<fmt:parseDate type="date" var="value" value="${value}"
							pattern="yyyy-MM-dd" />
						<fmt:formatDate value="${value}" type="date" dateStyle="long" />
					</div>
				</c:when>

				<c:when test="${champ.typeChamp=='NUMERIQUE'}">
					<div class="small-4 columns">
						<strong>${label} : </strong>
						<fmt:formatNumber value="${value}" type="currency" />
					</div>
				</c:when>
			</c:choose>
		</c:if>
	</c:forEach>
</div>

<ul id="annonce-photos" class="clearing-thumbs" data-clearing>
	<c:forEach items="${annonce.images}" var="image" varStatus="status">
		<c:if test="${status.index < 4}">
			<li>
				<a href="${pageContext.request.contextPath}/ressources/photos/${image}">
				<img
					src="${pageContext.request.contextPath}/ressources/photos/${image}"
					alt="" class="th" />
				</a>
			</li>
		</c:if>
	</c:forEach>
</ul>
