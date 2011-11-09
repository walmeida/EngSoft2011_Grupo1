<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style type="text/css">
    <%@ include file="/css/form.css" %>
    </style>
    <title>Academic Devoir</title>
</head>

<body>
    <div id="menu">Lista de Alunos</div>
    <table>
    <thead>
        <tr>
        <th>Nome</th>
        <th>Login</th>
        <th>Email</th>
        <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${turma.alunos}" var="aluno">
            <tr>
                <td>${aluno.nome}</td>
                <td>${aluno.login}</td>
                <td>${aluno.email}</td>
                <td>
                    <form action='removeMatricula'>
                        <input type="hidden" name="idAluno" value="${aluno.id}">
                        <input type="hidden" name="idTurma" value="${turma.id}">
                        <input type="submit" value="Excluir aluno da turma"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table> 
    
<div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
<div id="link"><a href="<c:url value='/professores/home'/>">Página Principal</a></div><br/>

</body>
</html>