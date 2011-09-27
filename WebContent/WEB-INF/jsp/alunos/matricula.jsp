<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD</title>
<style type="text/css">
body
{
background-color:#dfdfdf;
}
h1
{
color:black;
text-align:center;
}
p
{
font-family:"Verdana";
font-size:18px;
}
</style> 
</head>
<body>
	<h1>Inscrever-se em Turma</h1>
	<p>Coloque o Id da turma na qual quer se matricular</p>
	<form action='inscreve'>
	 Olá, ${usuarioSession.usuario.nome}, coloque a id da turma na qual vc quer se matricular<br/>
		<!-- Id do Aluno: <input type="text" name="idAluno"/><br/> -->
		Id da Turma: <input type="text" name="idTurma"/><br/>
		<input type="submit" value="OK"/>
	</form>
	</body>
</html>