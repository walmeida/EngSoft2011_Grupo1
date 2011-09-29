<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Academic Devoir</title>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<form action='altera'>	
	<fieldset><legend>Altera&cceil;&atilde;o de turma:</legend><br/>	
		Id da turma a alterar: <br/><input type="text" size="30" name="id" /> <br />
		Novo nome: <br/><input type="text" size="30" name="novoNome"/><br/><br/>
	<input type="submit" value="Alterar"/>
	</fieldset>
	</form>
</body>
</html>