<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<html>
<head>
<style type="text/css">
<%@ include file="/css/form.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
    <form action='cadastro' method="get"><input type="submit"
    value="Adicionar aluno" /></form>
    <form action='lista' method="get"><input type="submit"
    value="Listar aluno" /></form>
    <form action='remocao' method="get"><input type="submit"
    value="Remover aluno" /></form>
    <div id="link"><a href="<c:url value='/login'/>">Sair</a><br/></div>
</body>
</html>