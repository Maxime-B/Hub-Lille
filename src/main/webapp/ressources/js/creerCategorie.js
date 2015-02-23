function test(choix){
	var nom = $(choix).attr("value");
	var check = $(choix).is(":checked");
	if(check){
	$('#sortable').append('<li style="padding: 10px 50px;width: 40%;margin: 5px;" id="' + nom + '" class="ui-state-default ui-sortable-handle"><span style="margin-left: 35%;" class="ui-icon ui-icon-arrowthick-2-n-s"></span><input type="hidden" name ="champs" value="' + nom + '"/>' + nom + '</li>');
		//$('#champsChoisi').append('<div id="' + nom + '"><input type="hidden" name ="champs" value="' + nom + '"/>'+ nom +'</div>');
	}else{
		$('#' + nom).remove();
	}
	
}