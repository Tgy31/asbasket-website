<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.testjee1.Saison"%>
<%@ page import="com.testjee1.Equipe"%>

<%@ include file="header.jsp"%>

<div class="page-header">
	<h1>
		Equipes <small><%=request.getAttribute("type")%></small>
	</h1>
</div>

<div class="row">
	<div class="col-md-2">
		<!-- Single button -->
		<%

		int saisonIndex = Integer.parseInt((String) request.getAttribute("saisonIndex"));
		List<Saison> saisons = (List<Saison>) request.getAttribute("saisons");
		String linkUrl = "";
		
		%>
		<div class="btn-group btn-saison">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				Saison <%= saisonIndex %> - <%= saisonIndex+1 %> <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
			<%
				for (Saison saison : saisons) {
					linkUrl = "/equipes/" + request.getAttribute("type") + "/"
							+ saison.getYear() + "/";
			%>
			<li><a href="<%=linkUrl%>"><%=saison.title()%></a></li>
			<%
				}
			%>
			</ul>
		</div>
		<div>
			<ul class="nav nav-pills nav-stacked"><%
					String active = "";
					linkUrl = "";
					List<Equipe> equipes = (List<Equipe>) request
							.getAttribute("equipes");
					Equipe equipe = (Equipe) request.getAttribute("equipe");

					for (Equipe iEquipe : equipes) {
						if (equipe != null && iEquipe.getId().equals(equipe.getId())) {
							active = "class=\"active\"";
						} else {
							active = "";
						}
				%>
				<li <%=active%> id=<%=iEquipe.getId()%>>
					<a href='/equipes/<c:out value="${equipeType}" />/<%= saisonIndex %>/<%=iEquipe.getId()%>/'><%=iEquipe.getNom()%></a>
				</li>
				<%
					}
					if (equipe == null) {
						active = "class=\"active\"";
					} else {
						active = "";
					}
				%>
				<li <%=active%>>
					<a href='/equipes/<c:out value="${equipeType}" />/<%= saisonIndex %>/nouvelle-equipe/'> + Nouvelle équipe</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="col-md-10">
		<%		
			if (equipe != null) {
				request.setAttribute("joueurs", equipe.getJoueurs());
		%>
		<%@ include file="equipe.jsp"%>
		<%
			} else {
		%>
		<%@ include file="nouvelleEquipe.jsp"%>
		<%
			}
		%>
	</div>
</div>

<%@ include file="modalAjoutJoueur.jsp"%>

<script type="text/javascript">
	
</script>

<%@ include file="footer.jsp"%>