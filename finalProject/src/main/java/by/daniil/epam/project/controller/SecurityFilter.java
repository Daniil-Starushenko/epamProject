package by.daniil.epam.project.controller;

import by.daniil.epam.project.action.Action;
import by.daniil.epam.project.action.MainAction;
import by.daniil.epam.project.domain.Role;
import by.daniil.epam.project.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    private static Logger logger = LogManager.getLogger(SecurityFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            Action action = (Action)httpRequest.getAttribute("action");
            Set<Role> allowRoles = action.getAllowRoles();
            String userName = "unauthorized user";
            HttpSession session = httpRequest.getSession(false);
            User user = null;
            if(session != null) {
                user = (User)session.getAttribute("authorizedUser");
                action.setAuthorizedUser(user);
                String errorMessage = (String)session.getAttribute("SecurityFilterMessage");
                if(errorMessage != null) {
                    httpRequest.setAttribute("message", errorMessage);
                    session.removeAttribute("SecurityFilterMessage");
                }
            }
            boolean canExecute = allowRoles == null;
            if(user != null) {
                userName = "\"" + user.getLogin() + "\" user";
                canExecute = canExecute || allowRoles.contains(user.getRole());
            }
            if(canExecute) {
                chain.doFilter(request, response);
            } else {
                logger.info(String.format("Trying of %s access to forbidden resource \"%s\"", userName, action.getCommandName()));
                if(session != null && action.getClass() != MainAction.class) {
                    session.setAttribute("SecurityFilterMessage", "Доступ запрещён");
                }
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
