<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		Gérer les roles des utilisateurs
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<h1>Gérer les roles</h1>
		
		<div id="aide" title="Aide">
			<!-- class="alert-box info radius" -->
			<p>SUPER_ADMIN a tous les pouvoirs.</p>
			<p>SUPER_MODERATEUR a tous les pouvoirs sauf celui de changer les
				roles des utilisateurs</p>
		</div>

		<c:if test="${estUnSucces}">
			<p class="alert-box success radius">${utilisateur.login} a
				maintenant les droits : ${utilisateur.droits}</p>
		</c:if>
		<c:if test="${!empty estUnEchec}">
			<p class="alert-box alert radius">${estUnEchec}</p>
		</c:if>
		<c:if test="${!estUnSucces && empty estUnEchec}">
			<p> </p>
		</c:if>

		<div class="row">
			<section class="section small-5 columns">
				<div class="hide">
					<select id="droitsPossibles" name="droits">
						<c:forEach items="${droits}" var="droitPossible">
							<option value="${droitPossible}">${droitPossible}</option>
						</c:forEach>
					</select>
				</div>

				<form:form method="post" modelAttribute="utilisateur">
					<fieldset id="fieldset-changer-role">
						<legend>Modifier le rôle d'un utilisateur</legend>
						<div id="container-changer-role">
						
						<div id="login-changer-role" class="row">
							<div class="small-2 columns"><a href="#" class="button info" id="bouton-aide">Aide</a></div>
						
							<div class="small-2 columns"><form:label cssClass="right inline" path="login">login:</form:label></div>
							
							<div class="small-8 columns"><form:input path="login" required="required" placeholder="login" /></div>
						</div>

						<fieldset id="fieldset-gerer-droits" class='row'>
							<legend>Gérer ses droits</legend>
							<div id="container-gerer-droits">
							<div id="gerer-droits">
								<c:forEach items="${utilisateur.droits}" var="droitValeur"
									varStatus="status">
									<form:select path="droits[${status.index}]">
										<c:forEach items="${droits}" var="droitPossible">
											<form:option value="${droitPossible}">${droitPossible}</form:option>
										</c:forEach>
									</form:select>
								</c:forEach>
							</div>
							
							<div class="row">
								<div id="boutons-gerer-droits" class="small-4 columns">
									<a id="ajouter-droit"
									class="radius button success small expand" title="Ajouter un droit">Ajouter</a>
								</div>
								<div class="small-8 columns">
									<input id="submitDroit" type="submit" value="Valider"
								class="radius button small expand" />
								</div>
							</div>
							</div>
						</fieldset>
						</div>
					</fieldset>
				</form:form>
			</section>

			<section class="section small-7 columns">
					<form>
						<div class="row">
							<div id="utilisateurs" class="small-12 columns">
								<fieldset id="fieldset-chercher-utilisateur">
									<legend>Rechercher</legend>
									<div id="container-chercher-utilisateur">
									<spring:bind path="utilisateur.filtre">
										<input id="filtre" placeholder="filtre"
											name="${status.expression}" value="${status.value}"
											form="utilisateur" type="text" class="search" />
									</spring:bind>
									
									<c:if test="${empty utilisateurs}">
										<div>aucun utilisateur n'a de rôle</div>
									</c:if>
									
									<div id="liste-recherche">
										<ul class="list no-bullet">
											<c:forEach items="${utilisateurs}" var="utilisateur">
												<li>
													<div class="row">
														<span class="buttons small-3 columns"> <a
															 class="bouton-editer button success" href="#">éditer</a>
															<a class="bouton-supprimer button alert" href="#">supprimer</a>
														</span> <span class="login small-4 columns">${utilisateur.login}</span><span
															class="droits small-5 columns">${utilisateur.droits}</span>
													</div>
												</li>
											</c:forEach>
										</ul>
									</div>
									</div>
								</fieldset>
							</div>
						</div>
					</form>
			</section>
		</div>
	</tiles:putAttribute>

	<tiles:putAttribute name="js">
		<script
			src="${pageContext.request.contextPath}/ressources/js/list.min.js"></script>

		<script>
		$(function() {
		    var $input = $("#droitsPossibles").remove().attr('id', null),
		        renumeroterDroits = function() {
		            var tabindex = 1000
		            $("[name^='droits']").each(function(i, v) {
		                $(v).attr("name", "droits[" + i + "]");
		                $(v).attr('tabindex', ++tabindex)
		            })
		            $("#ajouter-droit").attr('tabindex', ++tabindex)
		            $("#submitDroit").attr('tabindex', ++tabindex)
		            $("#bouton-supprimer-droit").each(function() {
		                $(this).attr('tabindex', ++tabindex)
		            })
		        },
		        updateInput = function(input) {
		            $containerInput = $('<div class="small-8 columns"></div>')
		                .append(input)
		            var $droit = $('<div class="droit"></div>')
		                .addClass('row collapse prefix-round')
		                .append(
		                    $(
		                        '<div><a href="#" class="supprimer-droit prefix button alert">supprimer</a></div>')
		                    .addClass("small-4 columns")
		                    .click(
		                        function supprimerDroit() {
		                            $(this)
		                                .parent(
		                                    ".droit")
		                                .remove()
		                            renumeroterDroits()
		                            equalizerFieldset()
		                            return false
		                        }))
		            $droit.append($containerInput.clone())
		            return $droit
		        },
		        getNewInput = function() {
		            return updateInput($input.clone())
		        },
		        remplacerInputsDejaPresents = function() {
		            $("[name^='droits']").each(function() {
		                $(this).replaceWith(updateInput($(this).clone()))
		                renumeroterDroits()
		            })
		        },
		        inputDefautInit = function() {
		            $input.children().attr('selected', null).first().attr(
		                'selected', 'selected')
		        },
		        loginInit = function() {
		            $("#login").attr('tabindex', 1000).delay(100).focus()
		        },
		        ajouterDroit = function() {
		            var $ajouterDroit = $("#ajouter-droit"),
		                $input = getNewInput(),
		                $droit = $("[name^='droits']:first", $input)
		            $("#gerer-droits").append($input)
		            renumeroterDroits()
		            $droit.focus()
		            equalizerFieldset()
		            return $droit
		        },
		        editer = function(login, Stringdroits) {
		            $(".droit").remove()
		            $("#login").val(login)
		            var droits = Stringdroits.slice(1, Stringdroits.length - 1)
		                .split(", ")
		            $.each(droits, function(i, droit) {
		                var $select = ajouterDroit(),
		                    $option = $select
		                    .children('[value="' + droit + '"]').attr(
		                        "selected", "selected")
		            })
		        },
		        supprimer = function(login) {
		            $("#login").val(login)
		            $(".droit").remove()
		            $("#login").closest("form").submit()
		        },success
		        filtrer = function() {
		            var options = {
		                valueNames: ['login', 'droits']
		            };
		            var userList = new List('utilisateurs', options);
		            userList.search($("#filtre").val())
		            equalizerFieldset()

		            $containerInput = $('<div class="small-10 columns"></div>')
		                .append($("#filtre").clone())
		            var $newInput = $('<div id="container-filtre" class="filtre"></div>')
		                .addClass('row collapse prefix-round')
		                .append(
		                    $(
		                        '<div><a href="#" class="supprimer-filtre prefix button alert">reset</a></div>')
		                    .addClass("small-2 columns").click(
		                        function supprimerFiltre() {
		                            $("#filtre").val("")
		                            userList.search()
		                            equalizerFieldset()
		                            return false
		                        }))
		            $newInput.append($containerInput.clone())
		            $("#filtre").replaceWith($newInput)
		        },
		        boutonsInit = function() {
		            $("#ajouter-droit")
		            	.click(ajouterDroit)
		            	.keypress(ajouterDroit)
		            $("#bouton-aide").click(function() {
		                $("#aide").dialog("open")
		            })
		            $(".bouton-editer").click(function() {
		                var login = $(this).closest("li").find(".login").text(),
		                    droits = $(this).closest("li").find(".droits").text()
		                editer(login, droits)
		                return false
		            })
		            $(".bouton-supprimer").click(function() {
		                var login = $(this).closest("li").find(".login").text()
		                supprimer(login)
		                return false
		            })
		            var buttons = {
		                //"#ajouter-droit": "ui-icon-plus",
		                "#bouton-aide": "ui-icon-info",
		                ".bouton-editer": "ui-icon-pencil",
		                ".bouton-supprimer": "ui-icon-closethick",
		            }
		            for (var buttonSelecteur in buttons) {
		                var buttonIcon = buttons[buttonSelecteur]
		                $(buttonSelecteur).button({
		                    icons: {
		                        primary: buttonIcon
		                    },
		                    text: false
		                }).click(function(event) {
		                    event.preventDefault();
		                })
		            }
		            //$(".buttons", "#utilisateurs").addClass("button-group radius")

		        },
		        equalizer = function(selecteur) {
		            $(selecteur).height("inherit")
		            max = 0;
		            $(selecteur).each(function() {
		                max = Math.max(max, $(this).height());
		            })
		            $(selecteur).height(max)
		        },
		        limiterHauteur = function(selecteur, SurfaceNonUtilisee) {
		        	var SurfaceNonUtilisee = SurfaceNonUtilisee ? SurfaceNonUtilisee : 0
		        	$(selecteur).css({
	            		//taille pour tenir dans une fenêtre = taille initial + surface disponible dans la fenêtre + surface non utilisée
	                    "max-height": $(selecteur).height() + (innerHeight - $('#container').height()) + (SurfaceNonUtilisee + ($("#container").height() - $("#main").height() - $("#header").height() - $("#footer").height())),
	                    "overflow-y": "auto"
	                })
	                .children().css({
	                    "margin-right": "10px"
	                }) //scroll-bar
		        },
		        equalizerFieldset = function() {
		            equalizer("#fieldset-changer-role, #fieldset-chercher-utilisateur")
		        },
		        main = function() {
		            remplacerInputsDejaPresents()
		            inputDefautInit()
		            loginInit()

		            boutonsInit()
		            equalizerFieldset()
		            limiterHauteur("#liste-recherche", $("#fieldset-chercher-utilisateur").innerHeight() - $("#container-chercher-utilisateur").height())
		            equalizerFieldset()
		            limiterHauteur("#gerer-droits", $("#fieldset-changer-role").innerHeight() - $("#container-changer-role").height())
		            if ($("#fieldset-chercher-utilisateur li:first").length > 0) {
		            	filtrer()
		            }
	                $("#aide").dialog({
	                    autoOpen: false,
	                    width: "40em"
	                });
		        }

		    main()
		})
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="css">
		<style>
#utilisteurs li {
	margin-bottom: 1.5em
}

.top-bar, .row {
max-width: 80rem !important;
}
</style>
	</tiles:putAttribute>
</tiles:insertDefinition>