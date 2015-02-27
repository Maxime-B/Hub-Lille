<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		<spring:message code="admin.droits.titre"/>
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
	<session id="pas-de-js">
		<h1><spring:message code="admin.droits.titre"/></h1>

		<c:set var="hide" value=" hide" />
		<c:if test="${AuMoinsUnAdminException}">
			<c:set var="hide" value="" />
		</c:if>
		<c:set var="login"><span class="login"></span></c:set>
		<c:set var="spanDroits"><span class="droits"></span></c:set>
		<c:set var="anciensDroits"><span class="anciens-droits"></span></c:set>
		<c:set var="expediteur"><span class="expediteur"></span></c:set>
		<c:set var="logins"><span class="logins"></span></c:set>
		
		
		<div id="container-messages">
			<div id="messages">
				<div id="messages-perso" class="model hide">
					<div id="AuMoinsUnAdminException"
						class="alert-box alert radius${hide}"><spring:message code="admin.droits.AuMoinsUnAdminException"/></div>
					<div id="perso-ajouter" class="hide alert-box success radius"><spring:message code="admin.droits.perso-ajouter" arguments="${login},${spanDroits}" htmlEscape="false"/></div>
					<div id="perso-supprimer" class="hide alert-box success radius"><spring:message code="admin.droits.perso-supprimer" arguments="${login},${spanDroits}" htmlEscape="false"/></div>
					<div id="perso-modifier" class="hide alert-box success radius"><spring:message code="admin.droits.perso-modifier" arguments="${login},${spanDroits},${anciensDroits}" htmlEscape="false"/></div>
					<c:if test="${estUnSucces}">
						<div class="alert-box success radius">${utilisateur.login} a
							maintenant les droits : [${fn:join(utilisateur.droits, ', ')}]</div>
					</c:if>
				</div>
				
				<div id="messages-autrui" class="model hide">
					<div id="autrui-logins" class="hide alert-box radius"><spring:message code="admin.droits.autrui-logins" arguments="${logins}" htmlEscape="false"/></div>
					<div id="autrui-login" class="hide alert-box radius"><spring:message code="admin.droits.autrui-login" arguments="${expediteur}" htmlEscape="false"/></div>
					<div id="autrui-logout" class="hide alert-box radius"><spring:message code="admin.droits.autrui-logout" arguments="${expediteur}" htmlEscape="false"/></div>
					<div id="autrui-ajouter" class="hide alert-box radius"><spring:message code="admin.droits.autrui-ajouter" arguments="${expediteur}, ${login}, ${spanDroits}" htmlEscape="false"/></div>
					<div id="autrui-supprimer" class="hide alert-box radius"><spring:message code="admin.droits.autrui-supprimer" arguments="${expediteur}, ${login}, ${spanDroits}" htmlEscape="false"/></div>
					<div id="autrui-modifier" class="hide alert-box radius"><spring:message code="admin.droits.autrui-modifier" arguments="${expediteur}, ${login}, ${spanDroits}, ${AnciensDroits}" htmlEscape="false"/></div>
				</div>
			</div>
			<div id="messages-bouttons">
				<a id="messages-reduire"></a>
			</div>
		</div>
		<div class="row">
			<section class="section small-5 columns">
				<form:form method="post" modelAttribute="utilisateur">
					<fieldset id="fieldset-changer-role">
						<legend><spring:message code="admin.droits.utilisateur"/></legend>
						<div id="container-changer-role">
							<div id="login-changer-role" class="row">
								<div class="small-3 columns">
									<form:label cssClass="right inline" path="login">login :</form:label>
								</div>

								<div class="small-9 columns">
									<form:input path="login" required="required"
										placeholder="login" />
								</div>
							</div>

							<div id="fieldset-gerer-droits" class='row'>
								<div id="container-gerer-droits">
									<div class="small-12 columns" id="gerer-droits">
										<c:set var="changement" value="false" />
										<c:forEach items="${droits}" var="droit" varStatus="status">
											<c:set var="title" value="" />
											<c:if test="${fn:length(definitionsDesDroits[droit]) > 1}">
												<c:if test="${!changement}">
													<br />
												</c:if>
												<c:set var="title"
													value="équivaut à ${definitionsDesDroits[droit]}" />
												<c:set var="changement" value="true" />
											</c:if>
											<div class="droit row" title="${title}">
												<div class="small-3 columns">
													<div class="droitsPossibles switch radius">
														<form:checkbox path="droits" value="${droit}"
															label="${droit}" />
													</div>
												</div>
												<div class="label-droit small-9 columns"></div>
											</div>
										</c:forEach>
									</div>
									
									<c:set var="submitChangerRole"><spring:message code="admin.droits.submit"/></c:set>
									<div class="small-12 columns">
										<input id="bouton-modifier" type="submit"
											value="${submitChangerRole}" class="radius button small expand" />
									</div>
								</div>
							</div>
						</div>
					</fieldset>
				</form:form>
			</section>

			<section class="section small-7 columns">
				<form class="no-enter-submit">
					<div class="row">
						<div id="utilisateurs" class="small-12 columns">
							<fieldset id="fieldset-chercher-utilisateur">
								<legend><spring:message code="admin.droits.rechercher"/></legend>
								<div id="container-chercher-utilisateur">
									<spring:bind path="utilisateur.filtre">
										<c:set var="filtre"><spring:message code="admin.droits.filtre"/></c:set>
										<input id="filtre" placeholder="${filtre}"
											name="${status.expression}" value="${status.value}"
											form="utilisateur" type="text" class="search" />
									</spring:bind>

									<div></div>

									<div class="hide">
										<ul class="no-bullet">
											<li id="model-recherche">
												<div class="row">
													<span class="buttons small-3 columns"> <a
														class="bouton-editer button success" href="#"><spring:message code="admin.droits.editer"/></a> <a
														class="bouton-supprimer button alert" href="#"><spring:message code="admin.droits.supprimer"/></a>
													</span> <span class="login small-5 columns"></span><span
														class="droits small-4 columns"></span>
												</div>
											</li>
										</ul>
									</div>

									<div id="liste-recherche" class="hide">
										<ul class="list no-bullet">
											<c:forEach items="${utilisateurs}" var="utilisateur">
												<li>
													<div class="row">
														<span class="buttons small-3 columns"> <a
															class="bouton-editer button success" href="#"><spring:message code="admin.droits.editer"/></a>
															<a class="bouton-supprimer button alert" href="#"><spring:message code="admin.droits.supprimer"/></a>
														</span> <span class="login small-5 columns">${utilisateur.login}</span><span
															class="droits small-4 columns">${utilisateur.droits}</span>
													</div>
												</li>
											</c:forEach>
										</ul>
										<div id="liste-vide" data-alert
											class="alert-box warning radius"><spring:message code="admin.droits.aucunUtilisateur"/></div>
									</div>
								</div>
								<ul class="pagination no-bullet">
										</ul>
							</fieldset>
						</div>
					</div>
				</form>
			</section>
		</div>
		</session>
	</tiles:putAttribute>

	<tiles:putAttribute name="js">
		<script
			src="${pageContext.request.contextPath}/ressources/js/list.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/ressources/js/list.pagination.min.js"></script>
		<script type='text/javascript'
			src='${pageContext.request.contextPath}/dwr/engine.js'></script>
		<script type='text/javascript'
			src='${pageContext.request.contextPath}/dwr/interface/JSMetierUtilisateur.js'></script>
		<script type='text/javascript'
			src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script>
