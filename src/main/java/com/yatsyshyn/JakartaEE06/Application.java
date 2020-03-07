package com.yatsyshyn.JakartaEE06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        UserService userService = applicationContext.getBean(UserService.class);

        userService.createUser("Illia", "Yatsyshyn", "illia.yatsyshyn@ukma.edu.ua");
        userService.createUser("Serhiy", "Fedusov", "serhiy.sedusov@ukma.edu.ua");
        userService.createUser("Timur", "Diduk", "timur.diduk@ukma.edu.ua");
        userService.createUser("Polina", "Shlepakova", "polina.shlepakova@ukma.edu.ua");
        userService.createUser("Alena", "Diduk", "alena.diduk@ukma.edu.ua");

        System.out.println("\n======================= Get all users =======================");
        List<UserEntity> allUsers = userService.getAll();
        for (UserEntity user : allUsers) System.out.println(user.greeting());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("\n========== Get all users whose last name is 'Diduk' ========");
        List<UserEntity> byLastName = userService.getByLastName("Diduk");
        for (UserEntity user : byLastName) System.out.println(user.greeting());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("\n========== Get all users whose name contains \"du\" ==========");
        List<UserEntity> foundByWord = userService.getBySubstring("du");
        for (UserEntity user : foundByWord) System.out.println(user.greeting());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}