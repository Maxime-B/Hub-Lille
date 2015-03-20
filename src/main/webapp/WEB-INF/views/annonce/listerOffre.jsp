<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">
		<spring:message code="annonce.lister.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">

		<div class="row">

			<br />
			<h4>
				<spring:message code="annonce.lister.titreOffre" />
			</h4>
			<br />

			<!--  <div style="width: 450px; height: 250px; border: 1px solid black;-webkit-border-radius: 10px;-moz-border-radius: 10px; border-radius: 10px;">-->

			<div
				style="padding-top: 10px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; margin: 0 2% 5% 0;">
				<form method="get" action="">
					<!--<form method="get" action= "annonce/listerCategorie">-->
					<div style="text-align: right">

						<table>
							<tr>
								<td><input type="text" name="motCle"
									style="width: 170px; height: 30px"></td>

								<td><select name="categorie"
									style="width: 200px; height: 35px">
										<option value= "">Toutes catégories</option>
										<c:forEach items="${categories}" var="item">
											<c:set var="tmp" value="${item.nom}" />
											<c:set var="libelle" value="${fn:split(tmp, '-')}" />
											<c:set var="langue">
												<spring:message code="template.langue" />
											</c:set>
											<c:if test="${langue=='fr'}">
												<option value="${item.nom}">${libelle[0]}</option>
											</c:if>
											<c:if test="${langue=='en'}">
												<option value="${item.nom}">${libelle[1]}</option>
											</c:if>
										</c:forEach>
								</select></td>

								<td><input type="submit"
									value="<spring:message code="annonce.lister.rechercher" />"
									class="radius button" style="padding: 10px 50px" /></td>
							</tr>
						</table>
					</div>

				</form>
				<c:set var="tmp" value="${categorie}" />
				<c:set var="libelle" value="${fn:split(tmp, '-')}" />
				<c:if test="${langue=='fr'}">
									${motCle} ${libelle[0]} <br />
					<br /> &nbsp
								</c:if>
				<c:if test="${langue=='en'}">
									${motCle} ${libelle[1]} <br />
					<br /> &nbsp
								</c:if>
				<c:if test="${not empty annonces}">
					<table width="100%" id="datatable">
						<thead>
							<tr>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${annonces}" var="a">
								<tr>
									<td>
										<table
											style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; width: 100%;">
											<tr>
												<td width=25% rowspan="2" style="text-align: center;">

													<c:if test="${fn:length(a.images) gt 0}">


														<img alt="/hublille1/ressources/img/pas-dimage.png"
															class="th"
															src="${pageContext.request.contextPath}/ressources/photos/${a.images[0] }">

													</c:if> <c:if test="${fn:length(a.images) lt 1}">
														<img class="th"
															src="/hublille1/ressources/img/pas-dimage.png" />

													</c:if>
												</td>

												<td><p class="titre upper">${a.titre}</p>
													<p class="description upper">${a.description}</p></td>

												<td align=right></td>

											</tr>
											<tr>
												<td width=50% bgcolor="#FFFFFF"></td>
												<td width=25% bgcolor="#FFFFFF"><a
													href="consulter?ref=${a.id }">

														<button class="radius button">
															<spring:message code="annonce.lister.details" />
														</button>

												</a></td>
											</tr>
										</table>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!--  <p>
							
							<div align=center>
								<h6>
									<u><span
										style="font-family: Comic Sans MS; font-weight: bold; color: #000000;">
											${a.titre} </span></u>
											${a.lesChamps['prix']} 
								</h6>
							</div>
							<div align=left>
								<img
									src="${pageContext.request.contextPath}/ressources/img/pas-dimage.png"
									width="200" height="450" style="border: solid 1px;" />
								<div class="orbit-caption">
									<!--<c:forEach items="${a.lesChamps}" var="entry">
										<%--${entry.key}--%>${entry.value}
									</c:forEach>-->
					<!--  </div>
							</div>
							<form method="get" action="annonce/consulter">
								<div align=right>
									<input type="hidden" name="ref" value="${a.id}"> <input
										type="submit" value="details" style="padding: 8px 40px"
										class="radius button" />
								</div>
							</form>
					
							</p>-->


				</c:if>
				<c:if test="${empty annonces}">
					<tr>
						<td colspan="2"><spring:message
								code="annonce.lister.pasAnnonce" /></td>
					</tr>
				</c:if>

			</div>
		</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">

		<script src="<c:url value="/ressources/js/datatablesLister.js"/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>