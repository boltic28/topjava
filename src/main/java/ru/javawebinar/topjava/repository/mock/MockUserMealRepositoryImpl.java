package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Сергей on 12.03.2016.
 */
@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(MockUserMealRepositoryImpl.class);

    @Override
    public UserMeal save(UserMeal userMeal) {
        LOG.info("save " + userMeal.getDescription() + " " + userMeal.getId());
        return userMeal;
    }

    @Override
    public void delete(int id) {
        LOG.info("delete meal with id  " + id);
    }

    @Override
    public UserMeal get(int id) {
        LOG.info("get meal with id  " + id);
        return null;
    }

    @Override
    public Collection<UserMeal> getAllForUser(User user) {
        LOG.info("get all meals for name  " + user.getName());
        return null;
    }

    @Override
    public Collection<UserMeal> getAllForUserBetweenDT(LocalDateTime sdt, LocalDateTime edt, User user) {
        LOG.info("get all meals beetween dt for name  " + user.getName());
        return null;
    }
}
