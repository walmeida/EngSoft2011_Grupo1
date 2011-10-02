<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<form action='altera'>	
	<fieldset>
	<legend>Alterando dados cadastrais do aluno:</legend><br/>
		<input type="hidden" size="30" name="id" value="${aluno.id}"/>
		Novo Nome: <br/><input type="text" size="30" name="novoNome" value="${aluno.nome}"/><br/>
		Novo E-mail : <br/><input type="text" size="30" name="novoEmail" value="${aluno.email}"/><br/>
		Nova Senha : <br/><input type="password" size="30" name="novaSenha"/><br/><br/>
	<input type="submit" value="Enviar"/>
	</fieldset>
	</form>
</body>
</html>