package br.usp.ime.academicdevoir.arquivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.usp.ime.academicdevoir.infra.UsuarioSession;

@Component
@SessionScoped
public class Arquivos {

	private File pastaDeArquivos;
	private UsuarioSession usuarioSession;
	private ServletContext context;

	public File getPastaDaQuestao(Long idDaQuestao) {
	    return new File(pastaDeArquivos, "//" + 
	            usuarioSession.getUsuario().getLogin() + "//" + idDaQuestao);
	}
	
	public String getCaminhoReal(String diretorio) {
	       return context.getRealPath("WEB-INF//" + diretorio + "//");
	}
	
	public Arquivos(ServletContext context, UsuarioSession usuarioSession) {
		this.context = context;
	    String caminho = getCaminhoReal("arquivos");
		pastaDeArquivos = new File(caminho);
		pastaDeArquivos.mkdirs();
		
		this.usuarioSession = usuarioSession;
	}

	public void salva(UploadedFile arquivo, Long idDaQuestao) {
		
		File pastaDaQuestao = getPastaDaQuestao(idDaQuestao);
		
		if (!pastaDaQuestao.mkdirs()) {
			for (File arq : pastaDaQuestao.listFiles()) {
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
		File arquivo = pasta.listFiles()[0];
		return new FileDownload(arquivo, "text/plain", "\"" + arquivo.getName() + "\"", true);
	}
}
