<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
<title>Academic Devoir</title>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.7.1.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery.validate.min.js"/>"></script>
<script type="text/javascript" charset="utf-8">

$.validator.setDefaults({
    submitHandler: function() { document.forms["cadastro"].submit(); }
});

$().ready(function() {

    // validate signup form on keyup and submit
    $("#cadastro").validate({
        rules: {
        	"novo.nome": {
        		required: true,
        		minlength: 5
        	},
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
            },
            "confirmaSenha": {
            	required: true,
            	equalTo: '#senha'
            }
        },
        messages: {
        	"novo.nome": {
        		required: "Por favor, digite um nome.",
        		minlength: "Seu login deve ter no mínimo 5 caracteres."
        	},
        	"novo.login": {
                required: "Por favor, digite um login.",
                minlength: "Seu login deve ter no mínimo 2 caracteres."
            },
            "novo.senha": {
                required: "Por favor, digite uma senha.",
                minlength: "Sua senha deve ter no mínimo 5 caracteres."
            },
            "confirmaSenha": {
            	required: "Por favor, digite sua senha novamente.",
            	equalTo: "As senhas digitadas não coincidem."
            },
            "novo.email": "Entre com um e-mail válido."
        }
    });
});

</script>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>	
	<form id="cadastro" action='cadastra' method="post" accept-charset="utf-8">
	<fieldset>
	<legend>N&atilde;o &eacute; um professor cadastrado? </legend><br/>
		Preencha o formul&aacute;rio abaixo e clique no bot&atilde;o "Enviar".<br/><br/>
		Nome: <br/><input type="text" size="50" name="novo.nome"/><br/>
		Login : <br/><input type="text" size="30" name="novo.login"/><br/>
		Senha : <br/><input id="senha" type="password" size="30" name="novo.senha"/><br/>
		Confirmar senha : <br/><input type="password" size="30" name="confirmaSenha"/><br/>		
		E-mail : <br/><input type="text" size="30" name="novo.email"/><br/><br/>
		<input type="hidden" name="novo.privilegio" value="2"/>
	<input type="submit" value="OK"/>
	</fieldset>
	</form>
</body>
</html>