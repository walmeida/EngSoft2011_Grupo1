package br.usp.ime.academicdevoir.dao;

import java.util.List;

import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.usp.ime.academicdevoir.entidade.QuestaoDeCodigo;
import br.com.caelum.vraptor.ioc.Component;

@Component
@PrimaryKeyJoinColumn(name="id")
public class QuestaoDeCodigoDao {
    /**
     * @uml.property  name="session"
     * @uml.associationEnd  multiplicity="(0 -1)" elementType="br.usp.ime.academicdevoir.entidade.QuestaoDeTexto"
     */
    private final Session session;

    public QuestaoDeCodigoDao(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    /**
     * Devolve uma lista com todas as questões de texto cadastradas no banco de dados.
     * 
     * @return List<QuestaoDeTexto>
     */
    public List<QuestaoDeCodigo> listaTudo() {
        return this.session.createCriteria(QuestaoDeCodigo.class).list();
    }

    /**
     * Cadastra a questão fornecida no banco de dados.
     * 
     * @param questao
     */
    public void salva(QuestaoDeCodigo questao) {
        Transaction tx = session.beginTransaction();
        session.save(questao);
        tx.commit();
    }

    /**
     * Devolve uma questão de texto com o id fornecido.
     * 
     * @param id
     * @return QuestaoDeTexto
     */
    public QuestaoDeCodigo carrega(Long id) {
        return (QuestaoDeCodigo) this.session.load(QuestaoDeCodigo.class, id);
    }

    /**
     * Atualiza a questão fornecida no banco de dados. 
     * 
     * @param questao
     */
    public void atualiza(QuestaoDeCodigo questao) {
        Transaction tx = session.beginTransaction();
        this.session.update(questao);
        tx.commit();
    }

    /**
     * Remove a questão fornecida do banco de dados.
     * 
     * @param questao
     */
    public void remove(QuestaoDeCodigo questao) {
        Transaction tx = session.beginTransaction();
        this.session.delete(questao);
        tx.commit();
    }
    
    /**
     * Devolve uma QuestaoDeTexto com o id fornecido, se existir. Caso contrário, retorna null. 
     * 
     * @param id
     * @return QuestaoDeTexto
     */
    public QuestaoDeCodigo buscaPorId(Long id) {
        return (QuestaoDeCodigo) session
                .createCriteria(QuestaoDeCodigo.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
    }

    public void recarrega(QuestaoDeCodigo questao) {
        session.refresh(questao);
    }
}
