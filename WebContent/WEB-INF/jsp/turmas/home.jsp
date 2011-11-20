<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Academic Devoir</title></head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style> 
<body>
    <h1>Academic Devoir</h1>
    <h2>Grupo 1 - Engenharia de Software</h2>
    <br/><br/>
    
    <form action='../alteracao' method="get">
        <input type="hidden" value="${turma.id}" name="id"/>
        <!-- input type="submit" value="Alterar informações da turma"/-->
        <c:if test ="${usuarioSession.usuario.privilegio != 'ALUNO' && usuarioSession.usuario.privilegio != 'MONITOR'}">
        	<a href="<c:url value='../alteracao?id=${turma.id}'/>">Alterar informações da turma</a><br/>         
 		</c:if>
    </form>
    <form action='../remove' method="get">
        <input type="hidden" value="${turma.id}" name="id">
        <!--input type="submit" value="Excluir Turma"/-->
        <c:if test ="${usuarioSession.usuario.privilegio != 'ALUNO' && usuarioSession.usuario.privilegio != 'MONITOR'}">
        	<a href="<c:url value='../remove?id=${turma.id}'/>">Excluir Turma</a><br/>	         
 		</c:if>
    </form>
    <form action='../../listasDeExercicios/cadastro' method="get">
        <!-- input type="submit" value="Criar lista de exercícios"/-->
        <c:if test ="${usuarioSession.usuario.privilegio != 'ALUNO' && usuarioSession.usuario.privilegio != 'MONITOR'}">
        	<a href="<c:url value='../../listasDeExercicios/cadastro'/>">Criar lista de exercícios</a><br/>	         
 		</c:if>
    </form>
    <form action='../listaAlunos' method="get">
        <input type="hidden" value="${turma.id}" name="idTurma">
        <!--input type="submit" value="Gerenciar alunos"/-->
        <c:if test ="${usuarioSession.usuario.privilegio != 'ALUNO' && usuarioSession.usuario.privilegio != 'MONITOR'}">
        	<a href="<c:url value='../listaAlunos?idTurma=${turma.id}'/>">Gerenciar alunos</a><br/>	         
 		</c:if>
    </form>
    <form action='../../listasDeExercicios' method="get">
        <input type="hidden" value="${turma.id}" name="idTurma">
        <!--input type="submit" value="Resolver lista"/-->
        <a href="<c:url value='../../listasDeExercicios/listasTurma/${turma.id}'/>">Resolver lista</a><br/>
    </form>
    
    <!-- a href="<c:url value='/professores/listaTurmas?idProfessor=${usuarioSession.usuario.id}'/>">Meus cursos</a-->
    
    <a href="<c:url value='/login'/>">Sair</a>
    
    <c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO' || usuarioSession.usuario.privilegio == 'MONITOR'}">
    	<a href="<c:url value='/alunos/home'/>">Página Principal</a><br/>
 	</c:if>
    <c:if test ="${usuarioSession.usuario.privilegio == 'PROFESSOR' || usuarioSession.usuario.privilegio == 'ADMINISTRADOR'}">
    	<a href="<c:url value='/professores/home'/>">Página Principal</a><br/>    		         
 	</c:if>
    
</body>
</html>
