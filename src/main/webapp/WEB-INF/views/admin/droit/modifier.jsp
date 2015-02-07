<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		Gérer les roles des utilisateurs
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<h1>Gérer les roles des utilisateurs</h1>

		<p class="ui-state-highlight ui-corner-all panel">
			<strong><span class="ui-icon ui-icon-info"></span> Note</strong><br/>
				<span>SUPER_ADMIN a tous les pouvoirs.</span><br/>
				<span>SUPER_MODERATEUR a tous les pouvoirs sauf celui de changer
					les roles des utilisateurs</span>
		</p>

		<c:if test="${estUnSucces}">
			<p class="alert-box success radius">
				${utilisateur.login} a maintenant les droits : ${utilisateur.droits}
			</p>
		</c:if>
		<c:if test="${!estUnSucces}">
			<p> </p>
		</c:if>

		<div class="row">
			<section class="section small-6 columns">
				<form:form method="post" modelAttribute="utilisateur">
					<fieldset>
						<legend>Modifier le rôle d'un utilisateur</legend>

						<form:label path="login">login</form:label>
						<form:input path="login" />

						<fieldset class='row'>
							<legend>droits</legend>

							<c:forEach items="${utilisateur.droits}" var="droitValeur"
								varStatus="status">
								<form:select path="droits[${status.index}]">
									<c:forEach items="${droits}" var="droitPossible">
										<form:option value="${droitPossible}">${droitPossible}</form:option>
									</c:forEach>
								</form:select>
							</c:forEach>
							<input id="ajouterDroit" type="button" value="ajouter un droit"
								class="radius button expand secondary" />

						</fieldset>
						<br />
						<div>
							<input id="submitDroit" type="submit" value="valider"
								class="radius button expand success" />
						</div>
					</fieldset>
				</form:form>
			</section>

			<section class="section small-6 columns">

				<style>
#utilisteurs li {
	margin-bottom: 1.5em
}
</style>
				<c:if test="${not empty utilisateurs}">
					<ul id="utilisteurs" class="no-bullet">
						<c:forEach items="${utilisateurs}" var="utilisateur">
							<li>
								<div class="row">
									<span class="small-6 columns">${utilisateur.login}</span><span
										class="small-6 columns">${utilisateur.droits}</span>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${empty utilisateurs}">
					<div>aucun utilisateur n'a de rôle</div>
				</c:if>
			</section>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="js">
		<script>
			$(function() {
				var renumeroterDroits = function() {
					var tabindex = 1000
					$("[name^='droits']").each(function(i, v) {
						v.name = "droits[" + i + "]";
						$(v).attr('tabindex', ++tabindex)
					})
					$("#ajouterDroit").attr('tabindex', ++tabindex)
					$("#submitDroit").attr('tabindex', ++tabindex)
					$(".supprimer-droit").each(function() {
						$(this).attr('tabindex', ++tabindex)
					})
				}, updateInput = function(input) {
					$containerInput = $('<div class="small-9 columns"></div>')
							.append(input)
					var $droit = $('<div class="droit"></div>')
							.addClass('row collapse prefix-round')
							.append(
									$(
											'<div><a href="#" class="supprimer-droit prefix button alert">supprimer</a></div>')
											.addClass("small-3 columns")
											.click(
													function supprimerDroit() {
														$(this)
																.parent(
																		".droit")
																.remove()
														renumeroterDroits()
													}))
					$droit.append($containerInput.clone())
					return $droit
				}, $input = $("#droits0").clone().attr('id', null), getNewInput = function() {
					return updateInput($input.clone())
				}, remplacerInputsDejaPresents = function() {
					$("[name^='droits']").each(function() {
						$(this).replaceWith(updateInput($(this).clone()))
						renumeroterDroits()
					})
				}, inputDefautInit = function() {
					$input.children().attr('selected', null).first().attr(
							'selected', 'selected')
				}, loginInit = function() {
					$("#login").attr('tabindex', 1000).delay(100).focus()
				}, ajouterDroitInit = function() {
					$("#ajouterDroit").click(function() {
						var $input = getNewInput()
						var $droit = $("[name^='droits']", $input)
						$(this).before($input)
						renumeroterDroits()
						$droit.focus()
					})
				}, main = function() {
					remplacerInputsDejaPresents()
					inputDefautInit()
					loginInit()
					ajouterDroitInit()
				}

				main()
			})
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>