<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adivina el número</title>
<link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
<h1>Adivina el número</h1>
<%
if(session.getAttribute("error") != null){
	 out.println("<h1>" + session.getAttribute("error") + "</h1>");
	 session.setAttribute("error", null);
}
%>
<p>Todo listo, elija un número entre <% out.print(session.getAttribute("menor")); %> y <% out.print(session.getAttribute("mayor")); %></p>
<form action="GuessAttempt" method="post">
<input type="number" name="attempt" />
<br/>
<br/>
<input type="submit" value="Jugar!"/>
<input type="submit" value="Cancelar" name="cancelar"/>
</form>
<%
if(session.getAttribute("intentos") != null){
	ArrayList<Object> intentos = (ArrayList<Object>) session.getAttribute("intentos");
	out.println("<table style='background-color:rgba(0, 0, 0, 0);'><tr><th>Número de intento</th><th>Número intentado</th><th>Fecha del intento</th></tr>");
	for(int i=0; i<intentos.size(); i++){
		 out.println("<tr><th>"+ ((ArrayList<Object>) intentos.get(i)).get(0) +"</th>");
		 out.println("<th>"+ ((ArrayList<Object>) intentos.get(i)).get(1) +"</th>");
		 out.println("<th>"+ ((ArrayList<Object>) intentos.get(i)).get(2) +"</th></tr>");
	}
	out.println("</table>");
}
%>
</body>
</html>