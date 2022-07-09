package org.example;

import org.example.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    static String sq = "";

    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        sq = communication.showAllUsers();
        String s = "";
        s = s + communication.saveUser(sq);
//        s = s + communication.changeUser(sq);
        System.out.println(s);
    }
}
