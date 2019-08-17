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
public class SubjectDAO {

    private Session session = null;
    private Transaction tst = null;
    private List<Subject> list;

    public String insert(Subject p) {
        String result = "";

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            result = (String) session.save(p);

            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            result = "";
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(Subject p) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Subject q = (Subject) session.get(Subject.class, p.getCode());

            q.setName(p.getName());
            q.setTimeTables(p.getTimeTables());

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

    public boolean delete(int id) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Subject q = (Subject) session.get(Subject.class, id);
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
        return "select distinct s "
                + "from Subject as s "
                + "left join fetch s.timeTables tt "
                + "left join fetch tt.scores sr "
                + "left join fetch sr.student sd "
                + where;
    }

    public List<Subject> getAll() {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable(""));
            list = (List<Subject>) q.list();

            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return list;
    }

    public List<Subject> getByStudent(String studentCode) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where sd.studentCode = :id "));
            q.setParameter("id", studentCode);
            list = (List<Subject>) q.list();

            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return list;
    }
}
