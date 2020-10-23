/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jsser
 */
public class JPAUtil {
    
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManager getEntityManager() {
           if (emf == null) {
                emf = Persistence.createEntityManagerFactory("WebApplicationPU");
            }
            if (em == null) {
                em = emf.createEntityManager();
            }
            return em;
       
    }

    /*
    public static EntityManager getEntityManager() {
        return this.em;
    }

    
    public static EntityManager getEntityManager(){
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("WebApplicationPU");
        }
        if(em == null){
            em = emf.createEntityManager();
        }
        return em;
    }
    
    
    
    
    private static EntityManagerFactory emf = null;
    
    public static EntityManager getEntityManager() {
        try{
            emf = Persistence.createEntityManagerFactory("WebApplicationPU");
        }catch (Exception e){
            System.out.println("Erro de criação do JPA " + e);
        }
        return emf.createEntityManager();
    }
    
    public void closeEntityManager(EntityManager entityManager){
        try{
            entityManager.close();
        }catch(Exception e){
            System.out.println("Erro ao fechar seção JPA" + e);
        }
    }   */
    
    
}
