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
	<div id="wrapper">
	<div id="left"><%@ include file="home.jsp" %></div>
	<div id="right">
	<form action='cadastro' method="get">
	   <p class="submit"><input type="submit" value="Adicionar professor"/></p>
	</form>
	<form action='lista' method="get">
	   <p class="submit"><input type="submit" value="Listar professor"/></p>
	</form>
    <form action='remocao' method="get">
	   <p class="submit"><input type="submit" value="Remover professor"/></p>
	</form>
	</div></div>
	
</body>
</html>