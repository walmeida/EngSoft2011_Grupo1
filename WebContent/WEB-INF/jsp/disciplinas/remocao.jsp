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
form {
margin: 3em auto;
text-align: center;
width: 25%;
}
h4
{
color: black;
text-align: center;
font-family:"Times New Roman";
}

</style> 
<body>
	<h4>Remover disciplina</h4>
	<form action='remove'>
	<fieldset>
	    <br/>Para remover uma disciplina, digite abaixo a ID correspondente e confirme. <br/><br/>
		ID: <br/><input type="text" name="id"/><br/><br/>
		<input type="submit" value="Remover"/>
	</fieldset>
	</form>
</body>
</html>