/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.dao;

import br.pdv.model.Computer;
import br.pdv.util.JPAUtil;
import br.pdv.util.pdvException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author jsser
 */
public class ComputerDao implements Serializable {

    EntityManager em = JPAUtil.getEntityManager();

    public void salvar(Computer pc) throws pdvException {
        try {
            em.getTransaction().begin();
            em.persist(pc);
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao salvar computador" + he.getMessage());
        }
    }

    public void editar(Computer pc) throws pdvException {
        try {
            em.getTransaction().begin();
            em.merge(pc);
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao editar computador " + pc + " :" + he.getMessage());
        }
    }

    public void excluir(Computer pc) throws pdvException {
        try {
            em.getTransaction().begin();
            em.remove(pc);
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao excluir computador " + pc + " :" + he.getMessage());
        }
    }

    public void desativarPc(Computer pc) throws pdvException {
        pc.setPdv(null);
        pc.setAtivo(Boolean.FALSE);
        try {
            em.getTransaction().begin();
            em.merge(pc);
            em.getTransaction().commit();
        }catch(HibernateException he){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new pdvException("Falha ao desativar pc " + he.getMessage()) ;
        }
    }

    public Computer buscaPcId(Integer idpc) throws pdvException {
        Computer pc = null;
        Query q = null;
        try {
            em.getTransaction().begin();
            q = em.createQuery("select c from Computer c where c.id = :id");
            q.setParameter("id", idpc);
            pc = (Computer) q.getResultList().get(0);
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao busca Computador pelo Id " + he.getMessage());
        }
        return pc;
    }

    public List<Computer> listaPC() throws pdvException {
        List<Computer> lista = null;
        Query q = null;
        try {
            em.getTransaction().begin();
            q = em.createQuery("select c from Computer c where c.ativo = 0");
            lista = q.getResultList();
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.isOpen() && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao Listar os PCs dos Caixas " + he.getMessage());
        }

        return lista;
    }

    public List<Computer> listaPcMatriz() throws pdvException {
        List<Computer> lista = null;
        Query q = null;
        try {
            em.getTransaction().begin();
            q = em.createQuery("select c from Computer c where c.ativo = 1 and c.pdv.filial = 0 order by c.pdv.nome, c.nome");
            lista = q.getResultList();
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.isOpen() && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao Listar os PCs dos Caixas " + he.getMessage());
        }

        return lista;
    }

    public List<Computer> listaPcFilial() throws pdvException {
        List<Computer> lista = null;
        try {
            em.getTransaction().begin();
            lista = em.createQuery("select c from Computer c where c.pdv.filial = 1 and c.ativo = 1 order by c.pdv.nome, c.nome").getResultList();
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.isOpen() && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao listar Caixas da Filial " + he.getMessage());
        }
        return lista;
    }

}
