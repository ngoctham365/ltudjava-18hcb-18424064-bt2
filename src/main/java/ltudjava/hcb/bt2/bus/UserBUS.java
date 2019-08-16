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

    public User LogIn(String userName, String passWord) {
        return new UserDAO().getByNameAndPassword(userName,passWord);
    }
    
}
