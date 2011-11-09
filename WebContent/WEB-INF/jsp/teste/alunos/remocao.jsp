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
	<form action='remove'>
	<fieldset><legend>Remo&ccedil;&atilde;o:</legend><br/>
		Para remover um aluno, digite abaixo a ID correspondente e confirme. 
		<p><label>ID: </label><input type="text" size="30" name="id"/></p>
		<p class="submit"><input type="submit" value="Remover"/></p>
	</fieldset>
	</form>
	<div id="link"><a href="<c:url value='/login'/>">Sair</a></div><br/>
	<div id="link"><a href="<c:url value='/alunos/home'/>">Página Principal</a></div>
</body>
</html>