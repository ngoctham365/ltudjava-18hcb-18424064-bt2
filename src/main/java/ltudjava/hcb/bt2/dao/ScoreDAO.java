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
public class ScoreDAO {

    private Session session = null;
    private Transaction tst = null;
    private List<Score> list;

    @SuppressWarnings("CallToPrintStackTrace")
    public Integer insert(Score p) {
        Integer result = -1;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            result = (Integer) session.save(p);

            tst.commit();
        } catch (HibernateException e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean update(Score p) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Score q = (Score) session.get(Score.class, p.getId());

            q.setGradeId(p.getGradeId());
            q.setScodeHaft(p.getScodeHaft());
            q.setScoreAnother(p.getScoreAnother());
            q.setScoreFull(p.getScoreFull());
            q.setScoreSummary(p.getScoreSummary());
            q.setStudentId(p.getStudentId());
            q.setSubjectId(p.getSubjectId());

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
    public boolean delete(int id) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Score q = (Score) session.get(Score.class, id);
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
                + "from Score as s "
                + where;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Score> getAll() {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable(""));
            list = (List<Score>) q.list();

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
    public Score getById(int scoreId) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where s.id = :key"));
            q.setParameter("key", scoreId);
            list = (List<Score>) q.list();

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
    public List<Score> getByStudent(String studentCode) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where s.studentId = :key"));
            q.setParameter("key", studentCode);
            list = (List<Score>) q.list();

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
    public Score getByGradeSubjectStudent(Integer gradeId, String subjectCode, String studentCode) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where s.studentId = :stu "
                    + "and s.gradeId = :gra "
                    + "and s.subjectId = :sub"));
            q.setParameter("stu", studentCode);
            q.setParameter("gra", gradeId);
            q.setParameter("sub", subjectCode);
            list = (List<Score>) q.list();

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
    public List<Score> getByGradeSubject(Integer gradeId, String subjectCode) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where s.gradeId = :gra "
                    + "and s.subjectId = :sub "));
            q.setParameter("gra", gradeId);
            q.setParameter("sub", subjectCode);
            list = (List<Score>) q.list();

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
    public Score getByStudentSubject(String studentCode, String subjectCode) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where s.studentId = :stu "
                    + "and s.subjectId = :sub"));
            q.setParameter("stu", studentCode);
            q.setParameter("sub", subjectCode);
            list = (List<Score>) q.list();

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
