/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.dao;

import br.pdv.model.Log;
import br.pdv.util.JPAUtil;
import br.pdv.util.pdvException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author jsser
 */
public class LogDao implements Serializable{
    
    EntityManager em = JPAUtil.getEntityManager();
    
    public void salvar(Log logpc) throws pdvException {
        try {
            em.getTransaction().begin();
            em.persist(logpc);
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao salvar Log" + he.getMessage());
        }
    }
    
    public void editar(Log logpc) throws pdvException {
        try {
            em.getTransaction().begin();
            em.merge(logpc);
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao editar o log " + he.getMessage());
        }
    }
    
     public void excluir(Log logpc) throws pdvException {
        try {
            em.getTransaction().begin();
            em.remove(logpc);
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao excluir log " + he.getMessage());
        }
    }

    public List<Log> listaLogs(Integer id) throws pdvException {
        List<Log> lista = null;
        Query q = null;
        em.getTransaction().begin();
        try {
            q = em.createQuery("select l from Log l where l.pc.id = :id");
            q.setParameter("id", id);
            lista = q.getResultList();
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.isOpen() && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao Listar Logs: " + he.getMessage());
        }
        return lista;
    }
    
    public List<Log> listaLog() throws pdvException {
        List<Log> lista = null;
        Query q = null;
        em.getTransaction().begin();
        try {
            q = em.createQuery("from Log");
            lista = q.getResultList();
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.isOpen() && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao Listar Logs: " + he.getMessage());
        }
        return lista;
    }
    
    
}
