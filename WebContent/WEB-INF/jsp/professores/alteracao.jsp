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
	<div id="menu">Alterando dados cadastrais do professor:</div>
	<form action='altera'>	
	<fieldset>
		Digite os novos dados cadastrais
		<input type="hidden" size="30" name="id" value="${professor.id}"/> <br />
		<p><label>Nome: </label><input type="text" size="30" name="novoNome" value="${professor.nome}"/></p>
		<p><label>E-mail : </label><input type="text" size="30" name="novoEmail" value="${professor.email}"/></p>
		<p><label>Senha : </label><input type="password" size="30" name="novaSenha"/></p>
	<p class="submit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
    </div>
    </div>
</body>
</html>