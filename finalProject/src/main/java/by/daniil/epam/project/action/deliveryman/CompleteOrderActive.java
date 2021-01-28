package by.daniil.epam.project.action.deliveryman;

import by.daniil.epam.project.domain.Order;
import by.daniil.epam.project.domain.OrderingStatus;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CompleteOrderActive extends DeliverymanAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        String orderIdToComplete = request.getParameter("identity");
        OrderService orderService = factory.getService(OrderService.class);
        if (orderIdToComplete != null) {
            int id = Integer.parseInt(orderIdToComplete);
            Order order = orderService.findById(id);
            order.setStatus(OrderingStatus.READY);
            orderService.save(order);
            return new Forward("/delivery/main.html");
        }
        return null;
    }
}
