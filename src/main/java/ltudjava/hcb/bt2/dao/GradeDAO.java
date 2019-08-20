/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.dao;

import java.util.ArrayList;
import java.util.List;
import ltudjava.hcb.bt2.dto.Grade;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 
 */
public class GradeDAO {
        private  Session session=null;
        private Transaction tst=null;
        private List<Grade> list;
        
        @SuppressWarnings("CallToPrintStackTrace")
    public Integer insert(Grade p){
        Integer result=-1;
        
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst=session.beginTransaction();
            
            result=(Integer)session.save(p);
            
            tst.commit();
        } catch (HibernateException e) {
            if (tst!=null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    
        @SuppressWarnings("CallToPrintStackTrace")
    public boolean update(Grade p){
        Boolean result=false;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tst=session.beginTransaction();
            
            Grade q=(Grade) session.get(Grade.class, p.getId());
            
            q.setName(p.getName());
            
            session.update(q);
            tst.commit();
            
            result=true;
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        
        return result;
    }
    
        @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public boolean delete(int id){
        Boolean result = false;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Grade q = (Grade) session.get(Grade.class, id);
            session.delete(q);
            tst.commit();
            result = true;
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    
    private String getTable(String where){
        return "select distinct g "
                + "from Grade as g "
                + where
                + " order by g.name";
    }
    
        @SuppressWarnings("CallToPrintStackTrace")
    public List<Grade> getAll(){
        list=new ArrayList<>();
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tst=session.beginTransaction();
            
            Query q=session.createQuery(getTable(""));
            list = (List<Grade>)q.list();
            
            tst.commit();
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        
        return list;
    }
    
        @SuppressWarnings("CallToPrintStackTrace")
    public Grade getById(int id){
        Grade n = null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Query q = session.createQuery(getTable("where g.id = :id"));
            q.setParameter("id", id);
            n = (Grade) q.uniqueResult();
            tst.commit();
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return n;
    }
    
        @SuppressWarnings("CallToPrintStackTrace")
    public Grade getByName(String name){
        Grade n = null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Query q = session.createQuery(getTable("where g.name = :name"));
            q.setParameter("name", name);
            n = (Grade) q.uniqueResult();
            tst.commit();
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return n;
    }
}
