package by.daniil.epam.project.action.user;

import by.daniil.epam.project.domain.Order;
import by.daniil.epam.project.domain.OrderingStatus;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowOrderAction extends UserAction {
    Logger logger = LogManager.getLogger(ShowOrderAction.class);
    //TODO: massage
    //TODO:

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        Forward forward = new Forward("/user/order.jsp", false);
        OrderService orderService = factory.getService(OrderService.class);
        User user = (User)session.getAttribute("authorizedUser");
        List<Order> orders = orderService.findByUserAndStatus(user.getIdentity(), OrderingStatus.IN_BASKET.toString());
        if (orders != null) {
//            orders = takeProductsFromOrder(orders);
            request.setAttribute("userOrders", orders);
        }
        List<Order> transitOrders = orderService.findByUserAndStatus(user.getIdentity(), OrderingStatus.IN_TRANSIT.toString());
        if (transitOrders != null) {
            request.setAttribute("transitUserOrders", transitOrders);
        }
        return forward;
    }

//    private List<Order> takeProductsFromOrder(List<Order> orders) throws PersistentException {
//        List<Order> orderList = new ArrayList<>();
//        List<OrderItem> orderItems = new ArrayList<>();
//        OrderItemService orderItemService = factory.getService(OrderItemService.class);
//
//        for (int i = 0; i < orders.size(); i++) {
//            orderItems = orderItemService.findByOrderId(orders.get(i).getIdentity());
//            for (int j = 0; j < orderItems.size(); j++) {
//                orders.get(i).setOrderProduct(orderItems.get(j));
//                orderList.add(orders.get(i));
//            }
//        }
//        return orderList;
//    }
}
