<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<h1>Academic Devoir</h1>
<h2>Grupo 1 - Engenharia de Software</h2>
</head>
<style type="text/css">
body
{
background-color:#f0ecc6;
}
h1 
{
color: black;
text-align: center;
font-size: 40px;
font-family:"Times New Roman";
font-style: italic;
font-variant: small-caps;
}
h2
{
color: black;
text-align: center;
font-size: 20px;
font-family:"Times New Roman";
}
form {
margin: 1em auto;
text-align: center;
}
</style> 

<body>
	<br/><br/>
	<form action='cadastro' method="get">
	<input type="submit" value="Adicionar professor"/>
	</form>
	<form action='lista' method="get">
	<input type="submit" value="Listar professor"/>
	</form>
	<form action='remocao' method="get">
	<input type="submit" value="Remover professor"/>
	</form>
</body>
</html>