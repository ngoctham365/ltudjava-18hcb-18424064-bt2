/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.dao;

import java.util.ArrayList;
import java.util.List;
import ltudjava.hcb.bt2.dto.*;
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

    public String insert(User p) {
        String result = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            result = (String) session.save(p);

            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(User p) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            User q = (User) session.get(User.class, p.getName());

            q.setNameshow(p.getNameshow());
            q.setPass(p.getPass());
            q.setRole(p.getRole());
            q.setStudent(p.getStudent());

            session.update(q);
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

    public boolean delete(String name) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            User q = (User) session.get(User.class, name);
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

    private String getTable(String where) {
        return "select distinct u "
                + "from User as u "
                + "left join fetch u.Student sd "
                + where;
    }

    public List<User> getAll() {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable(""));
            list = (List<User>) q.list();

            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return list;
    }

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
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return list.size() == 0 ? null : list.get(0);
    }
}
