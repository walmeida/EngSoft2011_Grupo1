package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.usp.ime.academicdevoir.arquivos.Arquivos;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Resource
public class ArquivosController {
	
	private Arquivos arquivos;
	private UsuarioSession usuarioLogado;

	public ArquivosController(Arquivos arquivos, UsuarioSession usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
		this.arquivos = arquivos;
	}
		
	@Post
	@Path("/questoes/{idDaQuestao}/envia")
	public void envia(UploadedFile arquivo, Long idDaQuestao) {
		arquivos.salva(arquivo, idDaQuestao);
	}

	@Get
	@Path("/arquivos/{idDaQuestao}")
	public FileDownload download(Long idDaQuestao) {
		return arquivos.carrega("//" + usuarioLogado.getUsuario().getLogin() + "//" + idDaQuestao);
	}
}
