package br.usp.ime.academicdevoir.arquivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Component
@SessionScoped
public class Arquivos {

	private File pastaDeArquivos;
	private UsuarioSession usuarioSession;

	public Arquivos(ServletContext context, UsuarioSession usuarioSession) {
		String caminho = context.getRealPath("WEB-INF//arquivos//");
		pastaDeArquivos = new File(caminho);
		pastaDeArquivos.mkdirs();
		
		this.usuarioSession = usuarioSession;
	}

	public void salva(UploadedFile arquivo, Long idDaQuestao) {
		
		File pastaDaQuestao = new File(pastaDeArquivos, "//" + usuarioSession.getUsuario().getLogin() + "//" + idDaQuestao);
		
		if (!pastaDaQuestao.mkdirs()) {
			for (File arq : pastaDeArquivos.listFiles()) {
				arq.delete();
			}
		}		
		
		File destino = new File(pastaDaQuestao, arquivo.getFileName());

		try {
			IOUtils.copyLarge(arquivo.getFile(), new FileOutputStream(destino));
		} catch (IOException excecao) {
			throw new RuntimeException("Erro ao salvar arquivo", excecao);
		}
	}

	public FileDownload carrega(String caminho) {
		File pasta = new File(pastaDeArquivos, caminho);
		return new FileDownload(pasta, "text/plain", "\"" + pasta.listFiles()[0] + "\"", true);
	}
}
