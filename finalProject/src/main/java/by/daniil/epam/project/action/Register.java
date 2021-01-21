package by.daniil.epam.project.action;

import by.daniil.epam.project.domain.Role;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Register extends Action {
    private static Logger logger = LogManager.getLogger(Register.class);

    private static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();

    static {
        menu.put(Role.USER, new ArrayList<>(Arrays.asList(
                new MenuItem("/user/search.html", "shop"),
                new MenuItem("/user/basket.html", "basket"),
                new MenuItem("/user/logout.html", "logout")
        )));
        menu.put(Role.ADMINISTRATOR, new ArrayList<>(Arrays.asList(
                new MenuItem("", "пользователи"),
                new MenuItem("", "доставщики"),
                new MenuItem("/user/logout.html", "logout")
        )));
        menu.put(Role.DELIVERY_MAN, new ArrayList<>(Arrays.asList(
                new MenuItem("", "заказы"),
                new MenuItem("/user/logout.html", "logout")
        )));
    }

    @Override
    public Set<Role> getAllowRoles() {
        return null;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("mail");
        if(login != null && password != null) {
            UserService service = factory.getService(UserService.class);
            User user = service.findByLoginAndPassword(login, password);
            if(user == null) {
                HttpSession session = request.getSession();
                user = new User();
                user.setName(name);
                user.setLogin(login);
                user.setPassword(password);
                user.setMail(email);
                user.setRole(Role.USER);
                user.setRegistrationDate(createDate());
                service.create(user);
                session.setAttribute("authorizedUser", user);
                session.setAttribute("menu", menu.get(user.getRole()));
                logger.info(String.format("user \"%s\" is registered in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
                return new Forward("/user/search.html");
            } else {
                request.setAttribute("message", "Имя пользователя или пароль не опознанны");
                logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
            }
        }
        return null;
    }

    public String createDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date date = new Date();
        String registrationDate = ft.format(date);
        return registrationDate;
    }
}
