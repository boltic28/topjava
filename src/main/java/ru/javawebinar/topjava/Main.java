package ru.javawebinar.topjava;

import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;

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

        // testing InMemoryUserRepImpl
        InMemoryUserRepositoryImpl repository = new InMemoryUserRepositoryImpl();
        System.out.println();
        System.out.println("test get by e-mail:  " + repository.getByEmail("bo28@mail.ru").getName());

        System.out.println("test get id:  " +repository.get(6));

        System.out.println("test get list sorted by name:  " +repository.getAll());

        // testing InMemoryUserRepImpl
        InMemoryUserMealRepositoryImpl repositoryMeal = new InMemoryUserMealRepositoryImpl();
        System.out.println();
        System.out.println("test get by id owner:  " + repositoryMeal.get(4).getOwner().getName());

        System.out.println("test getAllForUser:  " +repositoryMeal.getAllForUser(repositoryMeal.get(1).getOwner()));

        System.out.println("test get for id with to String:  " +repositoryMeal.get(3).toString());


    }
}
