package by.daniil.epam.project.controller;

import by.daniil.epam.project.action.*;
import by.daniil.epam.project.action.admin.*;
import by.daniil.epam.project.action.deliveryman.*;
import by.daniil.epam.project.action.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActionFromUrlFilter implements Filter {
    private static Logger logger = LogManager.getLogger(ActionFromUrlFilter.class);

    private static Map<String, Class<? extends Action>> actions = new HashMap<>();

    static {
        actions.put("/index", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/register", Register.class);
        actions.put("/user/language", SetLanguageAction.class);

        actions.put("/user/search", SearchUserAction.class);
        actions.put("/user/product", ProductInfo.class);
        actions.put("/user/add/product", ProductToBasket.class);
        actions.put("/user/logout", LogoutAction.class);
        actions.put("/user/basket", BasketInfo.class);
        actions.put("/user/order", CreateOrderAction.class);
        actions.put("/user/show/order", ShowOrderAction.class);

        actions.put("/admin/main", AdminMainAction.class);
        actions.put("/admin/product/init", EditProductAction.class);
        actions.put("/admin/product/create", CreateProductAction.class);
        actions.put("/admin/edit/deliveryman", EditDeliverymanAction.class);
        actions.put("/admin/create/deliveryman", CreateDeliverymanAction.class);
        actions.put("/admin/change/product", EditProductInfoAction.class);
        actions.put("/admin/show/products", ShowProductsListAction.class);

        actions.put("/delivery/main", MainDeliveryManAction.class);
        actions.put("/deliveryman/new_orders", ShowNewOrdersActivity.class);
        actions.put("/deliveryman/order_page", OpenPersonalOrderPageAction.class);
        actions.put("/deliveryman/take_order", TakeOrderAction.class);
        actions.put("/deliveryman/token_orders", ShowTokenOrdersAction.class);
        actions.put("/deliveryman/complete_order", CompleteOrderActive.class);
    }
    //TODO: names of actions and names of jsp

    public void init(FilterConfig config) throws ServletException {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if(request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            logger.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;
            if(endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            Class<? extends Action> actionClass = actions.get(actionName);
            try {
                Action action = actionClass.newInstance();
                action.setCommandName(actionName);
                httpRequest.setAttribute("action", action);
                chain.doFilter(request, response);
            } catch (InstantiationException | IllegalAccessException | NullPointerException e) {
                logger.error("It is impossible to create action handler object", e);
                httpRequest.setAttribute("error", String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
                httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }

}
