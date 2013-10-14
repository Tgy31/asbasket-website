<%-- Set the content type header with the JSP directive --%>
<%@ page contentType="text"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.testjee1.Joueur"%>

<% 
out.println("{\"aaData\": [");

List<Joueur> joueurs = (List<Joueur>)request.getAttribute("joueurs");
int i = 0;
for (Joueur joueur : joueurs)
{
	i++;
	out.println("[");
	out.println("\"<input type=\'checkbox\' name=\'check\' value=\'"+ joueur.getId() +"\'>\",");
	out.println("\"" + joueur.getFirstName() + "\",");
	out.println("\"" + joueur.getLastName() + "\",");
	out.println("\"" + joueur.getYear() + "\",");
	out.println("\"" + joueur.getOption() + "\",");
	out.println("\"" + joueur.getMail() + "\"");
	out.println("]");
	if (i < joueurs.size()) {
		out.println(",");
		}
}

out.println("]}");
%>