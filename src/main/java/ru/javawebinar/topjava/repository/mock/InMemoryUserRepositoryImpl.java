package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Сергей on 12.03.2016.
 */
public class InMemoryUserRepositoryImpl extends MockUserRepositoryImpl {
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        repository.put(1,new User(1,"serg","b28@mail.ru","1234ass", Role.ROLE_USER));
        repository.put(2,new User(2,"andrre","b28@mail.ru","1234ass", Role.ROLE_USER));
        repository.put(3,new User(3,"petr","bo28@mail.ru","1234ass", Role.ROLE_USER));
        repository.put(4,new User(4,"vasia","b28@mail.ru","1234ass", Role.ROLE_USER));
        repository.put(5,new User(5,"frank","b28@mail.ru","1234ass", Role.ROLE_USER));
    }

    @Override
    public User save(User user) {
        super.save(user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        super.delete(id);
        try {
            repository.remove(id);
            return true;
        } catch (NotFoundException e) {
            return false;
         }
    }

    @Override
    public User get(int id) {
        super.get(id);
        try {
            return repository.get(id);
        } catch (NotFoundException e) {
            return null;
        }
    }

    @Override
    public User getByEmail(String email) {
        super.getByEmail(email);
            for(Map.Entry<Integer, User> user:repository.entrySet()){
                if(user.getValue().getEmail().equalsIgnoreCase(email))
                    return user.getValue();
            }

            return null;
    }

    @Override
    public List<User> getAll() {
        super.getAll();
        return repository.values().stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());
    }
}
