<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="/css/form.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<form action='../altera'>
	<fieldset><legend>Alteração de Disciplina:</legend><br/>
		<input type="hidden" name="id" value="${disciplina.id}"/> <br />
		Digite o nome nome da disciplina e clique em "alterar".<br/>
		Novo nome: <br/><input type="text" size="30" name="novoNome" value="${disciplina.nome}"/><br/><br/>
	<input type="submit" value="Alterar"/>
	</fieldset>
	</form>
	<div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
	<div id="link"><a href="<c:url value='/disciplinas/home'/>">Voltar</a></div>
</body>
</html>