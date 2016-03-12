package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public class UserMealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserMealRestController.class);

    @Autowired
    private UserMealService service;

    public List<UserMeal> getAll(User user) {
        LOG.info("getAllForMeal");
        return service.getAll(user);
    }

    public UserMeal get(int id) {
        LOG.info("get meal " + id);
        return service.get(id);
    }

    public UserMeal create(UserMeal userMeal) {
        userMeal.setId(0);
        LOG.info("create " + userMeal);
        return service.save(userMeal);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(UserMeal userMeal, int id) {
        userMeal.setId(id);
        LOG.info("update " + userMeal);
        service.save(userMeal);
    }

    public List<UserMeal> getBetweenTime(LocalDateTime start, LocalDateTime end) {
        LOG.info("getBetween " + start + " and " + end);
        return service.getBetweenTime(start,end);
    }
}
