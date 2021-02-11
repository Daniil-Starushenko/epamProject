package by.daniil.epam.project.validator;

import by.daniil.epam.project.domain.InfoMessage;
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
    private static final String INVALID_INPUT_MESSAGE_KEY = "login.invalid.input";

    @Override
    public User validate(HttpServletRequest request) {
        User user = new User();
        String parameter = request.getParameter("name");
        if (!checkParam(request, parameter)) {
            return null;
        }
        user.setName(parameter);
        parameter = request.getParameter("login");
        if (!checkParam(request, parameter)) {
            return null;
        }
        user.setLogin(parameter);

        parameter = request.getParameter("password");
        if (!checkParam(request, parameter)) {
            return null;
        }
        user.setPassword(parameter);

        parameter = request.getParameter("mail");
        if (parameter != null) {
            user.setMail(parameter);
        }
        user.setRegistrationDate(getDate());

        return user;
    }

    private boolean checkParam(HttpServletRequest request, String parameter) {
        if (parameter != null) {
            if (!complexValidator.isValidLoginOrName(parameter)) {
                request.setAttribute("messageType", InfoMessage.ERROR_TYPE);
                request.setAttribute("message", INVALID_INPUT_MESSAGE_KEY);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date date = new Date();
        return ft.format(date);
    }
}

