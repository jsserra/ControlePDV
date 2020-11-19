/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pdv.dao;

import br.pdv.model.Usuario;
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
public class UsuarioDao implements Serializable {

    EntityManager em = JPAUtil.getEntityManager();

    @SuppressWarnings("unchecked")
    public List<Usuario> listaUsuarios() throws pdvException {
        //List<Usuario> lista = null;
        Query q = null;
        try {
            em.getTransaction().begin();
            q = em.createQuery("from Usuario");
            // lista = q.getResultList();
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erros ao listar Usuarios" + he.getMessage());
        }
        return q.getResultList();
    }

    public Usuario usuarioById(Integer id) throws pdvException {
        Usuario usuario = null;
        try {
            em.getTransaction().begin();
            usuario = em.find(Usuario.class, id);
            em.getTransaction().commit();
        } catch (HibernateException he) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new pdvException("Erros ao buscar Usuario pelo Id" + he.getMessage());
        }
        return usuario;
    }

}
