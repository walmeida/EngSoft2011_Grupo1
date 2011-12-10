<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" charset="utf-8" media="screen" href="<c:url value="/css/jquery.ui.core.css"/>"/>
<link rel="stylesheet" type="text/css" charset="utf-8" media="screen" href="<c:url value="/css/jquery.ui.theme.css"/>"/>
<link rel="stylesheet" type="text/css" charset="utf-8" media="screen" href="<c:url value="/css/jquery.ui.resizable.css"/>"/>
<link rel="stylesheet" type="text/css" charset="utf-8" media="screen" href="<c:url value="/css/jquery.ui.dialog.css"/>"/>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.7.1.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.core.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.position.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.widget.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.mouse.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.draggable.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.resizable.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.dialog.min.js"/>"></script>

<script type="text/javascript" charset="utf-8">
	
	$(document).ready(function() {
		
		$('.alterarQuestao').click(function() {
			var valor = $(this).attr('id');
			var idDaQuestao = valor.match(/\d+/g);
			var listas = 0;
			var paginaAlteracao = '<c:url value="/questoes/vouf/"/>' + idDaQuestao;
			
			$.getJSON('<c:url value="/questoes/buscaListas/"/>' + idDaQuestao, function(json) {
				$('#confirmacao').empty();
				$('<span>Modificar esta questão causará modificação nas seguintes listas: <span><br/><br/>').appendTo('#confirmacao');
				$.each(json, function() {
					$('<span>' + this.nome + '</span><br/>').appendTo('#confirmacao');
					listas++;
				});
			})
			.success(function() {
				if (listas == 0) window.location.href =  paginaAlteracao;
				else {
					$( '#confirmacao' ).dialog({
						resizable: true,
						height:400,
						modal: true,
						buttons: {
							'Criar uma nova questão': function() {
								window.location.href = '<c:url value="/questoes/copia/"/>' + idDaQuestao;
							},
							'Alterar esta questão': function() {
								window.location.href =  paginaAlteracao;
							}
						}
					});
				}
			});
		});
	});
</script>


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
.fieldsetSemFormatacao{
	border:none;
	padding: 0px;
}
</style>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<div id="menu">
		<%@ include file="../questoes/menu.jsp" %><br/>
	</div>
	
	<h3>Questões de V ou F</h3>
		
	<div>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Enunciado</th>
					<th>Resposta</th>
					<th>Alterar</th>
					<th>Remover</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lista }" var="questao">
					<tr>
						<td>${questao.id }</td>
						<td>${questao.enunciado }</td>
						<td>${questao.resposta }</td>
						<td><button id="alterarQuestao${questao.id }" class="alterarQuestao">Alterar</button></td>
						<td>
							<form action="<c:url value="/questoes/vouf/${questao.id }"/>" method="post">
								<fieldset class="fieldsetSemFormatacao">
									<button name="_method" value="delete">Remover</button>
								</fieldset>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>	
	<form action="<c:url value="/questoes/cadastro"/>">
		<fieldset class="fieldsetSemFormatacao">
			<!--input type="submit" value="Cadastrar nova questão"></input-->
			<a href="<c:url value='/questoes/cadastro'/>">Cadastrar nova questão</a><br/>
		</fieldset>
	</form>	
	
	<div>
		<a href="<c:url value='/login'/>">Sair</a>
		<!-- a href="<c:url value='../questoes'/>">Voltar</a><br/ -->
	    <a href="<c:url value='/professores/home'/>">Página Principal</a><br/>
	</div>
	
	<div id="confirmacao">		
	</div>
</body>
</html>