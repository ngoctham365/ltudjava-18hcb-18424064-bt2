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
public class UserDAO {

    private Session session = null;
    private Transaction tst = null;
    private List<User> list;

    @SuppressWarnings("CallToPrintStackTrace")
    public String insert(User p) {
        @SuppressWarnings("UnusedAssignment")
        String result = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            result = (String) session.save(p);

            tst.commit();
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean update(User p) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            User q = (User) session.get(User.class, p.getName());

            q.setNameshow(p.getNameshow());
            q.setPass(p.getPass());
            q.setRole(p.getRole());

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
    public boolean delete(String name) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            User q = (User) session.get(User.class, name);
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
        return "select distinct u "
                + "from User as u "
                + where;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<User> getAll() {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable(""));
            list = (List<User>) q.list();

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
    public User getByNameAndPassword(String userName, String passWord) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where u.name like :name "
                    + "and u.pass like :pass "));
            q.setParameter("name", userName);
            q.setParameter("pass", passWord);

            list = (List<User>) q.list();

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
    public User getByStudentCode(String studentCode) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where u.name like :name "));
            q.setParameter("name", studentCode);

            list = (List<User>) q.list();

            tst.commit();
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return list.isEmpty() ? null : list.get(0);
    }
}
