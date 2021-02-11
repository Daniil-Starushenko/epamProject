package by.daniil.epam.project.validator;

import by.daniil.epam.project.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class <code>UserValidator</code> is needed to validate info that
 * user inputs in login form or in register form to check is this info
 * valid or not
 * <code>complexValidator</code> is object of <code>ComplexValidator</code> class
 * to use some its  methods
 */
public class UserValidator implements Validator<User>{

    @Override
    public User validate(HttpServletRequest request) {
        User user = new User();
        String parameter = request.getParameter("name");
        if (parameter != null) {
            if (complexValidator.isValidLoginOrName(parameter)) {
            user.setName(parameter);
            } else {
                return null;
            }
        } else {
            return null;
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

