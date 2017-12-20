/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.dao;

import com.llince.model.rest.entity.Companies;
import com.llince.model.rest.entity.Usuario;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author loren
 */
@Repository
public class UserDaoImpl implements UserDaoI {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    private Session getSession() {
        if (session == null) {
            try {
                session = sessionFactory.openSession();
                System.out.println("############# Initiaal openSession ####################");
            } catch (HibernateException e) {
                System.out.println(e.getMessage());

            }
        }else
         System.out.println("#############  Session not null ####################");
        return session;
    }

    @Override
    @Transactional
    public long save(Usuario user) {
        getSession().save(user);
        getSession().beginTransaction().commit();
        return user.getId();
    }

    @Override
    public Usuario get(long id) {
        return getSession().get(Usuario.class, id);
    }

    @Override
    public Usuario get(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> userRoot = query.from(Usuario.class);
        Root<Companies> comtRoot = query.from(Companies.class);
        query.multiselect(userRoot, comtRoot);
        query.select(userRoot).where(builder.equal(userRoot.get("loginUser"), name), builder.equal(userRoot.get("companies"), comtRoot.get("id")));
        Query<Usuario> q = getSession().createQuery(query);
        return q.getSingleResult();
    }

    @Override
    public List<Usuario> list() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> root = cq.from(Usuario.class);
        cq.select(root);
        Query<Usuario> query = getSession().createQuery(cq);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Usuario user) {
        getSession().merge(user);
        getSession().beginTransaction().commit();
    }

    @Override
    public void delete(long id) {
        Usuario user = session.byId(Usuario.class).load(id);
        getSession().delete(user);
        getSession().beginTransaction().commit();
    }

}
