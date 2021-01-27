package by.daniil.epam.project.action.deliveryman;

import by.daniil.epam.project.domain.*;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.DeliveryManService;
import by.daniil.epam.project.service.DeliverymanOrderService;
import by.daniil.epam.project.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TakeOrderAction extends DeliverymanAction {
    Logger logger = LogManager.getLogger(TakeOrderAction.class);
    //TODO: проверки
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        DeliveryManService deliveryManService = factory.getService(DeliveryManService.class);
        HttpSession session = request.getSession(false);
        String identity = request.getParameter("identity");
        if (identity != null) {
            int orderId = Integer.parseInt(identity);
            User user = (User)session.getAttribute("authorizedUser");
            DeliveryMan deliveryMan = deliveryManService.findByUsrId(user.getIdentity());
            DeliverymanOrderService deliverymanOrderService = factory.getService(DeliverymanOrderService.class);
            OrderService orderService = factory.getService(OrderService.class);
            Order order = orderService.findById(orderId);
            order.setStatus(OrderingStatus.IN_TRANSIT);
            order.setDeliveryManForOrder(deliveryMan);
            orderService.save(order);
            DeliverymanOrder deliverymanOrder = new DeliverymanOrder();
            deliverymanOrder.setDeliveryMan(deliveryMan);
            deliverymanOrder.setOrder(order);
            int deliverymanOrderId = deliverymanOrderService.create(deliverymanOrder);
            return new Forward("/deliveryman/new_orders.html");
        }
        return null;
    }
}
