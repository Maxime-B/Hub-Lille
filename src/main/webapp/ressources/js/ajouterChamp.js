function ajouterChamp() {
	if (document.getElementById('formChamp') == null) {
		$('#tableau')
				.append(
						'<tr id="formChamp"><td><input id="nomChamp" type="text"/></td><td><select id="select"></select></td><td><input id="obligatoire" type="checkbox"/></td><td><input type="button" value="Valider" onClick="validChamp()"/></td></tr>');
		JSMetierCategorie.listeTypeChamp(function(data) {
			dwr.util.addOptions("select", data);
		});
	}

}

function validChamp() {
	if (dwr.util.getValue('nomChamp') != '') {
		JSMetierChamp
				.creerChamp(
						dwr.util.getValue('nomChamp'),
						dwr.util.getValue('select'),
						dwr.util.getValue('obligatoire'),
						function(data) {
							console.log(data);
							if (data != null) {
								$('#tableau')
										.append(
												'<tr><td>'
														+ dwr.util
																.getValue('nomChamp')
														+ '</td><td>'
														+ dwr.util
																.getValue('select')
														+ '</td><td>'
														+ dwr.util
																.getValue('obligatoire')
														+ '</td><td><input type="checkbox" value="'
														+ dwr.util
																.getValue('nomChamp')
														+ '" onClick="test(this)" /></td></tr>');
								$('#formChamp').remove();
							} else {
								alert('Ce champ existe déjà');
							}
						});
	}

}