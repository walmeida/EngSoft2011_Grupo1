<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

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
	<h2>Grupo 1 - Engenharia de Software</h2>	<form action='cadastra' accept-charset="utf-8">
	<fieldset>
	<legend>N&atilde;o &eacute; um professor cadastrado? </legend><br/>
		Preencha o formul&aacute;rio abaixo e clique no bot&atilde;o "Enviar".<br/><br/>
		Nome: <br/><input type="text" size="50" name="novo.nome"/><br/>
		Login : <br/><input type="text" size="30" name="novo.login"/><br/>
		Senha : <br/><input type="password" size="32" name="novo.senha"/><br/>
		E-mail : <br/><input type="text" size="30" name="novo.email"/><br/><br/>
		<input type="hidden" name="novo.privilegio" value="2"/>
	<input type="submit" value="OK"/>
	</fieldset>
	</form>
</body>
</html>