package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.jdbc.JdbcUserMealRepositoryImpl;
import ru.javawebinar.topjava.repository.jdbc.JdbcUserRepositoryImpl;

import java.time.LocalDateTime;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");

        ConfigurableApplicationContext springContext =
                new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");

        UserRepository repository = springContext.getBean(JdbcUserRepositoryImpl.class);
        UserMealRepository mealRepository = springContext.getBean(JdbcUserMealRepositoryImpl.class);

//        System.out.println("-------test UserRepository----------");
//        System.out.println(repository.get(100001).getName());
//        System.out.println(repository.getByEmail("user@gmail.com").getName());
//        System.out.println(repository.getAll().size());
//        System.out.println(repository.get(100000).getName());
//        System.out.println("------------end of test------------");

        System.out.println("-------test UserMealRepository----------");
        System.out.println(mealRepository.getAll(100000));
//        System.out.println(mealRepository.get(1002, 100002));
//        System.out.println(mealRepository.getBetween(LocalDateTime.now().minusYears(1), LocalDateTime.now(), 100002));
//        System.out.println(mealRepository.save(new UserMeal(LocalDateTime.now(),"test food", 2030), 100002).getDescription() + " was added to db");
        System.out.println("------------end of test------------");
    }
}
