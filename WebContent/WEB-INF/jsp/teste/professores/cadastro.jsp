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
	<form action='cadastra'>
	<fieldset>
	<legend>N&atilde;o &eacute; um professor cadastrado? </legend><br/>
		Preencha o formul&aacute;rio abaixo e clique no bot&atilde;o "Enviar".<br/><br/>
		<p><label>Nome: </label><input type="text" size="30" name="novo.nome"/></p>
		<p><label>Login: </label><input type="text" size="30" name="novo.login"/></p>
		<p><label>Senha: </label><input type="password" size="30" name="novo.senha"/></p>
		<p><label>E-mail: </label><input type="text" size="30" name="novo.email"/></p><br/>
		<input type="hidden" name="novo.privilegio" value="2"/>
	<p class="submit"><input type="submit" value="OK"/></p>
	</fieldset>
	</form>
	</div></div>
</body>
</html>