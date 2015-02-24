<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		liste de mes annonces
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<section class="section">
			<h1>moderer une annonce</h1>
			<form action="">
			<fieldset>
				<label>Motif</label>
				<textarea name="motif"></textarea>
				<input type="submit" value="modérer">
			</fieldset>
			</form>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>