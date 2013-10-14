<%-- Set the content type header with the JSP directive --%>
<%@ page contentType="text"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.testjee1.Adherent"%>

<% 
out.println("{\"aaData\": [");

List<Adherent> adherents = (List<Adherent>)request.getAttribute("adherents");
int i = 0;
for (Adherent adherent : adherents)
{
	i++;
	out.println("[");
	out.println("\"<input type=\'checkbox\' name=\'check\' value=\'"+ adherent.getId() +"\'>\",");
	out.println("\"" + adherent.getFirstName() + "\",");
	out.println("\"" + adherent.getLastName() + "\",");
	out.println("\"" + adherent.getYear() + "\",");
	out.println("\"" + adherent.getOption() + "\",");
	out.println("\"" + adherent.getMail() + "\"");
	out.println("]");
	if (i < adherents.size()) {
		out.println(",");
		}
}

out.println("]}");
%>