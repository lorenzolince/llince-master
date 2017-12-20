/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.dao;

import com.llince.model.rest.entity.Customer;
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

/**
 *
 * @author Llince
 */
@Repository
public class CostumerDaoImpl implements CostumerDaoI {
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
    public long save(Customer customer) {
        getSession().save(customer);
        getSession().beginTransaction().commit();
        return customer.getCustomerId();
    }

    @Override
    public Customer get(long id) {
    return getSession().get(Customer.class, id);
    }

    @Override
    public List<Customer> list() {
        Query<Customer> query=null;
        try {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.select(root); 
         query = getSession().createQuery(cq);
        } catch (Exception e) {
            System.out.println("list: "+e.getMessage());
        }
        return query.getResultList();
    }

    @Override
    public void update(Customer customer) {
        try {
         getSession().merge(customer);
        getSession().beginTransaction().commit();  
         getSession().clear();
        } catch (Exception e) {
           System.out.println("update:"+e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        Customer customer = session.byId(Customer.class).load(id);
        getSession().delete(customer);
        getSession().beginTransaction().commit();
        getSession().clear();
        
    }
    
}
