<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Academic Devoir</title>
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
	<h1>Academic Devoir</h1>
	<p>Cadastrar nova turma</p>
	<form action='cadastra'>
		Nome: <input type="text" name="nova.nome"/><br/>
		Id do Professor: <input type="text" name="idProfessor"/><br/>
		Id da Disciplina: <input type="text" name="idDisciplina"/><br/>
	<input type="submit" value="OK"/>
	</form>
</body>
</html>