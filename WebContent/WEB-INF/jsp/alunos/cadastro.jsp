<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head>

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
        		minlength: "Seu nome deve ter no mínimo 5 caracteres."
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

<style type="text/css">
<%@ include file="/css/form2.css" %>
</style>
<title>Cadastro de Aluno</title>
</head>

<body>
	<div id="wrapper">
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="right">
	<form style="width:800px;" class="cmxform" action='cadastra' id="cadastro" method="post" accept-charset="utf-8">
	<fieldset style="width:600px;">
	<legend>N&atilde;o &eacute; um aluno cadastrado? </legend><br/>
		Preencha o formul&aacute;rio abaixo e clique no bot&atilde;o "Enviar".<br/><br/>
		<p> 
		    <label style="display:block;float:left;width:100px;text-align:right;margin:0px 5px 0px 0px;" for="novo.nome">Nome:</label>
		    <input    name="novo.nome"  type="text"     size="50" value="${novo.nome }" />
		</p>
		<p> 
		    <label style="display:block;float:left;width:100px;text-align:right;margin:0px 5px 0px 0px;" for="novo.login">Login:</label>
		    <input  name="novo.login" type="text"     size="30" value="${novo.login }" />
		</p>
		<p>
		    <label style="display:block;float:left;width:100px;text-align:right;margin:0px 5px 0px 0px;" for="novo.senha">Senha:</label>
		    <input id="senha" name="novo.senha" type="password" size="30" value="${novo.senha }" />
		</p>
		<p>
		    <label style="display:block;float:left;width:100px;text-align:right;margin:0px 5px 0px 0px;" for="confirmaSenha">Confirmar senha:</label>
		    <input name="confirmaSenha" type="password" size="30" value="${novo.senha }" />
		</p>
		<br/>
		<p>
		    <label style="display:block;float:left;width:100px;text-align:right;margin:0px 5px 0px 0px;" for="novo.email">E-mail:</label>
		    <input name="novo.email" type="text"     size="30" value="${novo.email }" />
		</p> <br/>
		<input type="hidden" name="novo.privilegio" value="0"/>
	<p class="submit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
	<div id="link"><a href="<c:url value='/login'/>">Voltar</a></div>
	
	<c:if test ="${usuarioSession.usuario.id != null}">
        <div id="link"><a href="<c:url value='/alunos/lista'/>">Voltar</a><br/></div>              
    </c:if>
    </div>
    </div>
</body>
</html>