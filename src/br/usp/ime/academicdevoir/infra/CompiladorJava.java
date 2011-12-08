package br.usp.ime.academicdevoir.infra;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import com.sun.tools.javac.Main;

/**
 * Entidade responsável por compilar um código Java.
 * 
 * O código foi adaptado do projeto http://dl.dropbox.com/u/10977140/Aulas.rar
 */
public class CompiladorJava {

    /**
     * Compila código Java no arquivo dado de nome "nomeDoArquivo" no diretorio
     * de endereco dado por "diretorio" 
     * @param diretorio ambiente da compilação
     * @param nomeDoArquivo nomeDoArquivo com o código Java a ser compilado
     * @return mensagem indicando se houve erro de compilação.
     * @throws Exception
     */
	public synchronized static String compila(String diretorio, 
	        String nomeDoArquivo) throws Exception {

		File arquivo = new File(diretorio + "logCompilacao.txt");
		if (arquivo.exists()) arquivo.delete();

		if (!(new File(diretorio + nomeDoArquivo).exists())) 
		    throw new Exception("Arquivo " + nomeDoArquivo + 
		                        " nao encontrado.");
				
		/* deixa um log da compilacao num arquivo chamado logCompilacao.txt */
		PrintWriter saida = new PrintWriter(new FileWriter(diretorio + 
		                                                  "logCompilacao.txt"));
		StringBuffer txtResultados = new StringBuffer();
		
		Main.compile(new String[] {diretorio + nomeDoArquivo}, saida);
		saida.close();		
		
		/* le o arquivo de resultados e imprime na tela */
		BufferedReader result = new BufferedReader(new FileReader(diretorio + 
		                                                  "logCompilacao.txt"));
		String linha;
		while ((linha = result.readLine()) != null) {
			txtResultados.append(linha + "\n");
		}

		return txtResultados.toString();
	}
}
