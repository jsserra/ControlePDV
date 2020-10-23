/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.dao;

import br.pdv.model.Pdv;
import br.pdv.util.JPAUtil;
import br.pdv.util.pdvException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author julianos
 */
public class PdvDao implements Serializable{

    EntityManager em = JPAUtil.getEntityManager();

    public List<Pdv> listaPdv() throws pdvException {
        List<Pdv> lista = null;
        em.getTransaction().begin();
        try {
            Query q = em.createQuery("select p from Pdv p where p.filial = 0");
           // Query q = em.createQuery("from Pdv");
            lista = q.getResultList();
            em.getTransaction().commit();
        } catch (HibernateException h) {
            if (em.isOpen() && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao listar PDV " + h.getMessage());
        }
        return lista;
    }
    
    public List<Pdv> listaPdvFilial() throws pdvException {
        List<Pdv> lista = null;
        em.getTransaction().begin();
        try {
            Query q = em.createQuery("select p from Pdv p where p.filial = 1");
            lista = q.getResultList();
            em.getTransaction().commit();
        } catch (HibernateException h) {
            if (em.isOpen() && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao listar PDV " + h.getMessage());
        }
        return lista;
    }

    public Pdv buscarPorId(Integer id) throws pdvException {
        Pdv pdv = null;
        try {
            em.getTransaction().begin();
            pdv = em.find(Pdv.class, id);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return pdv;
    }

}
