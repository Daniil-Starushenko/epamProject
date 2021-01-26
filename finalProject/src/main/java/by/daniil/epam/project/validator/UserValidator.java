package by.daniil.epam.project.validator;

import by.daniil.epam.project.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserValidator implements Validator<User>{

    @Override
    public User validate(HttpServletRequest request) {
        User user = new User();
        String parameter = request.getParameter("name");
        if (parameter != null) {
            user.setName(parameter);
        }
        parameter = request.getParameter("login");
        if (parameter != null) {
            user.setLogin(parameter);
        }
        parameter = request.getParameter("password");
        if (parameter != null) {
            user.setPassword(parameter);
        }
        parameter = request.getParameter("mail");
        if (parameter != null) {
            user.setMail(parameter);
        }
        user.setRegistrationDate(getDate());

        return user;
    }

    public String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date date = new Date();
        return ft.format(date);
    }
}

