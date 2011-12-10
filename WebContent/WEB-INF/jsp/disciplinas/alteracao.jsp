<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="/css/form2.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<div id="wrapper"> 
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
	<div id="right">
	<div id="menu">Altera&ccedil;&atilde;o de Disciplinas</div>
	<br/><br/>
	<form action='../altera'>
	<fieldset>
		<input type="hidden" name="id" value="${disciplina.id}"/> <br />
		Digite o nome nome da disciplina e clique em "alterar".<br/>
		Novo nome: <br/><input type="text" size="30" name="novoNome" value="${disciplina.nome}"/><br/><br/>
		<p class="submit"><input type="submit" value="Alterar"/></p>
	</fieldset>
	</form>
	</div>
	</div>
</body>
</html>