<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

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
	<div id="menu">Alterar dados cadastrais:</div>
	<form action='altera' accept-charset="utf-8">	
	<fieldset>
		Digite os novos dados cadastrais
		<input type="hidden" size="30" name="id" value="${aluno.id}"/>
		<p><label>Nome: </label><input type="text" size="50" name="novoNome" value="${aluno.nome}"/></p>
		<p><label>E-mail : </label><input type="text" size="30" name="novoEmail" value="${aluno.email}"/></p>
		<p><label>Senha : </label><input type="password" size="30" name="novaSenha"/></p>
	<p class="submit"><input type="submit" value="Enviar"/><br/></p>
	</fieldset>
	</form>
	</div>
	</div>
</body>
</html>