<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.7.1.min.js"/>"></script>
<script src="../javascript/jquery.validate.min.js" type="text/javascript"></script>

<script type="text/javascript">

$.validator.setDefaults({
    submitHandler: function() { document.forms["cadastro"].submit(); }
});

$().ready(function() {

    // validate signup form on keyup and submit
    $("#cadastro").validate({
        rules: {
        	"novo.nome": "required",
        	"novo.login": {
                required: true,
                minlength: 2
            },
            "novo.senha": {
                required: true,
                minlength: 5
            },
            "novo.email": {
                required: true,
                email: true
            }
        },
        messages: {
        	"novo.nome": "Por favor, digite um nome.",
        	"novo.login": {
                required: "Por favor, digite um login",
                minlength: "Seu login deve ter no mínimo 2 caracteres."
            },
            "novo.senha": {
                required: "Por favor, digite uma senha",
                minlength: "Sua senha deve ter no mínimo 5 caracteres"
            },
            "novo.email": "Entre com um e-mail válido."
        }
    });
});

</script>

<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<h4>Cadastro de Aluno</h4>
	<form class="cmxform" action='cadastra' id="cadastro">
	<fieldset style="width:350px;">
	<legend>N&atilde;o &eacute; um aluno cadastrado? </legend><br/>
		Preencha o formul&aacute;rio abaixo e clique no bot&atilde;o "Enviar".<br/><br/>
		<p> 
		    <label style="display:block;float:left;width:100px;text-align:right;margin:0px 5px 0px 0px;" for="novo.nome">Nome:</label>
		    <input    name="novo.nome"  type="text"     size="30" />
		</p>
		<p> 
		    <label style="display:block;float:left;width:100px;text-align:right;margin:0px 5px 0px 0px;" for="novo.login">Login:</label>
		    <input  name="novo.login" type="text"     size="30" />
		</p>
		<p>
		    <label style="display:block;float:left;width:100px;text-align:right;margin:0px 5px 0px 0px;" for="novo.senha">Senha:</label>
		    <input  name="novo.senha" type="password" size="30" />
		</p>
		<p>
		    <label style="display:block;float:left;width:100px;text-align:right;margin:0px 5px 0px 0px;" for="novo.email">E-mail:</label>
		    <input name="novo.email" type="text"     size="30" />
		</p> <br/>
		<input type="hidden" name="novo.privilegio" value="0"/>
	<input type="submit" value="Enviar"/>
	</fieldset>
	</form>
	<a href="<c:url value='/login'/>">Sair</a>
	
	<c:if test ="${usuarioSession.usuario.id != null}">
        <a href="<c:url value='/alunos/lista'/>">Voltar</a><br/>              
    </c:if>
</body>
</html>