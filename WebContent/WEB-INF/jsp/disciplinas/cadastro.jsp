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
	<div id="menu">Cadastro de Disciplinas</div>
	<br/><br/>
	<form action='cadastra'>
	<fieldset>
		<p><label>Nome: </label><input type="text" size="30" name="nova.nome"/></p><br/><br/>
	<p class="submit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
	
</body>
</html>