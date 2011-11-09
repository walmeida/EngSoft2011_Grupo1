<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Academic Devoir</title></head>
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
    <div id="menu">Lista de Turmas</div>
    <table>
        <c:forEach items="${professor.turmas}" var="turma">
            <tr>
                <td>
                    <a href="<c:url value="/turmas/home/${turma.id}"/>">
                        ${turma.disciplina.nome} - ${turma.nome} </a>
                </td>
                <td><div id="link"><a href="../turmas/alteracao?id=${turma.id}">  Alterar</a></div> <div id="link"><a href="../turmas/remove?id=${turma.id}">  Excluir</a></div></td>
            </tr>
        </c:forEach>
    </table>
    <form action='../turmas/cadastro'>
            <!-- input type="submit" value="Cadastrar nova turma"/-->
            <a href="<c:url value='../turmas/cadastro'/>">Cadastrar nova turma</a><br/>
	</form>
    
    <div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
    <div id="link"><a href="<c:url value='/professores/home'/>">Página Principal</a></div><br/>
    </div>
    </div>
</body>
</html>