/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.dao;

import java.util.ArrayList;
import java.util.List;
import ltudjava.hcb.bt2.dto.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author
 */
public class SubjectDAO {

    private Session session = null;
    private Transaction tst = null;
    private List<Subject> list;

    @SuppressWarnings("CallToPrintStackTrace")
    public String insert(Subject p) {
        @SuppressWarnings("UnusedAssignment")
        String result = "";

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            result = (String) session.save(p);

            tst.commit();
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            result = "";
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean update(Subject p) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Subject q = (Subject) session.get(Subject.class, p.getCode());

            q.setName(p.getName());

            session.update(q);
            tst.commit();

            result = true;
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean delete(String code) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Subject q = (Subject) session.get(Subject.class, code);
            session.delete(q);
            tst.commit();
            result = true;
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    private String getTable(String where) {
        return "select distinct s "
                + "from Subject as s "
                + where;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Subject> getAll() {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable(""));
            list = (List<Subject>) q.list();

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
    public Subject getById(String subjectId) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where s.code = :key"));
            q.setParameter("key", subjectId);
            list = (List<Subject>) q.list();

            tst.commit();
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return list.isEmpty() ? null : list.get(0);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Subject> getbyName(String subjectName) {
        
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where s.name = :key"));
            q.setParameter("key", subjectName);
            list = (List<Subject>) q.list();

            tst.commit();
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return list;
    }
}
