package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> finalListOfMeal = new ArrayList<>();
        List<UserMeal> tmpListOfMeals = new ArrayList<>();

        //   выставляем текущую дату
        LocalDate now = LocalDate.now();

        // выбираем что съедено сегодня и считаем ккал
        for (UserMeal meal: mealList) {
            // отнимаем от разрешенного кол-ва то что съедено
            if(meal.getDateTime().toLocalDate().equals(now)) {
                caloriesPerDay -= meal.getCalories() ;
                tmpListOfMeals.add(meal);
            }
        }

        // исходя из съеденных ккал выдаем значения
        for (UserMeal meal: tmpListOfMeals) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                finalListOfMeal.add(new UserMealWithExceed(meal.getDateTime(),
                        meal.getDescription(), meal.getCalories(),
                        (caloriesPerDay < 0)));
            }
        }

        return finalListOfMeal;
    }
}
