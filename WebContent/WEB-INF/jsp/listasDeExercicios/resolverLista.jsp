<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.6.2.min.js"/>"></script>

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
									<input type="text" size="6" maxlength="6" name="idDaNovaQuestao" />
									<button type="submit" name="_method" value="put">alterar</button>
								</fieldset>
							</form>
						</td>
						<td>
						</td>							
					</tr>						
				</table>
		</c:forEach>
		
		
	</div>
	
</body>
</html>