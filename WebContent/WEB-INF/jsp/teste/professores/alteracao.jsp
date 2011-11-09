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
	<form action='altera'>	
	<fieldset>
	<legend>Alterando dados cadastrais do professor:</legend><br/>
		Digite os novos dados cadastrais
		<input type="hidden" size="30" name="id" value="${professor.id}"/> <br />
		<p><label>Nome: </label><input type="text" size="30" name="novoNome" value="${professor.nome}"/></p>
		<p><label>E-mail : </label><input type="text" size="30" name="novoEmail" value="${professor.email}"/></p>
		<p><label>Senha : </label><input type="password" size="30" name="novaSenha"/></p>
	<p class="submit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
	<div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
    <div id="link"><a href="<c:url value='/professores/home'/>">Página Principal</a></div><br/>
    </div>
    </div>
</body>
</html>