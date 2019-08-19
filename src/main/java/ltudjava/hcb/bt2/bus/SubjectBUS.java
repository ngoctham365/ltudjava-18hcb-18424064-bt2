/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import ltudjava.hcb.bt2.dto.*;
import ltudjava.hcb.bt2.dao.*;

/**
 *
 * @author Jossion
 */
public class SubjectBUS {

    public static List<Subject> getListAccordingToStudent(String studentCode) {
        List<Subject> subjects = new ArrayList<>();
        List<Score> scores = new ScoreDAO().getByStudent(studentCode);
        for (Score score : scores) {
            subjects.add(new SubjectDAO().getById(score.getSubjectId()));
        }
        return subjects;
    }

    public static List<Subject> getAll() {
        return new SubjectDAO().getAll();
    }

    static String getNameById(String subjectId) {
        return getById(subjectId).getName();
    }

    static Subject getById(String subjectId) {
        return new SubjectDAO().getById(subjectId);
    }

    public static Subject getByCode(String subjectCode) {
        return new SubjectDAO().getById(subjectCode);
    }

    public static List<Subject> getByName(String subjectName) {
        return new SubjectDAO().getbyName(subjectName);
    }

    public static Object create(Subject subject) {
        return new SubjectDAO().insert(subject);
    }

    public static DefaultListModel getAllToGUI() {
        DefaultListModel listModel=new DefaultListModel();
        List<Subject> grades=getAll();
        for (Subject grade : grades) {
            listModel.addElement(HelperBUS.concatWithIconMoveRight(grade.getCode(), grade.getName()));
        }
        return listModel;
    }

    public static boolean update(String code, String name) {
        return new SubjectDAO().update(new Subject(code, name));
    }

    public static boolean delete(String code) {
        return new SubjectDAO().delete(code);
    }

}
