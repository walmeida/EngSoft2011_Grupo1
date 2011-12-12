<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.7.1.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery.form.js"/>"></script>

<script type="text/javascript" charset="utf-8">	
	function redireciona() {		
		// URL Provisoria
		window.location.href = '<c:url value="/respostas/autocorrecao/${listaDeRespostas.id}"/>';
			
	}

	$(document).ready(function () {
		var restantes = ${numeroDeQuestoes };
		
		<c:forEach items="${listaDeExercicios.questoes}" varStatus="iteracao">
			$('#questao' + ${iteracao.index}).ajaxForm({
				success: function() {
					<c:choose>
						<c:when test="${iteracao.index eq numeroDeQuestoes - 1}">redireciona();</c:when>
						<c:otherwise>$('#questao' + ${iteracao.index + 1}).submit();</c:otherwise>
					</c:choose>	
		        }
			});		
		</c:forEach>
		
		$('#enviaRespostas').click(function() {
			$(this).attr("disabled", "disabled").empty().append("Enviando");
			$('#questao0').submit();
		});
	});
</script>

<style type="text/css">
<%@ include file="/css/form2.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<div id="wrapper"> 
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
	<div id="right">
	<div id="menu">Resolver lista de exerc&iacute;cios</div>
	<br/><br/>
	
	<table>
		<tr>
			<td>${listaDeExercicios.propriedades.nome}</td>
		</tr>
		<tr>
			<td>${listaDeExercicios.propriedades.enunciado}</td>
		</tr>
	</table>
	
	<h3>Questões</h3>
	
	<div>
		<c:forEach items="${listaDeExercicios.questoes}" var="questaoDaLista" varStatus="iteracao">
			<form id="questao${iteracao.index }" class="respostaForm" action="<c:url value="/respostas/${listaDeRespostas.id }/cadastra"/>" method="post" accept-charset="utf-8" enctype="multipart/form-data">
				<fieldset>
						<p>${iteracao.index + 1} )
								${questaoDaLista.questao.enunciado}</p>
						${questaoDaLista.questao.renderizacao}
				</fieldset>
			</form>
		</c:forEach>
		<button id="enviaRespostas" type="button">Salvar</button>
	</div>
	
    <a href="<c:url value='/login'/>">Sair</a>
        <c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO' || usuarioSession.usuario.privilegio == 'MONITOR'}">
    	<a href="<c:url value='/alunos/home'/>">Página Principal</a><br/>
 	</c:if>
    <c:if test ="${usuarioSession.usuario.privilegio == 'PROFESSOR' || usuarioSession.usuario.privilegio == 'ADMINISTRADOR'}">
    	<a href="<c:url value='/professores/home'/>">Página Principal</a><br/>    		         
 	</c:if>
 	</div>
 	</div>	
</body>
</html>