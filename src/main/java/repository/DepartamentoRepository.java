package repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import entity.Departamento;

/**
 * Classe responsável por realizar operações no banco de dados referente a
 * entidade Departamento: - Inserir - Atualizar - Deletar - Consultar
 */
public class DepartamentoRepository {

    private Session session;

    public DepartamentoRepository() {
        this.session = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()
                .openSession();
    }

    /**
     * Método responsável por inserir um departamento no banco de dados.
     * 
     * @param departamento dados do departamento
     */
    public void inserir(Departamento departamento) {
        try {
            session.beginTransaction();
            session.persist(departamento); // Persiste nosso objeto (salva)
            session.getTransaction().commit();
            System.out.println("Departamento inserido com sucesso");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Ocorreu um problema ao inserir o departamento: " + e.getMessage());
        }
    }

    /**
     * Método para listar todos os departamentos do banco de dados.
     * 
     * @return lista de departamentos
     */
    public List<Departamento> listarTodos() {
        List<Departamento> departamentos = null;
        try {
            // Criando a consulta para listar todos os departamentos
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
            criteria.from(Departamento.class);
            departamentos = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            System.out.println("Ocorreu um problema ao listar todos os departamentos: " + e.getMessage());
        }
        return departamentos;
    }

    /**
     * Método responsável por atualizar os dados de um departamento no banco de dados.
     * 
     * @param departamento dados do departamento
     */
    public void atualizar(Departamento departamento) {
        try {
            session.beginTransaction();
            session.merge(departamento); // Atualiza o departamento
            session.getTransaction().commit();
            System.out.println("Departamento atualizado com sucesso");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Ocorreu um problema ao atualizar o departamento: " + e.getMessage());
        }
    }

    /**
     * Método responsável por remover um departamento do banco de dados com base no seu id.
     * 
     * @param id identificação do departamento
     */
    public void remover(long id) {
        try {
            session.beginTransaction();
            Departamento departamento = session.find(Departamento.class, id); // Recupera o departamento
            if (departamento != null) {
                session.remove(departamento); // Remove o departamento
                session.getTransaction().commit();
                System.out.println("Departamento removido com sucesso");
            } else {
                System.out.println("Departamento não encontrado para remoção");
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Ocorreu um problema ao remover o departamento: " + e.getMessage());
        }
    }

    /**
     * Método responsável por consultar todos os departamentos no banco de dados.
     * 
     * @return lista de departamentos
     */
    public List<Departamento> pesquisarTodos() {
        List<Departamento> registros = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
            criteria.from(Departamento.class);
            registros = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            System.out.println("Ocorreu um problema ao consultar todos os departamentos: " + e.getMessage());
        }
        return registros;
    }

    /**
     * Método responsável por consultar um departamento pelo seu ID.
     * 
     * @param id identificação do departamento
     * @return departamento encontrado
     */
    public Departamento pesquisaPeloId(long id) {
        Departamento departamento = null;
        try {
            departamento = session.find(Departamento.class, id); // Busca o departamento pelo ID
        } catch (Exception e) {
            System.out.println("Ocorreu um problema ao consultar o departamento pelo Id: " + e.getMessage());
        }
        return departamento;
    }
}
