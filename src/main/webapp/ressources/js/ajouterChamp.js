function ajouterChamp() {
	if (document.getElementById('formChamp') == null) {
		$('#tableau')
				.append(
						'<tr id="formChamp"><td><input id="nomChamp" type="text"/></td><td><input id="nomChamp2" type="text"/></td><td><select id="select"></select></td><td><input id="obligatoire" type="checkbox"/></td><td><input type="button" value="Ok" onClick="validChamp()"/></td></tr>');
		JSMetierCategorie.listeTypeChamp(function(data) {
			dwr.util.addOptions("select", data);
		});
	}

}

function validChamp() {
	if (dwr.util.getValue('nomChamp') != '' && dwr.util.getValue('nomChamp2') != '') {
		JSMetierChamp
				.creerChamp(
						dwr.util.getValue('nomChamp') + "-" +dwr.util.getValue('nomChamp2'),
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
														+ '</td><td>' +
														dwr.util.getValue('nomChamp2') +
														'</td><td>'
														+ dwr.util
																.getValue('select')
														+ '</td><td>'
														+ dwr.util
																.getValue('obligatoire')
														+ '</td><td><input type="checkbox" value="'
														+ dwr.util
																.getValue('nomChamp') + "-" + dwr.util.getValue('nomChamp2')
														+ '" onClick="test(this)" /></td></tr>');
								$('#formChamp').remove();
							} else {
								alert('Ce champ existe déjà');
							}
						});
	}

}