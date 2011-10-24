package br.usp.ime.academicdevoir.controller;

import java.io.File;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.usp.ime.academicdevoir.arquivos.Arquivos;

@Resource
public class ArquivosController {
	
	private Arquivos arquivos;
	private Result result;

	public ArquivosController(Arquivos arquivos, Result result) {
		this.result = result;
		this.arquivos = arquivos;
	}
	
	
	@Get
	@Path("/questoes/{idDaQuestao}/upload")
	public void upload(Long idDaQuestao) {
		result.include("idDaQuestao", idDaQuestao);
	}
	
	@Post
	@Path("/questoes/{idDaQuestao}/envia")
	public void envia(UploadedFile arquivo, Long idDaQuestao) {
		arquivos.salva(arquivo, idDaQuestao);
		//result.redirectTo
	}

	@Get
	@Path("/arquivos/{caminho}")
	public FileDownload download(String caminho) {
		return arquivos.carrega(caminho);
	}
}
