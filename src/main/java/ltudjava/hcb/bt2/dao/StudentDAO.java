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
public class StudentDAO {

    private Session session = null;
    private Transaction tst = null;
    private List<Student> list;

    public String insert(Student p) {
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

    public boolean update(Student p) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Student q = (Student) session.get(Student.class, p.getStudentCode());

            q.setFullname(p.getFullname());
            q.setPersonCode(p.getPersonCode());
            q.setSex(p.getSex());
            q.setGrade(p.getGrade());

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

    public boolean delete(String studentCode) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Student q = (Student) session.get(Student.class, studentCode);
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
                + "from Student as s "
                + where;
    }

    public List<Student> getAll() {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable(""));
            list = (List<Student>) q.list();

            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return list;
    }

    public Student getByCode(String studentCode) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where s.studentCode = :id"));
            q.setParameter("id", studentCode);
            list = (List<Student>) q.list();

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
