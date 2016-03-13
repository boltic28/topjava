package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
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
    ConfigurableApplicationContext appCtx;

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
        repository.save(userMeal, currentUser);
        response.sendRedirect("meals?user_id=" + currentUser.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // user initialization
        if (currentUser == null){
            LOG.debug("current user is null");
            String userMail = request.getParameter("user_mail");
            String userPass = request.getParameter("user_pass");
            LOG.info("taked parameters: " + userMail + ", " + userPass);
            if(userMail != null && userPass != null){
                LOG.debug(userMail + " entered to system with pass " + userPass);
                currentUser = userRepository.getByEmail(userMail);

                if(!currentUser.getPassword().equals(userPass)) {
                    currentUser = null;
                }else{
                    LOG.info("User id is " + currentUser.getId() + ", name is " + currentUser.getName());
                }

                appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
                appCtx.getBean(UserMealRestController.class);
                LOG.info(Arrays.toString(appCtx.getBeanDefinitionNames()));
            }else {
                LOG.debug("enter to entrance page");
                request.getRequestDispatcher("enter.jsp").forward(request, response);
            }
        }else {

            // if user not null
            String action = request.getParameter("action");
            request.setAttribute("user", currentUser);

            if (action == null) {

                LOG.info("getAllForUser");
                request.setAttribute("mealList",
                        UserMealsUtil.getWithExceeded(repository.getAllForUser(currentUser), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));

                request.getRequestDispatcher("/mealList.jsp").forward(request, response);

            } else if (action.equals("delete")) {
                int id = getId(request);
                LOG.info("Delete {}", id);
                repository.delete(id, currentUser);
                response.sendRedirect("meals");

            } else if (action.equals("filter")) {
                try {
                    LocalDateTime start = LocalDateTime.parse(request.getParameter("dt_start"));
                    LocalDateTime end = LocalDateTime.parse(request.getParameter("dt_end"));
                    LOG.info("take between " + start + " and " + end);
                    request.setAttribute("mealList",
                            UserMealsUtil.getWithExceeded(repository.getAllForUserBetweenDT(
                                    LocalDateTime.from(start),
                                    LocalDateTime.from(end),
                                    currentUser),
                                    UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
                    request.getRequestDispatcher("/mealList.jsp").forward(request, response);
                } catch (DateTimeParseException e) {
                    LOG.error("!--- don't feel all fields in filter form ----!");
                    request.setAttribute("mealList",
                            UserMealsUtil.getWithExceeded(repository.getAllForUser(currentUser), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
                    request.getRequestDispatcher("/mealList.jsp").forward(request, response);
                }
            } else {
                final UserMeal meal = action.equals("create") ?
                        new UserMeal(LocalDateTime.now(), "", 1000, currentUser) :
                        repository.get(getId(request),currentUser);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
            }
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
