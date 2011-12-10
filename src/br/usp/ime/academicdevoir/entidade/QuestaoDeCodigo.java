package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.usp.ime.academicdevoir.infra.Constantes;
import br.usp.ime.academicdevoir.infra.TestadorDeCodigoJava;
import br.usp.ime.academicdevoir.infra.TipoDeQuestao;

@Entity
public class QuestaoDeCodigo extends Questao {

    /**
     * @uml.property name="resposta"
     */
    @Column(length = Constantes.MAX_TAM_CAMPO)
    private String codigoDeTeste;
    private String linguagem;

    public QuestaoDeCodigo() {
   
        linguagem = "java";
    }
    
    /**
     * @return
     * @uml.property name="codigoDeTeste"
     */
    public String getCodigoDeTeste() {
        return codigoDeTeste;
    }
    
    /**
     * @param codigoDeTeste
     * @uml.property name="codigoDeTeste"
     */
    public void setCodigoDeTeste(String codigoDeTeste) {
        this.codigoDeTeste = codigoDeTeste;
    }

    /**
     * @return
     * @uml.property name="codigoDeTeste"
     */
    public String getLinguagem() {
        return linguagem;
    }

    /**
     * @param codigoDeTeste
     * @uml.property name="codigoDeTeste"
     */
    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }
   
    public TipoDeQuestao getTipo() {
        return TipoDeQuestao.CODIGO;
    }

    public String getRenderizacao() {
        String htmlResult = "";
        StringBuffer buffer = new StringBuffer();

        buffer.append("<div><textarea id=\"resposta");
        buffer.append(this.getId());
        buffer.append("\" name=\"resposta.valor\"");
        buffer.append(" rows=\"10\" cols=\"50\"></textarea>");
        buffer.append("</div>");
        buffer.append("<input type=\"hidden\" name=\"idDaQuestao\" value=\"");
        buffer.append(this.getId());
        buffer.append("\" />");

        htmlResult = buffer.toString();

        return htmlResult;
    }

    public String getRenderAlteracao(Resposta resposta) {
        if (resposta == null) return getRenderizacao();
        
        String htmlResult = "";
        StringBuffer buffer = new StringBuffer();

        buffer.append("<div><textarea id=\"resposta");
        buffer.append(this.getId());
        buffer.append("\" name=\"resposta.valor\"");
        buffer.append(" rows=\"10\" cols=\"50\">");
        buffer.append(resposta.getValor());
        buffer.append("</textarea>");
        buffer.append("</div>");
        buffer.append("<input type=\"hidden\" name=\"idDaQuestao\" value=\"");
        buffer.append(this.getId());
        buffer.append("\" />");

        htmlResult = buffer.toString();

        return htmlResult;
    }
    
    
    public Boolean respostaDoAlunoEhCorreta(Resposta respostaAluno)  {
       String resultado;
       String caminho = respostaAluno.getCaminhoParaDiretorioDeTeste();
       if (linguagem.equals("java")) {
            TestadorDeCodigoJava testador = new TestadorDeCodigoJava(
                    caminho);
            try {
                resultado = 
                    testador.testaCodigoJava(respostaAluno.getValor(), 
                            codigoDeTeste);
                respostaAluno.setComentario(resultado);
                System.out.println(resultado);
                return resultado.startsWith("Executou corretamente. Parabens!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
