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
	
	<form action='alteracao' method="get">
	    <input type="hidden" value="${usuarioSession.usuario.id}" name="id"/>
        <a href="<c:url value='/alunos/alteracao?id=${usuarioSession.usuario.id}'/>">Alterar dados pessoais</a><br/>
    </form>
	<form action='/disciplinas/cadastro' method="get">
        <!--input type="submit" value="Cadastrar disciplina"/-->
        <a href="<c:url value='/disciplinas/cadastro'/>">Cadastrar disciplina</a><br/>
    </form>
    <form action='../turmas/cadastro' method="get">
        <!--input type="submit" value="Cadastrar Turma"/-->
        <a href="<c:url value='/turmas/cadastro'/>">Cadastrar Turma</a><br/>
    </form>
    <form action='../questoes' method="get">
        <!--input type="submit" value="Cadastrar questão"/-->
        <a href="<c:url value='/questoes/cadastro'/>">Cadastrar questão</a><br/>
    <form action='listaTurmas' method="get">
        <input type="hidden" value="${usuarioSession.usuario.id}" name="idProfessor"/>
        <a href="<c:url value='/professores/listaTurmas?idProfessor=${usuarioSession.usuario.id}'/>">Meus cursos</a><br/>
    </form>
    <a href="<c:url value='/login'/>">Sair</a>
</body>
</html>