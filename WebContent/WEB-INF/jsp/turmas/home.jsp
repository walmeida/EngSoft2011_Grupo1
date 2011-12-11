<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head><title>Academic Devoir</title></head>
<style type="text/css">
<%@ include file="/css/form2.css" %>
</style> 

<body>
    <div id="wrapper"> 
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
	<div id="right">
    <div id="menu">${turma.disciplina.nome} - ${turma.nome}</div>
    <br/><br/>
    Listar listas aqui
    
    <form action='../../listasDeExercicios/cadastro' method="get">
        <c:if test ="${usuarioSession.usuario.privilegio != 'ALUNO' && usuarioSession.usuario.privilegio != 'MONITOR'}">
        	<a href="<c:url value='../../listasDeExercicios/cadastro'/>">Criar lista de exerc&iacute;cios</a><br/>	         
 		</c:if>
    </form>
    <form action='../listaAlunosEListas' method="get">
        <input type="hidden" value="${turma.id}" name="idTurma">
        <c:if test ="${usuarioSession.usuario.privilegio != 'ALUNO' && usuarioSession.usuario.privilegio != 'MONITOR'}">
        	<a href="<c:url value='../listaAlunosEListas?idTurma=${turma.id}'/>">Listas entregues (por aluno)</a><br/>	         
 		</c:if>
    </form>
    <form action='../listaAlunos' method="get">
        <input type="hidden" value="${turma.id}" name="idTurma">
        <c:if test ="${usuarioSession.usuario.privilegio != 'ALUNO' && usuarioSession.usuario.privilegio != 'MONITOR'}">
        	<a href="<c:url value='../listaAlunos?idTurma=${turma.id}'/>">Gerenciar alunos</a><br/>	         
 		</c:if>
    </form>
    <form action='../../listasDeExercicios' method="get">
        <input type="hidden" value="${turma.id}" name="idTurma">
        <c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO'}">
        	<a href="<c:url value='../../listasDeExercicios/listasTurma/${turma.id}'/>">Resolver lista</a><br/>
        </c:if>
    </form>
    
    
    
    <form action='../alteracao' method="get">
        <input type="hidden" value="${turma.id}" name="id"/>
        <c:if test ="${usuarioSession.usuario.privilegio != 'ALUNO' && usuarioSession.usuario.privilegio != 'MONITOR'}">
        	<a href="<c:url value='../alteracao?id=${turma.id}'/>">Alterar informações da turma</a>         
 		</c:if>
    </form>
    <form action='../remove' method="get">
        <input type="hidden" value="${turma.id}" name="id">
        <c:if test ="${usuarioSession.usuario.privilegio != 'ALUNO' && usuarioSession.usuario.privilegio != 'MONITOR'}">
        	<a href="<c:url value='../remove?id=${turma.id}'/>">Excluir Turma</a><br/>	         
 		</c:if>
    </form>
    
    </div>
    </div>
</body>
</html>