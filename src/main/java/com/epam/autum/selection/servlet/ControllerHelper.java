package com.epam.autum.selection.servlet;


import com.epam.autum.selection.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Тарас on 08.01.2017.
 */

public class ControllerHelper {
    private static ControllerHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("login", new CommandLogin());
        commands.put("signin", new CommandEmpty());
        commands.put("register", new CommandRegister());
        commands.put("registration", new CommandRegistration());
    }


    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new CommandEmpty();
        }
        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }
}