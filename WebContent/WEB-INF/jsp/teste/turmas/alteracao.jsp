<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Academic Devoir</title>
<style type="text/css">
<%@ include file="/css/form.css" %>
</style>
</head>

<body>
	<form action='altera'>	
	<fieldset><legend>Alteração de turma:</legend><br/>	
		<input type="hidden" size="30" name="id" value="${turma.id }"/> <br />
		<p><label>Nome: </label><input type="text" size="30" name="novoNome" value="${turma.nome }"/></p>
	<p class="submit"><input type="submit" value="Alterar"/></p>
	</fieldset>
	</form>
	<div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
    <div id="link"><a href="<c:url value='/professores/home'/>">Página Principal</a></div><br/>
</body>
</html>