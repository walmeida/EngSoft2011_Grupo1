<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<h1>Academic Devoir</h1>
<h2>Grupo 1 - Engenharia de Software</h2>
</head>
<style type="text/css">
body
{
background-color:#f0ecc6;
}
h1 
{
color: black;
text-align: center;
font-size: 40px;
font-family:"Times New Roman";
font-style: italic;
font-variant: small-caps;
}
h2
{
color: black;
text-align: center;
font-size: 20px;
font-family:"Times New Roman";
}
h4
{
color: black;
text-align: center;
font-family:"Times New Roman";
}
legend 
{
color: #8c550e;
}
form {
margin: 3em auto;
width: 35%;
}

</style> 
<body>
	<h4>Cadastro de Aluno</h4>
	<form action='cadastra'>
	<fieldset>
	<legend>N&atildeo &eacute um aluno cadastrado? </legend><br/>
		Preencha o formul&aacuterio abaixo e clique no bot&atildeo "Enviar".<br/><br/>
		Nome: <br/><input type="text" size="30" name="novo.nome"/><br/>
		Login : <br/><input type="text" size="30" name="novo.login"/><br/>
		Senha : <br/><input type="password" size="32" name="novo.senha"/><br/>
		E-mail : <br/><input type="text" size="30" name="novo.email"/><br/><br/>
	<input type="submit" value="Enviar"/>
	</fieldset>
	</form>
</body>
</html>