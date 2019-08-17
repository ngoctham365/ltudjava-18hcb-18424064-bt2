/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.List;
import ltudjava.hcb.bt2.dto.Subject;
import ltudjava.hcb.bt2.dao.*;

/**
 *
 * @author Jossion
 */
public class SubjectBUS {

    public static List<Subject> getListAccordingToStudent(String studentCode) {
        return new SubjectDAO().getByStudent(studentCode);
    }

    public static List<Subject> getAll() {
        return new SubjectDAO().getAll();
    }
    
}
