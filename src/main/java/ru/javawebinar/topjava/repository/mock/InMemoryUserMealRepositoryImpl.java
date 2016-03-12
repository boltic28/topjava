package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        User petr = new User(3,"petr","bo28@mail.ru","1234ass", Role.ROLE_USER);
        repository.put(1,new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, petr ));
        repository.put(2,new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, new User(3,"petr","bo28@mail.ru","1234ass", Role.ROLE_USER)));
        repository.put(3,new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак",1000,petr ));
        repository.put(4,new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, petr));
        repository.put(5,new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, petr));
        repository.put(6,new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, new User(3,"petr","bo28@mail.ru","1234ass", Role.ROLE_USER)));
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public UserMeal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAllForUser(User user) {
        List<UserMeal> list = repository.values().stream()
                .filter(meal -> meal.getOwner().equals(user))
                .sorted((m1,m2) -> m1.getDateTime().compareTo(m2.getDateTime()))
                .collect(Collectors.toList());
    return list.size() > 0 ? list : null;
    }
}

