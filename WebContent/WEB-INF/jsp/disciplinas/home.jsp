<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>

<head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2><br/>
	<div>
		<form action="/academic-devoir/disciplinas/lista">
		<input type="submit" value="Listar disciplinas"></input>
		</form>
		<form action="/academic-devoir/disciplinas/cadastro">
		<input type="submit" value="Cadastrar nova disciplina"></input>
		</form>
		<form action="/academic-devoir/disciplinas/remocao">
		<input type="submit" value="Remover disciplina"></input>
		</form>
		<form action="/academic-devoir/turmas/cadastro">
		<input type="submit" value="Cadastrar nova turma em uma disciplina"></input>
		</form>
		<form action="/academic-devoir/disciplinas/alteracao">
		<input type="submit" value="Alterar dados da disciplina"></input>
		</form>

		<br/>
	</div>
</body>

</html>