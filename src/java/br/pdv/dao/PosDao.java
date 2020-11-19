/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.dao;

import br.pdv.model.Pos;
import br.pdv.model.PosTeste;
import br.pdv.util.JPAUtil;
import br.pdv.util.pdvException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.HibernateException;

/**
 *
 * @author jsser
 */
public class PosDao implements Serializable{
    
    EntityManager em = JPAUtil.getEntityManager();
    
    public void salvar(Pos pos) throws pdvException{
        try{
            em.getTransaction().begin();
            em.persist(pos);
            em.getTransaction().commit();                    
        }catch(HibernateException he){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
           throw new pdvException("Erro ao gravar POS: " + he.getMessage());
        }    
    }
    
        public void salvarTeste(PosTeste posTeste) throws pdvException{
        try{
            em.getTransaction().begin();
            em.persist(posTeste);
            em.getTransaction().commit();                    
        }catch(HibernateException he){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
           throw new pdvException("Erro ao gravar POS: " + he.getMessage());
        }    
    }
    
    public void editar(Pos pos) throws pdvException{
        try{
            em.getTransaction().begin();
            em.merge(pos);
            em.getTransaction().commit();                    
        }catch(HibernateException he){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
           throw new pdvException("Erro ao editar POS: " + he.getMessage());
        }    
    }
    
    public void excluir(Pos pos) throws pdvException{
        try{
            em.getTransaction().begin();
            em.remove(pos);
            em.getTransaction().commit();                    
        }catch(HibernateException he){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
           throw new pdvException("Erro ao excluir POS: " + he.getMessage());
        }    
    }
    
    public List<Pos> listaPosMatriz() throws pdvException{
        List<Pos> poss = null;
        try{
            em.getTransaction().begin();
            poss = em.createQuery("select p from Pos p where p.filial = 0 and p.ativo = 1 order by p.bandeira, p.chip").getResultList();
            em.getTransaction().commit();
        } catch(HibernateException he){
            if(em.isOpen() && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao listar POS: " + he.getMessage());
        }
        return poss;
    }
    
        public List<Pos> listaPosFilial() throws pdvException{
        List<Pos> poss = null;
        try{
            em.getTransaction().begin();
            poss = em.createQuery("select p from Pos p where p.filial = 1 and p.ativo = 1 order by p.bandeira, p.chip").getResultList();
            em.getTransaction().commit();
        } catch(HibernateException he){
            if(em.isOpen() && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new pdvException("Erro ao listar POS: " + he.getMessage());
        }
        return poss;
    }
    
        public void dasativar(Pos pos) throws pdvException{
        pos.setAtivo(Boolean.FALSE);
        try{
            em.getTransaction().begin();
            em.merge(pos);
            em.getTransaction().commit();                    
        }catch(HibernateException he){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
           throw new pdvException("Falha ao desativar POS: " + he.getMessage());
        }    
    }
}
