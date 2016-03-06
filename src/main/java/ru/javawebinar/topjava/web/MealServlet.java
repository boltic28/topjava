package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Сергей on 05.03.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
//data for testing
    private List<UserMeal> mealList = Arrays.asList(
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 6, 9, 0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 6, 11, 0), "Обед", 1000),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 6, 13, 0), "Ужин", 500),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 7, 8, 0), "Завтрак", 1000),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 7, 14, 0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2016, Month.MARCH, 7, 18, 0), "Ужин", 510)
    );
    private List<String> list = Arrays.asList(
            "Anton","Petr","frank"
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

//        req.setAttribute("list", getMealWithExceedFor(7, 15, 2000));

        req.setAttribute("name", "Serg");

        LOG.debug("on jsp");
        req.setAttribute("list", list);
        req.getRequestDispatcher("/mealList.jsp").forward(req,resp);
    }

    private List<UserMealWithExceed> getMealWithExceedFor(int hourS, int houtE, int callories){
        return UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(hourS, 0), LocalTime.of(houtE, 0), callories);
    }

}
