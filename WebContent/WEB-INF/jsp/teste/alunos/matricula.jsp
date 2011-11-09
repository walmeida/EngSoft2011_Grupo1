<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="/css/form.css" %>
</style>
<title>Matricula</title>
</head>

<body>
	<div id="wrapper">
	<div id="left"><%@ include file="home.jsp" %></div>
	<div id="right">
	<form action='inscreve'>
	<input type="hidden" name="idAluno" value="${usuarioSession.usuario.id}">
	<fieldset><legend>Inscri&ccedil;&atilde;o:</legend><br/>
	 Olá, ${usuarioSession.usuario.nome}, selecione a turma na qual você quer se matricular.<br/><br/>
	<select name="idTurma">
    <c:forEach items="${listaDeDisciplinas}" var="disciplina">
          <c:forEach items="${disciplina.turmas}" var="turma">
          <option value="${turma.id }">${disciplina.nome } - ${turma.nome }</option> 
          </c:forEach>
    </c:forEach>
    </select>
        <br /><br />        
    <input type="submit" value="Ok"/>
	</fieldset>
	</form>
	<div id="link"><a href="<c:url value='/login'/>">Sair</a></div><br/>
	<div id="link"><a href="<c:url value='/alunos/home'/>">P&aacute;gina Principal</a></div>
	</div>
	</div>
	</body>
</html>