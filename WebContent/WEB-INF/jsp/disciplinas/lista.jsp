<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<h4>Lista de Disciplinas</h4>
	<a href="<c:url value='/turmas/cadastro'/>">Cadastrar nova turma</a>  
    <a href="<c:url value='/disciplinas/cadastro'/>">Cadastrar nova disciplina</a>

	<table>
    <thead>
        <tr>
        <th>id</th>
        <th>Nome</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${lista }" var="disciplina">
            <tr>
                <td>${disciplina.id}</td>
                <td>${disciplina.nome}</td>
                <td><a href="<c:url value='/disciplinas/alteracao/${disciplina.id}'/>"> Alterar</a> <a href="./remove?id=${disciplina.id}">  Excluir</a></td>
            </tr>
        </c:forEach>             
    </tbody>
	</table>
	
	<a href="<c:url value='/login'/>">Sair</a>
	    <c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO' || usuarioSession.usuario.privilegio == 'MONITOR'}">
    	<a href="<c:url value='/alunos/home'/>">Página Principal</a><br/>
 	</c:if>
    <c:if test ="${usuarioSession.usuario.privilegio == 'PROFESSOR' || usuarioSession.usuario.privilegio == 'ADMINISTRADOR'}">
    	<a href="<c:url value='/professores/home'/>">Página Principal</a><br/>    		         
 	</c:if>
</body>
</html>