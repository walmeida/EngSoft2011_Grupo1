<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
h4
{
color: black;
text-align: center;
font-family:"Times New Roman";
}
legend 
{
color: #8c550e;
}
form {
margin: 1em auto;
text-align: center;
}
</style>

<body>
    <br/><br/>
	<div>
		<form action="/academic-devoir/disciplinas/lista">
		<input type="submit" value="Listar disciplinas"></input><br/>
		</form>
		<form action="/academic-devoir/disciplinas/cadastro">
		<input type="submit" value="Cadastrar nova disciplina"></input><br/>
		</form>
		<form action="/academic-devoir/disciplinas/remocao">
		<input type="submit" value="Remover disciplina"></input><br/>
		</form>
		<form action="/academic-devoir/turmas/cadastro">
		<input type="submit" value="Cadastrar nova turma em uma disciplina"></input><br/>
		</form>
		<form action="/academic-devoir/disciplinas/alteracao">
		<input type="submit" value="Alterar dados da disciplina"></input>
		</form>

		<br/>
	</div>
</body>

</html>