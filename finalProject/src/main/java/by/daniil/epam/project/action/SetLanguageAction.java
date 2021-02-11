package by.daniil.epam.project.action;

import by.daniil.epam.project.domain.Role;
import by.daniil.epam.project.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class SetLanguageAction extends Action {
    Logger logger = LogManager.getLogger(SetLanguageAction.class);

    @Override
    public Set<Role> getAllowRoles() {
        return null;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(true);
        String language = request.getParameter("lang");
        logger.debug(request.getParameter("lang"));
        String locale = null;
        switch (language) {
            case "rus":
                locale = "ru_RU";
                logger.debug("locale = {}", locale);
                break;
            case "eng":
                locale = "en_US";
                logger.debug("locale = {}", locale);
                break;
            default:
                break;
        }

        session.setAttribute("text", locale);
        Cookie localeCookie = new Cookie("text", locale);
        response.addCookie(localeCookie);
        String currentPage = request.getParameter("page");

        return new Forward(currentPage);
    }
}
