package by.daniil.epam.project.action;

import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction extends AuthorizedUserAction {
    Logger logger = LogManager.getLogger(LogoutAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        User user = getAuthorizedUser();
        logger.info(String.format("user \"%s\" is logged out", user.getLogin()));
        request.getSession(false).invalidate();
        return new Forward("/login.html");
    }
}
