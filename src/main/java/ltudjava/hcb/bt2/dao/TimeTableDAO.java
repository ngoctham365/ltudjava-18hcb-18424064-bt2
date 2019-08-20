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
public class TimeTableDAO {

    private Session session = null;
    private Transaction tst = null;
    private List<TimeTable> list;

    @SuppressWarnings("CallToPrintStackTrace")
    public TimeTableId insert(TimeTable p) {
        TimeTableId result = null;

        try {
            if (session == null) {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
            }
            tst = session.beginTransaction();

            result = (TimeTableId) session.save(p);

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
    public boolean update(TimeTable p) {
        Boolean result = false;
        try {
            if (session == null) {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
            }
            tst = session.beginTransaction();

            TimeTable q = (TimeTable) session.get(TimeTable.class, p.getId());

            q.setRoom(p.getRoom());

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
    public boolean delete(TimeTableId id) {
        Boolean result = false;
        try {
            if (session == null) {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
            }
            tst = session.beginTransaction();
            TimeTable q = (TimeTable) session.get(TimeTable.class, id);
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
        return "select distinct tt "
                + "from TimeTable as tt "
                + "left join fetch tt.id i  "
                + where;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<TimeTable> getAll() {
        list = new ArrayList<>();
        try {
            if (session == null) {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
            }
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable(""));
            list = (List<TimeTable>) q.list();

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
    public TimeTable getByGradeAndSubject(Integer gradeId, String subjectCode) {
        list = new ArrayList<>();
        try {
            if (session == null) {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
            }
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where i.grade = :gra "
                    + "and tt.id.subjectCode = :sub "));
            q.setParameter("gra", gradeId);
            q.setParameter("sub", subjectCode);
            list = (List<TimeTable>) q.list();

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
    public List<TimeTable> getByGrade(Integer gradeId) {
        list = new ArrayList<>();
        try {
            if (session == null) {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
            }
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where i.grade = :gra"));
            q.setParameter("gra", gradeId);
            list = (List<TimeTable>) q.list();

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
    public TimeTable getByScoreId(String subjectCode) {
        list = new ArrayList<>();
        try {
            if (session == null) {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
            }
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable("where i.subjectCode = :sub"));
            q.setParameter("sub", subjectCode);
            list = (List<TimeTable>) q.list();

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
