/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import ltudjava.hcb.bt2.dao.UserDAO;
import ltudjava.hcb.bt2.dto.User;

/**
 *
 * @author Jossion
 */
public class UserBUS {

    public static User getByStudentCode(String studentCode) {
        return new UserDAO().getByStudentCode(studentCode);
    }

    public static boolean update(User user) {
        return new UserDAO().update(user);
    }

    public static Object logIn(String name, String encryptPassword) {
        return new UserDAO().getByNameAndPassword(name, encryptPassword);
    }

    public User LogIn(String userName, String passWord) {
        return new UserDAO().getByNameAndPassword(userName,HelperBUS.encryptPassword(passWord));
    }
    
}
