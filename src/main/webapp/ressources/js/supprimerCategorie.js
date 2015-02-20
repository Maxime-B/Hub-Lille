function supprimerCategorie(valeur) {
	var resultExist;
	resultExist = JSMetierCategorie.existeAnnonce(valeur,function(data){
		resultExist = data;
		if (resultExist) {
			alert('Impossible de supprimer la catégorie car il existe des annonces dans celle ci !')
		} else {
			var res = confirm('Etes vous sûr de vouloir supprimer la catégorie '
					+ valeur + ' ?');
			if (res == true) {
				JSMetierCategorie.supprimerCategorie(valeur);
				$('#' + valeur).remove();
			}
		}
	});

	
	

}