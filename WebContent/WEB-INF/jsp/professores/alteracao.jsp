<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="/css/form2.css" %>
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
    $("#alteracao").validate({
        rules: {
        	"novoNome": {
        		required: true,
        		minlength: 5
        	},
            "novaSenha": {
                required: true,
                minlength: 5
            },
            "novoEmail": {
                required: true,
                email: true
            },
            "confirmaSenha": {
            	required: true,
            	equalTo: '#senha'
            }
        },
        messages: {
        	"novoNome": {
        		required: "Por favor, digite um nome.",
        		minlength: "Seu nome deve ter no mínimo 5 caracteres."
        	},
            "novaSenha": {
                required: "Por favor, digite uma senha.",
                minlength: "Sua senha deve ter no mínimo 5 caracteres."
            },
            "confirmaSenha": {
            	required: "Por favor, digite sua senha novamente.",
            	equalTo: "As senhas digitadas não coincidem."
            },
            "novoEmail": "Entre com um e-mail válido."
        }
    });
});

</script>
</head>

<body>
	<div id="wrapper">
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
	<div id="right">
	<div id="menu">Alterando dados cadastrais do professor:</div>
	<form id="alteracao" action='altera' method="post" accept-charset="utf-8">	
	<fieldset>
		Digite os novos dados cadastrais
		<input type="hidden" size="30" name="id" value="${professor.id}"/> <br />
		<p><label>Nome: </label><input type="text" size="30" name="novoNome" value="${professor.nome}"/></p>
		<p><label>E-mail : </label><input type="text" size="30" name="novoEmail" value="${professor.email}"/></p>
		<p><label>Senha : </label><input id="senha" type="password" size="32" name="novaSenha"/></p>
		<p><label>Confirmar senha : </label><input type="password" size="32" name="confirmaSenha"/></p>
	<p class="submit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
    </div>
    </div>
</body>
</html>