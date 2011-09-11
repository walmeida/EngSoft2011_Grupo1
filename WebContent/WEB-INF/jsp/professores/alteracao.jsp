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

legend 
{
color: #8c550e;
}
form {
margin: 3em auto;
width: 30%;
}
</style> 

<body>
	<form action='altera'>	
	<fieldset>
	<legend>Alterando dados cadastrais do professor:</legend><br/>
		Id do professor a alterar: <br/><input type="text" size="30" name="id" /> <br />
		Novo Nome: <br/><input type="text" size="30" name="novoNome"/><br/>
		Nova E-mail : <br/><input type="text" size="30" name="novoEmail"/><br/>
		Novo Senha : <br/><input type="password" size="32" name="novaSenha"/><br/><br/>
	<input type="submit" value="Enviar"/>
	</fieldset>
	</form>
</body>
</html>