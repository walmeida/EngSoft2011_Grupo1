<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
		
	<h3>Listas de Exerc&iacute;cios da Turma</h3>
	
	<div>
		<ul>
			<c:if test ="${usuarioSession.usuario.privilegio == 'PROFESSOR' || usuarioSession.usuario.privilegio == 'ADMINISTRADOR'}">
				<c:forEach items="${listaDeListas }" var="lista">
        			<li><a href="<c:url value="/listasDeExercicios/${lista.id }"/>">${lista.propriedades.nome }</a></li>
        		</c:forEach>              
    		</c:if>
                	
            <c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO'}">
            	<!-- Listas de Exerc&iacute;cios corrigidas -->
            	<c:forEach items="${listaDeListas }" var="lista">
					<%boolean flag=true;%>
                	<c:forEach items="${lista.respostas }" var="resposta">
                		<c:if test ="${resposta.aluno.id == usuarioSession.usuario.id}">
        					<c:if test ="${resposta.propriedades.estado == 'CORRIGIDA'}">
        						<li><a href="<c:url value="/listasDeExercicios/resolver/${lista.id }"/>">${lista.propriedades.nome }</a> - Corrigida</li>
        						<%flag=false;%>
        					</c:if>
        		<!-- Listas de Exerc&iacute;cios finalizadas -->
        					<c:if test ="${resposta.propriedades.estado == 'FINALIZADA'}">
        						<li><a href="<c:url value="/listasDeExercicios/resolver/${lista.id }"/>">${lista.propriedades.nome }</a> - Finalizada</li>
        						<%flag=false;%>
        					</c:if>
        		<!-- Listas de Exerc&iacute;cios salvas -->
        					<c:if test ="${resposta.propriedades.estado == 'SALVA'}">
        						<li><a href="<c:url value="/listasDeExercicios/resolver/${lista.id }"/>">${lista.propriedades.nome }</a> - Salva</li>
        						<%flag=false;%>
        					</c:if>
        		<!-- Listas de Exerc&iacute;cios inicializdas -->        					
        					<c:if test ="<%=flag%>" >
        						<li><a href="<c:url value="/listasDeExercicios/resolver/${lista.id }"/>">${lista.propriedades.nome }</a> - Inicializada</li>
        						<%flag=false;%>
        					</c:if>
        				</c:if>
        			</c:forEach>
        		<!-- Listas de Exerc&iacute;cios não inicializadas -->
        			<c:if test ="<%=flag%>" >
	       				<li><a href="<c:url value="/listasDeExercicios/resolver/${lista.id }"/>">${lista.propriedades.nome }</a> - Não inicializada</li>
     				</c:if>
        		</c:forEach>        			
			</c:if>

		</ul>
	</div>	
	
	<!-- a href="<c:url value='questoes'/>">Voltar</a><br/ -->	
    <c:if test ="${usuarioSession.usuario.privilegio == 'PROFESSOR' || usuarioSession.usuario.privilegio == 'ADMINISTRADOR'}">
    	<form action="<c:url value="/listasDeExercicios/cadastro"/>">
		<fieldset class="fieldsetSemFormatacao">
			<!--input type="submit" value="Cadastrar nova lista"></input-->
			<a href="<c:url value='/listasDeExercicios/cadastro'/>">Cadastrar nova lista</a><br/>	
		</fieldset>
		</form>
		<a href="<c:url value='/login'/>">Sair</a>
    	<a href="<c:url value='/professores/home'/>">P&aacute;gina Principal</a><br/>              
    </c:if>
    <c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO' || usuarioSession.usuario.privilegio == 'MONITOR'}">
    	<a href="<c:url value='/login'/>">Sair</a>
    	<a href="<c:url value='/alunos/home'/>">P&aacute;gina Principal</a><br/>              
 	</c:if>
 	
</body>
</html>