package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository mealRepository;

    @Override
    public UserMeal save(UserMeal userMeal) {
        mealRepository.save(userMeal);
        return userMeal;
    }

    @Override
    public void delete(int id) {
        mealRepository.delete(id);
    }

    @Override
    public UserMeal get(int id) {
        return mealRepository.get(id);
    }

    @Override
    public List<UserMeal> getAll(User user) {
        return (List<UserMeal>) mealRepository.getAllForUser(user);
    }

    @Override
    public List<UserMeal> getBetweenTime(LocalDateTime start, LocalDateTime end, User user) {
        return mealRepository.getAllForUser(user).stream()
                .filter(meal -> meal.getDateTime().isAfter(start) && meal.getDateTime().isBefore(end))
                .sorted((m1, m2) -> m1.getDateTime().compareTo(m2.getDateTime()))
                .collect(Collectors.toList());
    }
}
