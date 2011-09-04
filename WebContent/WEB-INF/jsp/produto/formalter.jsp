<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD</title>
<style type="text/css">
body
{
background-color:#dfdfdf;
}
h1
{
color:black;
text-align:center;
}
p
{
font-family:"Verdana";
font-size:18px;
}
</style> 
</head>
<body>
	<h1>CRUD</h1>
	<p>Alterar produto</p>
	<form action='alterar'>
		Id do produto a alterar: <input type="text" name="aalterar.id" /> <br />
		Novo Nome: <input type="text" name="aalterar.nome"/><br/>
		Nova Descricao : <input type="text" name="aalterar.descricao"/><br/>
		Novo Preço : <input type="text" name="aalterar.preco"/><br/>
	<input type="submit" value="OK"/>
	</form>
</body>
</html>