var userList = null

$(function() {
	var boutonsJQuerySelector = ".bouton-editer, .bouton-supprimer"
    updateInput = function(input) {
        $(input).find(".label-droit").append(
            $(input).find("label").clone().addClass("inline"))
    }, remplacerInputsDejaPresents = function() {
        $(".droit").each(function() {
            updateInput(this)
        })
    }, loginInit = function() {
        $("#login").delay(100).focus()
    }, editer = function(login, StringDroits) {
        $("#login").val(login)
        var droits = StringDroits.slice(1, StringDroits.length - 1)
            .split(", ")
        $("[name~='droits']").prop("checked", false)
        $.each(droits, function(i, droit) {
            $("[value='" + droit + "']").prop("checked", true)
        })
       // metier coté serveur
    }, supprimer = function(login, callback) {
        JSMetierUtilisateur.supprimerDroits(login, callback);
    }, modifier = function(login, droits, callback) {
        JSMetierUtilisateur.modifierRole(
            dwr.util.getValue("login"), $(
                '#gerer-droits [name="droits"]:checked')
            .map(function(i, el) {
                return $(el).val();
            }).get(), callback);
    }
    filtrerInit = function(values) {
    		var paginationOptions = {
					outerWindow : 1,
                   innerWindow : 6
    		}
            var options = {
                valueNames: ['login', 'droits'],
                item: "model-recherche",
                plugins: [ListPagination(paginationOptions)],
            }
    		,	userList = new List('utilisateurs', options, values);

            if ($("#liste-recherche li:first").length > 0) {
                userList.search($("#filtre").val())
            }
            $("#gerer-droits").on("add", function(event, li) {
				userList.search($("#filtre").val())
            })
            $(window).trigger("resize")
            $('.no-enter-submit').keypress(function(event) {
                if (event.keyCode == '13') {
                    event.preventDefault();
                }
            })
            $containerInput = $(
                    '<div class="small-10 columns"></div>')
                .append($("#filtre").clone())
            var $newInput = $(
                    '<div id="container-filtre" class="filtre"></div>')
                .addClass('row collapse prefix-round')
                .append(
                    $(
                        '<div><a href="#" class="supprimer-filtre prefix button alert">reset</a></div>')
                    .addClass("small-2 columns")
                    .find("a")
                    .click(
                        function supprimerFiltre(
                            e) {
                            $("#filtre")
                                .val("")
                            userList
                                .search()
                            $(window)
                                .trigger(
                                    "resize")
                            return false
                        }).end())

            $newInput.append($containerInput.clone())
            $("#filtre").replaceWith($newInput)
            var timer = null
            $("#filtre").keyup(function() {
            	var search = function() {
                    userList.search($("#filtre").val())
                }
                window.clearTimeout(timer)
                if ($("#filtre").val().length > 2) {
                    timer = window.setTimeout(search, 200)
                } else {
                    timer = window.setTimeout(search, 1000)
                }
            })
            return userList;
        },
        updateButton = function(selecteur) {
            selecteur = $(selecteur).not(".ui-widget")
            var buttons = {
                "bouton-editer": "ui-icon-pencil",
                "bouton-supprimer": "ui-icon-closethick",
            }
            selecteur.each(function(i, el) {
                var buttonIcon = null
                for (var buttonSelecteur in buttons) {
                    if ($(el).hasClass(buttonSelecteur)) {
                        buttonIcon = buttons[buttonSelecteur]
                        break;
                    }
                }
                $(el).button({
                    icons: {
                        primary: buttonIcon
                    },
                    text: false
                }).click(function(event) {
                    return null
                })
            })
        },
        boutonsInit = function() {
            var $messages = $("#messages-reduire")
            ,	optionRestaurer = {
	     		      icons : {
	     		         primary : "ui-icon ui-icon-carat-1-s"
	     		       }, label: "réduire", text : false 
   		     	}
            ,   optionReduire = {
    		      icons: {
     		         primary: "ui-icon ui-icon-carat-1-w"
     		       },
     		      label: "restaurer", text : false 
    		     }
            , restaurer = function (){
            	$("#messages").css({
		        	height: "250px",
		        	"overflow-y" : "scroll"
     		   })
     		   $messages.button("option", optionRestaurer)
    			  $messages.one("click", reduire)
            }, reduire = function(){
            	$("#messages").scrollTop(0)
    				$("#messages").css({
	        		height: "60px",
        			"overflow-y" : "scroll"
     		   })
     		   $messages.button("option", optionReduire)
     		  	$messages.one("click",restaurer)
    		 	 }
            $messages.button(optionReduire)
            reduire()
            $messages.one("click", restaurer)
            $("#gerer-droits").on("add", function(event, li) {
                updateButton($(boutonsJQuerySelector, li))
            })
            /* $("#fieldset-chercher-utilisateur .pagination").on("click", function() {
                updateButton($(boutonsJQuerySelector,
                    "#liste-recherche"))
            }) */
           /*  $("#filtre").keyup(function() {
                updateButton($(boutonsJQuerySelector,
                    "#liste-recherche"))
            }) */
            $("#bouton-modifier")
                .click(
                    function() {
                        var login = $("#login").val()
                        ,   droits = $(
                                '#gerer-droits [name="droits"]:checked')
                            .map(function(i, el) {
                                return $(el).val();
                            }).get(),
                            droitsString = "[" + droits.join(", ") + "]"
                           ,	users = userList.get("login", login)
                        modifier(
                            login,
                            droits,
                            function() {
                                var item = {
                                    login: login,
                                    droits: droitsString
                                }
                                if (droits.length == 0) {
                                    send(JSON.stringify({
                                        type: "remove",
                                        item : item
                                    }))
                                    supprimerIhm(login)
                                    
                                    afficherMessage($("#perso-supprimer")
							            	.find(".login").text(login).end()
							            	.find(".droits").text(droitsString).end())
                                } else if (users.length == 0) {
                                    send(JSON.stringify({
                                        type: "add",
                                        item: item
                                    }))
                                    ajouterIhm(login, item)
                                    afficherMessage($("#perso-ajouter")
							            	.find(".login").text(login).end()
							            	.find(".droits").text(droitsString).end())
                                } else {
                                	var	anciensDroits = users[0].values().droits
                                    send(JSON.stringify({
                                        type: "modify",
                                        item: item,
                                        anciensDroits : anciensDroits
                                    }))
                                    afficherMessage($("#perso-modifier")
							            	.find(".login").text(login).end()
							            	.find(".droits").text(droitsString).end()
							            	.find(".anciens-droits").text(anciensDroits).end())
                                    modifierIhm(users[0], item)
                                }
                                $(window).trigger(
                                    "resize")
                            })
                           $("#login").delay(200).focus()
                        return false
                    })
            $("#liste-recherche")
                .on(
                    "click",
                    ".bouton-editer",
                    null,
                    function() {
                        var login = $(this).closest(
                                "li").find(".login")
                            .text(),
                            droits = $(
                                this).closest("li")
                            .find(".droits").text()
                        editer(login, droits)
                        return false
                    })
                .on(
                    "click",
                    ".bouton-supprimer",
                    null,
                    function() {
                        var $li = $(this).closest("li"),
                            login = $li.find(".login").text()
                           ,	droitsString = $li.find(".droits").text()
                           ,   item = {login:login,droits:droitsString}
                        ,	callback = function() {
                               supprimerIhm(login)
                               send(JSON.stringify({
                                   type : "remove",
                                   item : item
                               }))
                               
                               afficherMessage($("#perso-supprimer")
					            	.find(".login").text(login).end()
					            	.find(".droits").text(droitsString).end())
                               $("#login").val(
                                   login)
                               $(
                                       "[name~='droits']")
                                   .prop(
                                       "checked",
                                       false)
                           }
                        dwr.engine
                            .setErrorHandler(function(
                                msg, exc) {
                                if (exc.javaClassName = "ipint.glp.metiers.exceptions.AuMoinsUnAdminException") {
                                    afficherMessage("#AuMoinsUnAdminException")
                                }
                            });
                        supprimer(
                            login,
                            callback
                            )
                        return false
                    })
        },
        libererHauteur = function(selecteur) {
            $(selecteur).height("inherit")
        },
        equalizer = function(selecteur) {
            $(selecteur).height("inherit")
            max = 0;
            $(selecteur).each(function() {
                max = Math.max(max, $(this).height());
            })
            $(selecteur).height(max)
        },
        limiterHauteur = function(selecteur) {
        	$("#container, body").css({
                margin: 0,
                padding: 0
            }) //normalizer
            var sauvegarde = $(selecteur).css('max-height')
            $(selecteur).css('max-height', "inhderit")
            var heightNonVisible = ($('#container').height() - innerHeight)
            if (heightNonVisible <= 0) {
                return
            }
            $(selecteur).css({
                "max-height": $(selecteur).height() - heightNonVisible - 2,
                "overflow-y": "auto"
            }).children().css({
                "margin-right": "8px" //scroll-bar, margin de row
            })
            heightNonVisible = ($('#container').height() - innerHeight)
            if (heightNonVisible > 0) { //rollback si scroll bar toujours visible
            	$(selecteur).css('max-height', "20em")
            	//$(selecteur).css('max-height', sauvegarde)
            }
        },
        resizeList = function() {
            limiterHauteur("#liste-recherche")
            equalizer("#fieldset-changer-role, #fieldset-chercher-utilisateur")
            
        }
    var send = function(message, callback) {
    	if (typeof WebSocket !== 'undefined') {
	        waitForConnection(function() {
	            socket.send(message);
	            if (typeof callback !== 'undefined') {
	                callback();
	            }
	        }, 1000);
    	}
    }
    ,	waitForConnection = function(callback, interval) {
        if (socket.readyState === 1) {
            callback();
        } else {
            setTimeout(function() {
                waitForConnection(callback);
            }, interval);
        }
    }
    ,	afficherMessage = function(selecteur) {
        $(selecteur)
        	.clone()
            .removeClass("hide")
            .fadeIn()
            .prependTo($("#messages"))
    }
    ,	 login = function(message) {
            var messageDiv = $("#autrui-login").find(".expediteur").text(message.expediteur).end()
            afficherMessage(messageDiv)
        },
        logins = function(message) {
            var messageDiv = $("#autrui-logins").find(".logins").text(message.logins).end()
            afficherMessage(messageDiv)
        },
        logout = function(message) {
            var messageDiv = $("#autrui-logout").find(".expediteur").text(message.expediteur).end()
            afficherMessage(messageDiv)
        },
        add = function(message) {
            ajouterIhm(message.item.login, message.item)
            var messageDiv = $("#autrui-ajouter")
            	.find(".expediteur").text(message.expediteur).end()
            	.find(".login").text(message.item.login).end()
            	.find(".droits").text(message.item.droits).end()
            afficherMessage(messageDiv)
        },
        remove = function(message) {
            supprimerIhm(message.item.login)
            var messageDiv = $("#autrui-supprimer")
	            .find(".expediteur").text(message.expediteur).end()
            	.find(".login").text(message.item.login).end()
            	.find(".droits").text(message.item.droits).end()		            
         	afficherMessage(messageDiv)
        },
        modify = function(message) {
        	console.log(message)
            modifierIhm(message.item.login, message.item)
            var messageDiv = $("#autrui-modifier")
            	.find(".expediteur").text(message.expediteur).end()
            	.find(".login").text(message.item.login).end()
            	.find(".droits").text(message.item.droits).end()
            	.find(".anciens-droits").text(message.anciensDroits).end()
            afficherMessage(messageDiv)
        }
        // ihm
        ,
        supprimerIhm = function(login) {
            userList.remove("login", login)
        },
        modifierIhm = function(user, item) {
            if (typeof user === "string") {
                user = userList.get("login", item.login)[0]
            }
            user.values(item);
        },
        ajouterIhm = function(login, item) {
            userList.add(item)
            var user = userList.get("login",login)
            $("#gerer-droits").trigger("add",user.elm)
            userList.sort('login', { order: "asc" });
            $(window).trigger("resize")
        }
        // fin ihm
       ,	websocketInit = function(){
        	if (typeof WebSocket !== 'undefined') {
		        var socket = new WebSocket("ws://" + location.host + "/hublille1/droit");
		        $(window).on('beforeunload unload', function () {
			        socket.close()
	       	    });
			    
			    socket.onopen = function(e) {
			        console.log(e)
			    }
			    socket.onclose = function(e) {
			        console.log(e)
			    }
			    socket.onerror = function(e) {
			        console.log(e.stack)
			    }
			    socket.onmessage = function(e) {
			        var message = $.parseJSON(e.data)
			        switch (message.type) {
				        case "logins" :
			                logins(message);
			                break;
			            case "login" :
			                login(message);
			                break;
			            case "logout" :
			                logout(message);
			                break;
			            case "add" :
			                add(message);
			                break;
			            case "remove" :
			                remove(message);
			                break;
			            case "modify" :
			                modify(message);
			                break;
			            default:
			                console.log("type d'un message invalide : " + message.type)
			        }
			    }
		    }
        }
    ,	 main = function() {
    		websocketInit()
            //mise en forme + comportement
            updateButton($(boutonsJQuerySelector,"#liste-recherche"))
            userList = filtrerInit()
            remplacerInputsDejaPresents()
            loginInit()
            boutonsInit()
            $("#liste-recherche").removeClass("hide")
                //dimensions
            resizeList()
        }
    main()
})

$("#pas-de-js").attr("id", null)
</script>
		<!-- si js désactivé -->
		<style>
			#pas-de-js .switch label {color:black}
			#pas-de-js #liste-recherche {display:block !important;visibility:visible !important}
		</style>
		<!-- fin si js désactivé -->
	</tiles:putAttribute>

	<tiles:putAttribute name="css">
		<style>	
#container-messages .login {
	word-break: break-all;
}
#container-messages {
	position : relative
}
#messages-reduire {
	position : absolute;
	top : 13px;
	right : -45px;
}
#liste-recherche li {
	margin-bottom: 0.4em !important
}

#liste-vide {
	display: none
}

ul:empty+#liste-vide {
	display: block
}
</style>
	</tiles:putAttribute>
</tiles:insertDefinition>
