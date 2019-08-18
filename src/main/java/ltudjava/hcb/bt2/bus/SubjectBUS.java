/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.ArrayList;
import java.util.List;
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

}