package by.daniil.epam.project.action.admin;

import by.daniil.epam.project.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminMainAction extends AdminAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        return new Forward("/admin/main.jsp", false);
    }
}
