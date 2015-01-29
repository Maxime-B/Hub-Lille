function test(choix){
	var nom = $(choix).attr("value");
	var check = $(choix).is(":checked");
	if(check){
		$('#champsChoisi').append('<div id="' + nom + '"><input type="hidden" name ="champs" value="' + nom + '"/>'+ nom +'</div>');
	}else{
		$('#' + nom).remove();
	}
	
}