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
public class ScoreDAO {
    
        private  Session session=null;
        private Transaction tst=null;
        private List<Score> list;
        
    public Integer insert(Score p){
        Integer result=-1;
        
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tst=session.beginTransaction();
            
            result=(Integer)session.save(p);
            
            tst.commit();
        } catch (Exception e) {
            if (tst!=null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean update(Score p){
        Boolean result=false;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tst=session.beginTransaction();
            
            Score q=(Score) session.get(Score.class, p.getId());
            
            q.setScodeHaft(p.getScodeHaft());
            q.setScoreAnother(p.getScoreAnother());
            q.setScoreFull(p.getScoreFull());
            q.setScoreSummary(p.getScoreSummary());
            q.setRemarking(p.getRemarking());
            q.setStudent(p.getStudent());
            q.setTimeTable(p.getTimeTable());
            
            session.update(q);
            tst.commit();
            
            result=true;
        } catch (Exception e) {
            if (tst != null) {
                tst.rollback();
            }
            e.printStackTrace();
        }
        
        return result;
    }
    
    public boolean delete(int id){
        Boolean result = false;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tst = session.beginTransaction();
            Score q = (Score) session.get(Score.class, id);
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
        return "select distinct s "
                + "from Score as s "
                + "left join fetch s.student sd "
                + "left join fetch s.timeTable t "
                + "left join fetch s.remarking r "
                + where;
    }
    
    public List<Score> getAll(){
        list=new ArrayList<>();
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tst=session.beginTransaction();
            
            Query q=session.createQuery(getTable(""));
            list = (List<Score>)q.list();
            
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