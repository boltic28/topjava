package ru.javawebinar.topjava.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 21.03.2016.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)

public class UserMealServiceImplTest {

    @Autowired
    UserMealService mealService;

    @Test
    public void testSave() throws Exception {
        mealService.save(new UserMeal(LocalDateTime.now().minusDays(2), "testing- update", 1 ), 1);
    }

    @Test
    public void testUpdate() throws Exception {
        mealService.update(new UserMeal(LocalDateTime.now(), "testing- update", 1 ), 1);
    }

    @Test
    public void testGet() throws Exception {
          mealService.get(1,1);
    }

    @Test
    public void testGetAll() throws Exception {
        mealService.getAll(1);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        mealService.getBetweenDateTimes(LocalDateTime.now().minusMonths(1), LocalDateTime.now(), 1);
    }

    @Test
    public void testDelete() throws Exception {
        mealService.delete(1,1);
    }
}