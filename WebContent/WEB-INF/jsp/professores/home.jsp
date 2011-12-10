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
	<div align="center">
	    <a href="<c:url value='/professores/alteracao?id=${usuarioSession.usuario.id}'/>">Alterar dados pessoais</a><br/><br/>
	    <a href="<c:url value='/professores/listaTurmas?idProfessor=${usuarioSession.usuario.id}'/>">Meus cursos</a><br/><br/>    
	    <a href="<c:url value='/disciplinas/lista'/>">Listar disciplinas</a><br/><br/>
	    <a href="<c:url value='/questoes/cadastro'/>">Cadastrar questão</a><br/><br/>
	    <a href="<c:url value='/listasDeExercicios/cadastro'/>">Cadastrar Lista</a><br/><br/>
	    <a href="<c:url value='/turmas/cadastro'/>">Cadastrar Turma</a><br/><br/>
	    <a href="<c:url value='/disciplinas/cadastro'/>">Cadastrar disciplina</a><br/><br/>
    </div>
    <a href="<c:url value='/login'/>">Sair</a>
    
</body>
</html>
