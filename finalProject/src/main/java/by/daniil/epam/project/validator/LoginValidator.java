package by.daniil.epam.project.validator;

import by.daniil.epam.project.domain.User;

import javax.servlet.http.HttpServletRequest;

public class LoginValidator implements Validator<User> {

    @Override
    public User validate(HttpServletRequest request) {
        User user = new User();
        String parameter = request.getParameter("login");
        if (!checkParam(request, parameter)) {
            return null;
        }
        user.setLogin(parameter);

        parameter = request.getParameter("password");
        if (!checkParam(request, parameter)) {
            return null;
        }
        user.setPassword(parameter);
        return user;
    }

    private boolean checkParam(HttpServletRequest request, String parameter) {
        if (parameter != null) {
            return complexValidator.isValidLoginOrName(parameter);
        }
        return true;
    }
}
