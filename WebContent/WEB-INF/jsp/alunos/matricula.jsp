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
	<form action='inscreve'>
	<fieldset><legend>Inscri&ccedil;&atilde;o:</legend><br/>
	 Olá, ${usuarioSession.usuario.nome}, coloque a id da turma na qual você quer se matricular.<br/><br/>
		<!-- Id do Aluno: <input type="text" name="idAluno"/><br/> -->
		Id da Turma: <input type="text" size="30" name="idTurma"/><br/><br/>
		<input type="submit" value="OK"/>
	</fieldset>
	</form>
	</body>
</html>