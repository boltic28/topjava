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
import java.util.stream.Collectors;

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
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2016, Month.FEBRUARY, 28,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2016, Month.FEBRUARY, 28,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2016, Month.FEBRUARY, 28,20,0), "Ужин", 510)
        );

        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(
            List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> list = new ArrayList<>();

        // находим общую сумму ккал за день
        int sumCall = mealList
                .stream()
                .filter(s -> s.getDateTime().toLocalDate().equals(LocalDate.now()))
                .collect(Collectors.summingInt(UserMeal::getCalories));

        // находим прием пищи в указаный период и создаем соответствующий Meal с дальнейшим помощенгием в лист
        mealList
                .stream()
                .filter(s -> s.getDateTime().toLocalDate().equals(LocalDate.now()) &&
                        TimeUtil.isBetween(s.getDateTime().toLocalTime(), startTime, endTime))
                .forEach(s -> list.add(new UserMealWithExceed(s.getDateTime(), s.getDescription(),
                        s.getCalories(), sumCall > caloriesPerDay)));

        return list;
    }
}
