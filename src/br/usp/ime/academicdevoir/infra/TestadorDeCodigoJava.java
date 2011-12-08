package br.usp.ime.academicdevoir.infra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Entidade responsável por corrigir uma questão de código Java.
 * 
 * O código foi adaptado do projeto http://dl.dropbox.com/u/10977140/Aulas.rar
 */
public class TestadorDeCodigoJava {
    
    private String caminhoDoDiretorio; /* Endereço do caminhoDoDiretorio em que serao realizados
                                 os testes. */
    
    public TestadorDeCodigoJava(String diretorio) {
        this.caminhoDoDiretorio = diretorio + "/";
    }

    /**
     * Corrige questão de código java 
     * @param codigo String com o código a ser testado
     * @param codigoDeTeste String com o código do teste a ser realizado
     * @return String com o resultado da correção automática
     * @throws Exception exceção lançado quando o direório para a realização dos
     * testes não é encintrado
     */
	public synchronized String testaCodigoJava(String codigo, String codigoDeTeste) 
	throws Exception {
	    File diretorio;
	    String nomeDaClasse;
	    String resultadoDaCompilacao;
	    
	    // Verifica a existência do diretório em que os testes serao realizados
	    if (!(diretorio = new File(caminhoDoDiretorio)).mkdirs()) {
            for (File arq : diretorio.listFiles()) {
                arq.delete();
            }
        }

		nomeDaClasse = nomeDaPrimeiraClasseDefinidaNoCodigo(codigo);

		if (nomeDaClasse.equals("Nao ha classe definida.")) 
		    return "Nao ha classe definida.";
		
		// Compila código a ser testado.
		resultadoDaCompilacao = CompiladorJava.compila(caminhoDoDiretorio,      
		        geraArquivoComOCodigoJava(nomeDaClasse, codigo));
	    
		// Verifica se houve erro de compilação
		if (!resultadoDaCompilacao.equals("")) {
	        return "Falha ao compilar o codigo.\n"+resultadoDaCompilacao;
	    }
		
		// Gera o arquivo de teste
		//nomeDaClasse = "RealizaTesteNaClasseSubmetida";
        
		codigoDeTeste = codigoDeTeste(codigoDeTeste);
		
		if (codigoDeTeste.startsWith("Problema no codigo de teste."))
		    return codigoDeTeste;
		
		geraArquivoComOCodigoJava(nomeDaClasse, codigo + "\n\n" +codigoDeTeste);
		
		resultadoDaCompilacao = CompiladorJava.compila(caminhoDoDiretorio, nomeDaClasse + 
		                                                          ".java");
		resultadoDaCompilacao = 
		    verificaAusenciaDeClasseOuMetodoASerTestado(resultadoDaCompilacao);
		
		if (!resultadoDaCompilacao.equals(""))
		    return resultadoDaCompilacao;
		
		nomeDaClasse = "RealizaTesteNaClasseSubmetida";
		
		// Executa programa
		return ExecutorDeProgramas.executaProgramaJava(caminhoDoDiretorio, nomeDaClasse);
		
	}
	

