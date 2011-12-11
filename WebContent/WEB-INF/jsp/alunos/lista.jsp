<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="/css/form.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<div id="wrapper">
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
	<div id="right">
	<div id="menu">Lista de Alunos</div>
	
        <form action='cadastro'>
        	<!-- input type="submit" value="Inserir"/-->
        	<a href="<c:url value='/alunos/cadastro'/>">Inserir</a><br/>
        </form>
<!--        <form action='alteracao'>
        	<input type="submit" value="Alterar"/>
        </form>
        <form action='remocao'>
        	
        	<a href="<c:url value='/alunos/remocao'/>">Excluir</a><br/>
        </form>
        <form action='home'>
        	
		</form>
-->
    
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
        <c:forEach items="${listaDeAlunos}" var="aluno">
            <tr>
                <td>${aluno.id}</td>
                <td>${aluno.nome}</td>
                <td>${aluno.email}</td>
                <td>${aluno.login}</td>
                <td>${aluno.senha}</td>
                <td><a href="./alteracao?id=${aluno.id}">  Alterar</a> <a href="./remove?id=${aluno.id}">  Excluir</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
<div id="link"><a href="<c:url value='/professores/home'/>">Voltar</a></div><br/>
</div>
</div>
</body>
</html>