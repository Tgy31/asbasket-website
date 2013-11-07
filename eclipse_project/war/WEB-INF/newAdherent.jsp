
<%@ page import="java.util.List"%>


<%@ include file="header.jsp"%>

<% 
	boolean actionSucceed = ((String)request.getAttribute("actionSucceed") == "yes");
	if (actionSucceed) 
		{
%>
<div class="alert alert-success">Adhérent correctement ajouté</div>
<%
		};
%>

<div class="panel panel-default">
	<!-- Default panel contents -->
	<div class="panel-heading">
		Ajouter un adhérent
	</div>
	<div class="panel-body">
	
	</div>


	<form class="form-horizontal" action="/admin/newadherent/" method="post">
		<div class="form-group">
			<label for="inputFirstName" class="col-lg-2 control-label">Prénom</label>
			<div class="col-lg-4">
				<input type="text" class="form-control" id="inputFirstName"
					placeholder="Prénom" name="first_name">
			</div>
		</div>
		<div class="form-group">
			<label for="inputLastName" class="col-lg-2 control-label">Nom</label>
			<div class="col-lg-4">
				<input type="text" class="form-control" id="inputLastName"
					placeholder="Nom" name="last_name">
			</div>
		</div>

		<div class="form-group">
			<label for="selectYear" class="col-lg-2 control-label">Année</label>
			<div class="col-lg-4">
				<select class="form-control" name="year" id="selectYear">
					<option value="1">1ére année</option>
					<option value="2">2ème année</option>
					<option value="3">3ème année</option>
					<option value="4">4ème année</option>
					<option value="5">5ème année</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="selectOption" class="col-lg-2 control-label">Option</label>
			<div class="col-lg-4">
				<select class="form-control" name="option" id="selectOption">
					<option>IF</option>
					<option>SGM</option>
					<option>GEN</option>
					<option>GMC</option>
					<option>TC</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="inputMail" class="col-lg-2 control-label">Mail</label>
			<div class="col-lg-4">
				<input type="email" class="form-control" id="inputMail"
					placeholder="Mail" name="mail">
			</div>
		</div>

		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-4">
				<button type="submit" class="btn btn-primary">Valider</button>
			</div>
		</div>
	</form>

</div>

<%@ include file="footer.jsp"%>