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
	<form action='remove'>
	<fieldset><legend>Remo&ccedil;&atilde;o:</legend><br/>
		Para remover um aluno, digite abaixo a ID correspondente e confirme. <br/><br/>
		ID: <br/><input type="text" size="30" name="id"/><br/><br/>
		<input type="submit" value="Remover"/>
	</fieldset>
	</form>
</body>
</html>