	/**
	 * Corrige código de teste, no caso de haver chamadas a "assert" com menos
	 * argumentos.
	 * @param codigoDeTeste
	 * @return String com o código de teste corrigido
	 */
	private String trataCodigoDeTeste(String codigoDeTeste) {
	    codigoDeTeste = codigoDeTeste.replace("\r", "");
       
        // Acrescenta parametro String no AssertEquals, caso nao tenha.
        int pos = codigoDeTeste.indexOf("assert");
        
        while (pos != -1) {
            // Encontra o primeiro parametro
            while (codigoDeTeste.charAt(pos) != '(') pos++;
            pos++;
            int posFim = pos;
            int qtdeParentesesAberto = 0;
            while (qtdeParentesesAberto != 0 || 
                    codigoDeTeste.charAt(posFim) != ',') {
                if (codigoDeTeste.charAt(posFim) == '(') qtdeParentesesAberto++;
                if (codigoDeTeste.charAt(posFim) == ')') qtdeParentesesAberto--;
                if (qtdeParentesesAberto > 1000) 
                    return "Problema no codigo de teste." +
                           " Notifique o professor." + 
                           " Nao foi possovel identificar o primeiro parametro."
                           + " Excesso de paranteses abertos.";
                if (posFim == codigoDeTeste.length()-1) 
                    return "Problema no codigo de teste. " +
                           "Notifique o professor. " +
                           "Nao foi possivel identificar o primeiro parametro. "
                           + "Fim do arquivo encontrado.";
                posFim++;
            }
            
            String parametro = codigoDeTeste.substring(pos, posFim);
            // Encontra o segundo parametro 
            qtdeParentesesAberto = 0;
            posFim++;
            while (qtdeParentesesAberto != 0 || 
                    (codigoDeTeste.charAt(posFim) != ',' && 
                            codigoDeTeste.charAt(posFim) != ')')) {
                if (codigoDeTeste.charAt(posFim) == '(') qtdeParentesesAberto++;
                if (codigoDeTeste.charAt(posFim) == ')') qtdeParentesesAberto--;
                if (qtdeParentesesAberto > 1000) 
                    return "Problema no codigo de teste. " +
                           "Notifique o professor. " +
                           "Nao foi possivel identificar o ultimo parametro. " +
                           "Excesso de parenteses abertos.";
                if (posFim == codigoDeTeste.length()-1) 
                    return "Problema no codigo de teste. " + 
                           "Notifique o professor. " +
                           "Nao foi possivel identificar o ultimo parametro. " +
                           "Fim do arquivo encontrado."; 
                posFim++;
            }
            if (codigoDeTeste.charAt(posFim) == ')') {
                // Nao tem o ultimo parametro
                codigoDeTeste = codigoDeTeste.substring(0, posFim) + ",\"" + 
                parametro.replace("\"", "\\\"") + "\"" + 
                codigoDeTeste.substring(posFim);
            }
            pos = codigoDeTeste.indexOf("assert", pos); 
        }
        return codigoDeTeste;
	}

	/**
	 * Gera arquivo "nomeDaClasse.java" com o codigo fornecido.
	 * @param nomeDaClasse nome da classe gerada pelo codigo recebido
	 * @param codigo
	 * @return String com o nome do arquivo gerado.
	 * @throws Exception
	 */
	private synchronized String geraArquivoComOCodigoJava (String nomeDaClasse, 
	        String codigo)
    throws Exception {
	    String nomeDoArquivo = nomeDaClasse + ".java";
	    File arquivo = new File(caminhoDoDiretorio + nomeDoArquivo);
        if (arquivo.exists())
            arquivo.delete();
	    FileWriter arq = new FileWriter(arquivo);
	    arq.write(codigo); // grava no arquivo o codigo
	    arq.close();
	    return nomeDoArquivo;
	}
	
	/**
	 * Devolve o nome da primeira classe no codigo fornecido.
	 * @param codigo
	 * @return nome da classe
	 */
	private String nomeDaPrimeiraClasseDefinidaNoCodigo(String codigo) {
	    int pos = codigo.indexOf("class ");
        if (pos == -1) 
            return "Nao ha classe definida.";
        pos = pos + "class ".length();
        while (pos < codigo.length() && codigo.charAt(pos) == ' ') pos++;
        int posFim = pos;
        while (posFim < codigo.length() && 
              (Character.isLetterOrDigit(codigo.charAt(posFim)) || 
                      codigo.charAt(posFim) == '_'))
            posFim++;
        if (posFim == pos) 
            return "Nao ha classe definida.";
        
        // Nome da classe encontrada
        return codigo.substring(pos, posFim);
	}
	
