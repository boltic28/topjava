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
 * Created by NotePad on 07.03.2016.
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("names", "antoniy");
        System.out.println("go = " + request.getParameter("go"));
//        if(request.getParameter("go") != null){
        System.out.println("__________________");
            System.out.println(request.getParameter("filter_date"));
            System.out.println(request.getParameter("filter_st"));
            System.out.println(request.getParameter("filter_et"));
            System.out.println(request.getParameter("cal_lim"));
//        }
        request.setAttribute("list", getMealWithExceedFor(7, 20, 2000));


        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }

    private List<UserMealWithExceed> getMealWithExceedFor(int hourS, int hourE, int callories){
        return UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(hourS, 0), LocalTime.of(hourE, 0), callories);
    }
}
