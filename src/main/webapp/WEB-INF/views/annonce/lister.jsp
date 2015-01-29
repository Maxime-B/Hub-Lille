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
				<h4>
					<spring:message code="annonce.lister.titre" />
				</h4>
				<br />
				<!-- <div style="text-align: right">
					<table>
						<tr>
							<td><spring:message code="annonce.lister.titre" /></td>
							<td><input type="text" name="motGeneral"
								style="width: 170px; height: 30px"></td>

							<td><select name="publication"
								style="width: 200px; height: 35px">
									<option>publications</option>
									<option>annonces</option>
									<option>jobs</option>
									<option>evenements</option>
							</select></td>
							<td><input type="submit" value="chercher"  style="padding: 10px 60px" /></td>
						</tr>
					</table>
				</div>-->
				<div
					style="padding-top: 10px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px; border: solid 1px #000000">
					<form method="get" action="">
						<!--<form method="get" action= "annonce/listerCategorie">-->
						<div style="text-align: right">
							<table>
								<tr>
									<td><input type="text" name="motCle"
										style="width: 170px; height: 30px"></td>

									<td><select name="categorie"
										style="width: 200px; height: 35px">
											<option></option>
											<c:forEach items="${categories}" var="item">
												<option value="${item.nom}">${item.nom}</option>
											</c:forEach>
									</select></td>
									<td><input type="submit" value="chercher"
										class="radius button"  style="padding: 10px 50px"/></td>
								</tr>
							</table>
						</div>

					</form>
					${motCle} ${categorie} <br /> <br /> &nbsp
					<c:if test="${not empty annonces}">
					<c:forEach items="${annonces}" var="a">
						<div
							style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #000000">

							<p>
							<div align=center>
								<h6>
									<u><span
										style="font-family: Comic Sans MS; font-weight: bold; color: #000000;">
											${a.titre} </span></u>
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
								</div>
							</div>
							<form method="get" action="annonce/consulter">
								<div align=right>
									<input type="hidden" name="ref" value="${a.id}"> <input
										type="submit" value="details" style="padding: 8px 40px"
										class="radius button" />
								</div>
							</form>
							<!-- <div class="orbit-caption">
									<c:forEach items="${a.lesChamps}" var="entry">
										<%--${entry.key}--%>${entry.value}
									</c:forEach>
								</div>-->
							</p>

						</div>

						<br />
					</c:forEach>
						</c:if>
				<c:if test="${empty annonces}">
					<tr>
						<td colspan="2">aucune annonce créée</td>
					</tr>
				</c:if>
					
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>