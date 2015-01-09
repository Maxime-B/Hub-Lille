<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title">Le job a bien ete publié avec les infomrations suivante</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<br />
				
				<br />
				<table>

					<tr>
						<th>Titre</th>
						<th>Description</th>
						<th>Remuneration</th>

					</tr>

					<tr>
						<td>${titre}</td>
						<td>${description}</td>
						<td>${remuneration}</td>

					</tr>

					<!--  </table>
    <tr>
        <td>Titre</td>
        <td>${titre}</td>
    </tr>
    <tr>
        <td>Description</td>
        <td>${description}</td>
    </tr>
    <tr>
        <td>Remuneration</td>
        <td>${remuneration}</td>
    </tr>-->
				</table>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
