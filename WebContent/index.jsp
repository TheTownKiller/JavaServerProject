<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/estilos2.css">
<title>Adivina el número</title>
</head>
<body>
<h1>Adivina el número</h1>
<%
if(session.getAttribute("message") != null){
	 out.println("<h1>" + session.getAttribute("message") + "</h1>");
	 session.setAttribute("message", null);
}
if(session.getAttribute("intentos") != null){
	 session.setAttribute("intentos", null);
}
%>
<p>Elija los intervalos</p>
<form action="GuessNumber" method="post">
<label for="menor">
<h3>Menor</h3>
<input type="number" name="menor" />
<br/>
<br/>
</label>
<label for="mayor">
<h3>Mayor</h3>
<input type="number" name="mayor" />
<br/>
<br/>
</label>
<input type="submit" value="Jugar!"/>
</form>
</body>
</html>