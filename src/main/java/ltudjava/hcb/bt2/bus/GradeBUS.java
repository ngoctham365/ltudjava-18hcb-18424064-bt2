/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.List;
import javax.swing.DefaultListModel;
import ltudjava.hcb.bt2.dao.GradeDAO;
import ltudjava.hcb.bt2.dto.*;

/**
 *
 * @author Jossion
 */
public class GradeBUS {

    public static Grade getGrade(String gradeId) {
        return new GradeDAO().getByName(gradeId);
    }

    public static int add(String trim) {
        return new GradeDAO().insert(new Grade(trim));
    }

    public static DefaultListModel getListName() {
        DefaultListModel listModel=new DefaultListModel();
        List<Grade> grades=new GradeDAO().getAll();
        for (Grade grade : grades) {
            listModel.addElement(grade.getName());
        }
        return listModel;
    }

    public static Grade getByName(String toString) {
        return new GradeDAO().getByName(toString);
    }

    public static boolean replateName(Grade g) {
        return new GradeDAO().update(g);
    }

    public static boolean remove(String gradeName) {
        return new GradeDAO().delete(getByName(gradeName).getId());
    }

}