	/**
	 * Devolve o código de teste completo. Cria-se uma classe para os testes e 
	 * acrescentam-se os métodos "assert".
	 * @param codigoDeTeste
	 * @return codigo de teste completo
	 * @throws Exception na abertura do arquivo com a definicao da classe e dos 
	 * métodos "assert" a serem acrescentado no código de teste.
	 */
	private String codigoDeTeste(String codigoDeTeste) throws Exception {

	    String saida = "";
	    String linha;
	    BufferedReader template;
	    
	    codigoDeTeste = trataCodigoDeTeste(codigoDeTeste);
        
        if (codigoDeTeste.startsWith("Problema no codigo de teste."))
            return codigoDeTeste;
	    
		try {
			template = new BufferedReader(new 
			        FileReader((new File(caminhoDoDiretorio)).getParentFile().
			                getParentFile().getParentFile().
			                getParent() + 
			                "/templates/TemplateDeTesteDeCodigoJava.java"));
		} catch (Exception ex) {
			throw new Exception 
			    ("Arquivo TemplateDeTesteDeCodigoJava.java nao encontrado");
		}
		
		while ((linha = template.readLine()) != null) {
			if (linha.indexOf("/* Insert code here */") != -1) {
				saida = saida + codigoDeTeste + "\n";
			} else {
				saida = saida + linha + "\n";
			}
		}
		return saida;
	}

	/**
	 * Verfica se está faltando algum método ou classe no código que está sendo
	 * testado.
	 * @param resultadoDaCompilacao
	 * @return mensagem indicando a ausência de alguma classe ou método, ou "",
	 * caso todos os métodos e classes a serem testados estejam no codigo sendo
	 * testado.
	 */
	private String verificaAusenciaDeClasseOuMetodoASerTestado
	              (String resultadoDaCompilacao) {
	    if (!resultadoDaCompilacao.equals("")) {
            if (resultadoDaCompilacao.indexOf("cannot find symbol") != -1) {
                if (resultadoDaCompilacao.indexOf("symbol  : class ") != -1) {
                    String nome = extraiPalavraSeguinte(resultadoDaCompilacao, 
                                                        "symbol  : class");
                    return "Nao foi encontrada a classe " + nome + ".";
                } 
                else if (resultadoDaCompilacao.indexOf("symbol  : method") 
                        != -1) {
                    String nomeMetodo = extraiPalavraSeguinte(
                            resultadoDaCompilacao, "symbol  : method");
                    String nomeLocal = extraiPalavraSeguinte(
                            resultadoDaCompilacao, "location: class ");
                    return "Nao foi encontrado o metodo "+ nomeMetodo + 
                           " na classe " + nomeLocal + ".";
                }
            } 
            else if (resultadoDaCompilacao.indexOf("cannot be applied to ") != 
                -1) {
                String mensagem = extraiPalavraSeguinte(resultadoDaCompilacao,
                                                        ": ");
                return "Problema com parametros: the method " + mensagem;
            } else if (resultadoDaCompilacao.indexOf("incompatible types") != 
                -1) {
                String encontrado = extraiPalavraSeguinte(resultadoDaCompilacao, 
                                                     "found :");
                String requerido = extraiPalavraSeguinte(resultadoDaCompilacao, 
                                                        "required: ");
                return "Tipos de dados incompativeis. Foi encontrado " + 
                       encontrado + " e era requerido " + requerido + ".";
            }
            return "Falha ao compilar o codigo de teste.\n"+resultadoDaCompilacao;
        }
	    return "";
	}

	/**
	 * TODO Colocar esse mértodo em outro lugar.
	 * Extrai a palavra seguinte à String dada por inicio no texto.
	 * @param texto
	 * @param inicio
	 * @return palavra seguinte à inicio no texto
	 */
	private String extraiPalavraSeguinte(String texto, String inicio) {
	    int tam = inicio.length();
	    if (texto.indexOf(inicio) == -1) return null;
	    int posIni = texto.indexOf(inicio) + tam;
	    int posFim = texto.indexOf('\n', posIni);
        return texto.substring(posIni, posFim);
    }
}