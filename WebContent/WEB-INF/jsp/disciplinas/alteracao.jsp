<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>

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
	<div id="menu">Altera&ccedil;&atilde;o da Disciplina</div>
	<br/><br/>
	<form action='../altera' accept-charset="utf-8">
	<fieldset>
		<input type="hidden" name="id" value="${disciplina.id}"/> <br />
		Novo nome: <br/><input type="text" size="30" name="novoNome" value="${disciplina.nome}"/><br/><br/>
		<p class="submit"><input type="submit" value="Alterar"/></p>
	</fieldset>
	</form>
	</div>
	</div>
</body>
</html>