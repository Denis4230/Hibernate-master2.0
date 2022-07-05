package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("1", "1", (byte) 1);
        userService.saveUser("2", "2", (byte) 2);
        userService.saveUser("3", "3", (byte) 3);
        userService.saveUser("4", "4", (byte) 4);
        userService.getAllUsers().forEach(System.out::println);
        userService.removeUserById(13);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.getAllUsers().forEach(System.out::println);
        Util.closeSessionFacto();

    }
}
