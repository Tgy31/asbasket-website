
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.testjee1.Adherent"%>

<%@ include file="header.jsp"%>

<% 
	            List<Adherent> adherents = (List<Adherent>)request.getAttribute("adherents"); 
				pageContext.setAttribute("adherents", adherents);
%>

<div class="panel panel-default">
	<!-- Default panel contents -->
	<div class="panel-heading">
		Liste des adhérents :
		<%= adherents.size() %>
		adhérents
	</div>
	<div style="padding: 10px;">
		<!-- Table -->
		<table class="table" id="adherentTable">
			<thead>
				<tr>
					<th></th>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Année</th>
					<th>Option</th>
					<th>Mail</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
    $('#adherentTable').dataTable( {
        "bProcessing": true,
        "bRetrieve": true,
        "sAjaxSource": '/ajax/getadherents/'
    } );
} );
</script>

<%@ include file="footer.jsp"%>