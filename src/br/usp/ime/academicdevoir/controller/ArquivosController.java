package br.usp.ime.academicdevoir.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.usp.ime.academicdevoir.arquivos.Arquivos;
import br.usp.ime.academicdevoir.infra.Permission;
import br.usp.ime.academicdevoir.infra.Privilegio;
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
	/**
	 * Salva um arquivo enviado pelo usuário logado como resposta da questão de id fornecido no servidor.
	 * @param arquivo
	 * @param idDaQuestao
	 */
	public void envia(UploadedFile arquivo, Long idDaQuestao) {
		arquivos.salva(arquivo, idDaQuestao);
	}

	@Get
	@Path("/arquivos/{idDaQuestao}")
	/**
	 * Baixa o arquivo enviado pelo usuário logado como resposta da questão de id fornecido.
	 * @param idDaQuestao
	 * @return FileDownload
	 */
	public FileDownload download(Long idDaQuestao) {
		return arquivos.carrega("//" + usuarioLogado.getUsuario().getLogin() + "//" + idDaQuestao);
	}
	
	@Get
	@Path("/arquivos/{loginDoaluno}/{idDaQuestao}")
	@Permission({ Privilegio.ADMINISTRADOR, Privilegio.PROFESSOR })
	/**
	 * Baixa o arquivo enviado pelo aluno de login fornecido enviado como resposta da questão de id fornecido.
	 * @param loginDoAluno
	 * @param idDaQuestao
	 * @return FileDownload
	 */
	public FileDownload download(Long loginDoAluno, Long idDaQuestao) {
		return arquivos.carrega("//" + loginDoAluno + "//" + idDaQuestao);
	}
}
