package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;

import java.sql.SQLException;

public class UserService {
    public static Integer saveUser(User user){
        try {
            if (UserDAO.isExist(user.getEmail())){
                return 0;
            }else {
               System.out.println("this is value of user+++++++++++++++++ "+ user);
               return UserDAO.saveUser(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
