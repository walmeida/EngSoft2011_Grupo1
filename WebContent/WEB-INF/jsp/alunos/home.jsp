<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
<form action='cadastro' method="get"><input type="submit"
	value="Adicionar aluno" /></form>
<form action='lista' method="get"><input type="submit"
	value="Listar aluno" /></form>
<form action='remocao' method="get"><input type="submit"
	value="Remover aluno" /></form>
<form action='matricula' method="get"><input type="submit"
	value="Fazer matricula numa turma" /></form>
</body>
</html>