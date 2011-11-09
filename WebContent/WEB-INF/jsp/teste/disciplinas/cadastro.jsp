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
	<form action='cadastra'>
	<fieldset><legend>Cadastro de Disciplina:</legend><br/>
		<p><label>Nome: </label><input type="text" size="30" name="nova.nome"/></p><br/><br/>
	<p class="submit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
	<div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
	<div id="link"><a href="<c:url value='/professores/home'/>">Voltar</a></div><br/>
</body>
</html>