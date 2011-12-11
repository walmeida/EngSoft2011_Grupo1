<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head><title>Academic Devoir</title></head>
<head>
<style type="text/css">
<%@ include file="/css/form2.css" %>
</style>
<title>Lista de Turmas</title>
</head>

<body>
	<div id="wrapper"> 
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
	<div id="right">
    <div id="menu">Lista de Turmas</div>
    <table>
        <c:forEach items="${aluno.turmas}" var="turma">
            <tr>
                <td>
                    <a href="<c:url value="/turmas/home/${turma.id }"/>">
                        ${turma.disciplina.nome} - ${turma.nome} </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action='matricula'>
    <!-- input type="submit" value="Inscrever-me em um nova turma"/-->
    <a href="<c:url value='/alunos/matricula'/>">Inscrever-me em uma nova turma</a><br/>
    </form>
    </div>
    </div>
</body>
</html>
