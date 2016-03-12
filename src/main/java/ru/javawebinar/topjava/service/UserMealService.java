package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(UserMeal userMeal);

    void delete(int id);

    UserMeal get(int id);

    List<UserMeal> getAll(User user);

    List<UserMeal> getBetweenTime(LocalDateTime start, LocalDateTime end, User user);
}
