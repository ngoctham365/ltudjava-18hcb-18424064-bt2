/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import ltudjava.hcb.bt2.dao.StudentDAO;

/**
 *
 * @author 
 */
public class StudentBUS {

    public static String getFullNameByCode(String studentCode) {
        return new StudentDAO().getByCode(studentCode).getFullname();
    }
    
}
