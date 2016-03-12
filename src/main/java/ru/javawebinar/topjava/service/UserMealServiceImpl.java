package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal userMeal) {
        repository.save(userMeal);
        return userMeal;
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public UserMeal get(int id) {
        return repository.get(id);
    }

    @Override
    public List<UserMeal> getAll(User user) {
        return (List<UserMeal>) repository.getAllForUser(user);
    }

    @Override
    public List<UserMeal> getBetweenTime(LocalDateTime start, LocalDateTime end, User user) {
        return repository.getAllForUser(user).stream()
                .filter(meal -> meal.getDateTime().isAfter(start) && meal.getDateTime().isBefore(end))
                .sorted((m1, m2) -> m1.getDateTime().compareTo(m2.getDateTime()))
                .collect(Collectors.toList());
    }
}
