<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Academic Devoir</title>
<style type="text/css">
body
{
background-color:#ffcfeb;
}
h1
{
background-color:#fea8ce;
font-family: "Times New Roman";
color:black;
}
p
{
font-family:"Times New Roman";
font-size:18px;
}
div
{
background-color:#fec2e5;
}
</style> 
</head>
<body>
	<h1>Academic Devoir</h1>
	<p>Remover disciplina</p>
	<form action='remove'>
		ID: <input type="text" name="id"/><br/>
		<input type="submit" value="Remover"/>
	</form>
</body>
</html>