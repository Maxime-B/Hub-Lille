function ajouterPhoto()
{
	var nb = $(".photos").length +1;
	$("#photo").append('<div class="row">Photo '+nb+' <input class="photos" accept="image/*" type="file" name="photos"></div>')
	
}