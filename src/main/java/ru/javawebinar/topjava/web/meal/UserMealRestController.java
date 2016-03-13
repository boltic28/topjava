package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserMealRestController.class);

    @Autowired
    private UserMealService mealService;

    public List<UserMeal> getAll(User user) {
        LOG.info("getAllForMeal");
        return mealService.getAll(user);
    }

    public UserMeal get(int id, User user) {
        LOG.info("get meal " + id);
        return mealService.get(id, user);
    }

    public UserMeal create(UserMeal userMeal, User user) {
        userMeal.setId(0);
        LOG.info("create " + userMeal);
        return mealService.save(userMeal, user);
    }

    public void delete(int id, User user) {
        LOG.info("delete " + id);
        mealService.delete(id, user);
    }

    public void update(UserMeal userMeal, int id, User user) {
        userMeal.setId(id);
        LOG.info("update " + userMeal);
        mealService.save(userMeal, user);
    }

    public List<UserMeal> getBetweenTime(LocalDateTime start, LocalDateTime end) {
        LOG.info("getBetween " + start + " and " + end);
        return mealService.getBetweenTime(start,end,new User());
    }
}
