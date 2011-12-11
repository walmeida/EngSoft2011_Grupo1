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
	<h2>Grupo 1 - Engenharia de Software</h2>
	<p>Lista de Professores</p><br/>

    <form action='cadastro'>
       	<a href="<c:url value='/professores/cadastro'/>">Inserir</a><br/>
    </form>

	
	<table>
    <thead>
        <tr>
        <th>id</th>
        <th>Nome</th>
        <th>E-mail</th>
        <th>Login</th>
        <th>Senha</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listaDeProfessores}" var="professor">
            <tr>
                <td>${professor.id}</td>
                <td>${professor.nome}</td>
                <td>${professor.email}</td>
                <td>${professor.login}</td>
                <td>${professor.senha}</td>
                <td><a href="./alteracao?id=${professor.id}">  Alterar</a> <a href="./remove?id=${professor.id}">  Excluir</a></td>
            </tr>
        </c:forEach>
        </form>
    </tbody>
</table>
<a href="<c:url value='/login'/>">Sair</a>
<a href="<c:url value='/professores/home'/>">Voltar</a><br/>

	
</body>
</html>