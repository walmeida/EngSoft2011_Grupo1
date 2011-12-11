<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" charset="utf-8" media="screen" href="<c:url value="/css/jquery.ui.core.css"/>"/>
<link rel="stylesheet" type="text/css" charset="utf-8" media="screen" href="<c:url value="/css/jquery.ui.theme.css"/>"/>
<link rel="stylesheet" type="text/css" charset="utf-8" media="screen" href="<c:url value="/css/jquery.ui.autocomplete.css"/>"/>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.7.1.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.core.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.position.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.widget.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-ui/jquery.ui.autocomplete.min.js"/>"></script>
<script type="text/javascript" charset="utf-8">
	function split( val ) {
		return val.split( /,\s*/ );
	}
	
	function extractLast( term ) {
		return split( term ).pop();
	}
	
	$(document).ready(function(){
		//Adaptado do exemplo do JQuery UI 
	 	$('#tags')
		.bind('keydown', function(event) {
			if (event.keyCode === $.ui.keyCode.TAB &&
					$(this).data('autocomplete').menu.active) {
				event.preventDefault();
			}
		})
		.autocomplete({
			source: function(request, response) {
				$.getJSON('<c:url value="/questoes/tags/autocompletar.json"/>', {
					term: extractLast(request.term)
				}, 
				function(result) {
					response($.map(result, function(item) {
						return item.nome;
					}));
				});
			},
			search: function() {
				var term = extractLast(this.value);
				if (term.length < 2) {
					return false;
				}
			},
			focus: function() {
				return false;
			},
			select: function(event, ui) {
				var terms = split(this.value);
				terms.pop();
				terms.push(ui.item.value);
				terms.push("");
				this.value = terms.join(", ");
				return false;
			}
		});
	});	
</script>
<style type="text/css">
<%@ include file="/css/form2.css" %>
</style>
</head>

<body>
<div id="wrapper">
    <div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
    <div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
    <div id="right">
    <div id="menu">Alterar Questão</div></br>
	<div>
		<%@ include file="../questoes/menu.jsp" %><br/>
	</div>
	
	<div>
		<br/>
		<form action="<c:url value="/questoes/texto/${questao.id }" />" method="post" accept-charset="utf-8">
			<br/>
				<label for="enunciado">Enunciado:</label><br/>
					<textarea id="enunciado" rows= "5" cols="80" name="questao.enunciado">${questao.enunciado }</textarea>
				<br/>
				<label for="tags">Tags: </label>
					<input id="tags" type="text" name="tags" value="${tags }"></input>
				<br/>
				<label for="resposta">Resposta:</label><br/>
					<textarea id="resposta" rows= "5" cols="80" name="questao.resposta">${questao.resposta }</textarea>
				<br/><br/>
				<button type="submit" name="_method" value="put">Salvar altera&ccedil;&otilde;es</button>
		</form>
		<br/>
	</div>
	</div>
	</div>
</body>
</html>