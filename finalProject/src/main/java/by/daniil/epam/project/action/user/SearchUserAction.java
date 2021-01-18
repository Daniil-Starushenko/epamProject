package by.daniil.epam.project.action.user;

import by.daniil.epam.project.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchUserAction  extends UserAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        return new Forward("/user/search.jsp", false);
    }
}
