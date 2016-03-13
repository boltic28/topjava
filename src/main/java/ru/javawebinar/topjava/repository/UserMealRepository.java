package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal, User user);

    void delete(int id, User user);

    UserMeal get(int id, User user);

    Collection<UserMeal> getAllForUser(User user);

    Collection<UserMeal> getAllForUserBetweenDT(LocalDateTime sdt, LocalDateTime edt, User user);
}
