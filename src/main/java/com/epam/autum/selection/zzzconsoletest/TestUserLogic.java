package com.epam.autum.selection.zzzconsoletest;

import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.UserLogic;

/**
 * Created by Tapac on 08.01.2017.
 */
public class TestUserLogic {
    public static void main(String[] args) {

        try {
            User user = UserLogic.checkEmail("yaremenko.taras@gmail.com","1111");
            System.out.println(user);
        } catch (LogicException e) {
            System.err.println(e);
        }


    }
}
