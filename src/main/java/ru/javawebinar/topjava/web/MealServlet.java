package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private UserMealRepository repository;
    private UserRepository userRepository;
    User currentUser = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryUserMealRepositoryImpl();
        userRepository = new InMemoryUserRepositoryImpl();
//        currentUser;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")),
                currentUser);

        LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
        repository.save(userMeal);
        response.sendRedirect("meals?user_id=" + currentUser.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (currentUser == null){
            LOG.debug("current user is null");
            String userId = request.getParameter("user_id");
            if(userId != null){
                LOG.debug("kept id of user, is " + userId);
                currentUser = userRepository.get(Integer.parseInt(request.getParameter("user_id")));
            }else {
                LOG.debug("enter to entrance page");
                request.getRequestDispatcher("enter.jsp").forward(request, response);
            }
        }
            String action = request.getParameter("action");

            if (action == null) {
                LOG.info("getAllForUser");
                request.setAttribute("user", currentUser);
                request.setAttribute("mealList",
                        UserMealsUtil.getWithExceeded(repository.getAllForUser(currentUser), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/mealList.jsp").forward(request, response);
            } else if (action.equals("delete")) {
                int id = getId(request);
                LOG.info("Delete {}", id);
                repository.delete(id);
                response.sendRedirect("meals");
            } else {
                final UserMeal meal = action.equals("create") ?
                        new UserMeal(LocalDateTime.now(), "", 1000, currentUser) :
                        repository.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
            }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
