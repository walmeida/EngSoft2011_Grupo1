<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<html>
<head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<form action='../disciplinas/cadastro' method="get">
        <input type="submit" value="Cadastrar disciplina"/>
    </form>
    <form action='../turmas/cadastro' method="get">
        <input type="submit" value="Cadastrar Turma"/>
    </form>
    <form action='../questoes' method="get">
        <input type="submit" value="Cadastrar questão"/>
    </form>
    <form action='../turmas/listaTurmasDoProfessor' method="get">
        <input type="hidden" value="${usuarioSession.usuario.id}" name="professor.id"/>
        <input type="submit" value="Meus cursos"/>
    </form>
</body>
</html>