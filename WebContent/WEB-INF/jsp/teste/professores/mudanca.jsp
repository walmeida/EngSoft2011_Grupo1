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
	<div id="wrapper">
	<div id="left"><%@ include file="home.jsp" %></div>
	<div id="right">
	<form action='mudarTipo'>
	<fieldset>
	<legend>Alterando aluno para professor:</legend><br/>
	<p><label>Id do usu&aacute;rio a alterar: </label><input type="text" size="30" name="id" /></p><br />
	<p class="sumbit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
	</div></div>
</body>
</html>