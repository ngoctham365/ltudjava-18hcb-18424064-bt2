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
public class RemarkingDAO {

    private Session session = null;
    private Transaction tst = null;
    private List<Remarking> list;

    public Integer insert(Remarking p) {
        Integer result = -1;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            result = (Integer) session.save(p);

            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(Remarking p) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Remarking q = (Remarking) session.get(Remarking.class, p.getScoreId());

            q.setReason(p.getReason());
            q.setScoreDesired(p.getScoreDesired());
            q.setScoreOld(p.getScoreOld());
            q.setScoreType(p.getScoreType());
            q.setStatus(p.getStatus());
            q.setScore(p.getScore());

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
            Remarking q = (Remarking) session.get(Remarking.class, id);
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
        return "select distinct r "
                + "from Remarking as r "
                + "left join fetch g.score s "
                + "left join fetch s.student sd "
                + "left join fetch s.timeTable tt "
                + "left join fetch tt.subject sj "
                + where;
    }

    public List<Remarking> getAll() {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();

            Query q = session.createQuery(getTable(""));
            list = (List<Remarking>) q.list();

            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }

        return list;
    }

    public Remarking getById(int id) {
        Remarking n = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Query q = session.createQuery(getTable("where r.scoreId = :id"));
            q.setParameter("id", id);
            n = (Remarking) q.uniqueResult();
            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return n;
    }

    public List<Remarking> getByScoreType(String scoreType) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Query q = session.createQuery(getTable("where r.scoreType = :name"));
            q.setParameter("name", scoreType);
            list = (List<Remarking>) q.list();
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
