<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.6.2.min.js"/>"></script>

<script type="text/javascript" charset="utf-8">	
	$(document).ready(function() {		
		$('#formIncluirTurma').hide();
		$('#formIncluirQuestao').hide();
		$('[id^=formAlterarQuestao]').hide();
		
		$('#ativaIncluirTurma').click(function() {
			$('#formIncluirTurma').show();
			$(this).hide();
	  	});
		
		$('#ativaIncluirQuestao').click(function() {
			$('#formIncluirQuestao').show();
			$(this).hide();
		});
		
		$('.ativaAlterarQuestao').click(function() {
			var idBotaoClicado = $(this).attr('id');
			var numero = parseInt(idBotaoClicado.replace(/\D/g, ''), 10);
			$('#formAlterarQuestao'+numero).show();
			$(this).hide();
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
	
	<h3>Propriedades da Lista</h3>
	<table>
		<tr>
			<td>Nome:</td>
			<td>${listaDeExercicios.propriedades.nome }</td>
		</tr>
		<tr>
			<td>Enunciado:</td>
			<td>${listaDeExercicios.propriedades.enunciado }</td>
		</tr>
		<tr>
			<td>Turma:</td>
			<td>${listaDeExercicios.turma.disciplina.nome } - ${listaDeExercicios.turma.nome }</td>
		</tr>
		<tr>
			<td>Visível:</td>
			<td>
				<c:choose>
					<c:when test="${listaDeExercicios.propriedades.visivel }">Sim</c:when>
					<c:otherwise>Não</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>Peso:</td>
			<td>${listaDeExercicios.propriedades.peso }</td>
		</tr>
		<tr>
			<td>Prazo de Entrega:</td>
			<td>${prazo }</td>
		</tr>
		<tr>
			<td>Número Máximo de Envios:</td>
			<td>
				<c:choose>
					<c:when test="${empty (listaDeExercicios.propriedades.numeroMaximoDeEnvios) or (listaDeExercicios.propriedades.numeroMaximoDeEnvios eq 0)}">
						Ilimitado
					</c:when>
					<c:otherwise>
						${listaDeExercicios.propriedades.numeroMaximoDeEnvios }
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>Auto Correção:</td>
			<td>
				<c:choose>
					<c:when test="${listaDeExercicios.propriedades.autoCorrecao eq 'DESATIVADA' }">
						Desabilitada
					</c:when>
					<c:when test="${listaDeExercicios.propriedades.autoCorrecao eq 'PROFESSOR' }">
						Somente Professor
					</c:when>
					<c:otherwise>
						Professor e Aluno
					</c:otherwise>
				</c:choose>
			</td>
		</tr>		
	</table>
	<form action="<c:url value="/listasDeExercicios/altera/${listaDeExercicios.id }"/>">
		<fieldset class="fieldsetSemFormatacao">
			<button type="submit">Alterar</button>
			<a href="<c:url value='/listasDeExercicios/resolver/${listaDeExercicios.id }'/>">Resolver Lista</a>
		</fieldset>
	</form>
	
	<form action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }"/>" method="post">
		<fieldset class="fieldsetSemFormatacao">
			<button type="submit" name="_method" value="delete">Excluir Lista</button>
		</fieldset>
	</form>
	
	<h3>Questões</h3>
	
	<div>
		<c:forEach items="${listaDeExercicios.questoes }" var="questaoDaLista" varStatus="status">
				<table>
					<tr>
						<td>${status.index+1 }. ${questaoDaLista.questao.enunciado }</td>
						<td>
							<button id="ativaAlterarQuestao${status.index }" class="ativaAlterarQuestao" type="button">Alterar Questão</button>
							<form id="formAlterarQuestao${status.index }" action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/questoes/${status.index }"/>" method="post" accept-charset="us-ascii">							
								<fieldset class="fieldsetSemFormatacao">
									<label for="idDaNovaQuestao">ID da nova Questão:</label>
									<input type="text" value="${questaoDaLista.questao.id}" size="6" maxlength="6" name="idDaNovaQuestao" />
									<input type="text" value="${questaoDaLista.ordem}" size="6" maxlength="6" name="ordemDaQuestao" />
									<button type="submit" name="_method" value="put">alterar</button>
								</fieldset>
							</form>
						</td>
						<td>
							<form action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/questoes/${status.index }"/>" method="post" accept-charset="us-ascii">
								<fieldset class="fieldsetSemFormatacao">
									<button type="submit" name="_method" value="delete">Remover</button>
								</fieldset>
							</form>
						</td>							
					</tr>						
				</table>
		</c:forEach>
		
		<button id="ativaIncluirQuestao" type="button">Incluir nova Questao</button>
		
		<form id="formIncluirQuestao" action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/questoes/inclui"/>" method="post">
			<fieldset class="fieldsetSemFormatacao">
				
				<label id="labelIdDaQuestao" for="idDaQuestao">ID da Questao:</label>
				<input id="idDaQuestao" type="text" size="6" maxlength="6" name="idDaQuestao" />
				<label id="labelPesoDaQuestao" for="pesoDaQuestao">Peso:</label>
				<input id="pesoDaQuestao" type="text" size="6" maxlength="6" name="pesoDaQuestao" />
				<label id="labelOrdemDaQuestao" for="ordemDaQuestao">Ordem:</label>
				<input id="ordemDaQuestao" type="text" size="6" maxlength="6" name="ordemDaQuestao" />
				<button id="incluiQuestao" type="submit" name="_method" value="put">Incluir</button>
			</fieldset>
		</form>
	</div>
	<a href="<c:url value='/login'/>">Sair</a>
    <!--  TODO a href="<c:url value='/alunos/home'/>">Página Principal</a><br/> -->
</body>
</html>