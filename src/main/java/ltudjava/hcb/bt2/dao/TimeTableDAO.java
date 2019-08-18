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
public class TimeTableDAO {

    private Session session = null;
    private Transaction tst = null;
    private List<TimeTable> list;
    
    public TimeTableId insert(TimeTable p) {
        TimeTableId result = null;
        
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            
            result = (TimeTableId) session.save(p);
            
            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean update(TimeTable p) {
        Boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            
            TimeTable q = (TimeTable) session.get(TimeTable.class, p.getId());
            
            q.setRoom(p.getRoom());
            
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
            TimeTable q = (TimeTable) session.get(TimeTable.class, id);
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
        return "select distinct tt "
                + "from TimeTable as tt "
                + where;
    }
    
    public List<TimeTable> getAll() {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            
            Query q = session.createQuery(getTable(""));
            list = (List<TimeTable>) q.list();
            
            tst.commit();
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        
        return list;
    }
    
    public TimeTable getByGradeAndSubject(Integer gradeId, String subjectCode) {
        list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            
            Query q = session.createQuery(getTable("where tt.id.grade = :gra"
                    + "and tt.id.subjectCode = :sub "));
            q.setParameter("gra", gradeId);
            q.setParameter("sub", subjectCode);
            list = (List<TimeTable>) q.list();
            
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
