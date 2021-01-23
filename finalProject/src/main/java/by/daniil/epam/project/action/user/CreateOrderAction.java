package by.daniil.epam.project.action.user;

import by.daniil.epam.project.domain.*;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.OrderItemService;
import by.daniil.epam.project.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateOrderAction extends UserAction {
    Logger logger = LogManager.getLogger(CreateOrderAction.class);
    List<Product> basket = new ArrayList<>();

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        basket = (List<Product>) session.getAttribute("basket");

        if (basket.size() != 0) {
            OrderItemService itemService = factory.getService(OrderItemService.class);
            OrderService orderService = factory.getService(OrderService.class);
            Order order = new Order();
            String currentDate = createDate();
            order.setCustomer((User) session.getAttribute("authorizedUser"));
            order.setAddress(request.getParameter("address"));
            order.setPhoneNumber(request.getParameter("telNumber"));
            order.setDateOfOrdering(currentDate);
            String totalPrice = request.getParameter("totalPrice");
            Double totalPriceDouble = Double.valueOf(totalPrice);
            order.setTotalPrice(totalPriceDouble);
            order.setStatus(OrderingStatus.IN_BASKET);
            Integer orderId = orderService.create(order);
            order.setIdentity(orderId);
            for (int i = 0; i < basket.size(); i++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProductList(basket.get(i), 1);
                orderItem.setQuantity(1);
                orderItem.setUser((User) session.getAttribute("authorizedUser"));
                itemService.create(orderItem);
            }

            freeBasket(session);
            return new Forward("/user/search.html");
        } else {
            return null;
        }
    }

    public String createDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date date = new Date();
        String registrationDate = ft.format(date);
        return registrationDate;
    }

    public void freeBasket(HttpSession session) {
        basket.clear();
        session.removeAttribute("basket");
        session.setAttribute("basket", basket);
    }
}
