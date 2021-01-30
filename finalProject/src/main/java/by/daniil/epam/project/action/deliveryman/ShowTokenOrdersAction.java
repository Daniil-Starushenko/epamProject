package by.daniil.epam.project.action.deliveryman;

import by.daniil.epam.project.domain.*;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShowTokenOrdersAction extends DeliverymanAction {
    Logger logger = LogManager.getLogger(ShowTokenOrdersAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        OrderService orderService = factory.getService(OrderService.class);
        UserService userService = factory.getService(UserService.class);
        OrderItemService orderItemService = factory.getService(OrderItemService.class);
        ProductService productService = factory.getService(ProductService.class);
        DeliverymanOrderService deliverymanOrderService = factory.getService(DeliverymanOrderService.class);
        DeliverymanService deliverymanService = factory.getService(DeliverymanService.class);
        if (orderService != null) {
            List<Order> tokenOrders = new ArrayList<>();
            User user = new User();
            user = (User) session.getAttribute("authorizedUser");
            DeliveryMan currentDeliveryman = deliverymanService.findByUsrId(user.getIdentity());
            List<DeliverymanOrder> deliverymanOrders = deliverymanOrderService.findByDeliverymanId(currentDeliveryman.getIdentity());
            for (DeliverymanOrder orders : deliverymanOrders) {
                Order order = orderService.findById(orders.getOrder().getIdentity());
                if (order.getStatus() == OrderingStatus.IN_TRANSIT) {
                    tokenOrders.add(order);
                }
            }

            for (Order order : tokenOrders) {
                int userId = order.getCustomer().getIdentity();
                user = takeUser(userId, userService);
                order.setCustomer(user);
                order.setProductList(takeProducts(order.getIdentity(), orderItemService, productService));
                System.out.println();
            }

            request.setAttribute("currentOrdersToComplete", tokenOrders);
            return new Forward("/deliveryman/token_orders.jsp", false);
        }
        return null;
    }

    private User takeUser(Integer id, UserService service) throws PersistentException {
        return service.findById(id);
    }

    private List<Product> takeProducts(Integer id, OrderItemService service, ProductService productService) throws PersistentException {
        List<OrderItem> orderItems = service.findByOrderId(id);
        List<Product> products = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            for (int i = 0; i < orderItem.getQuantity(); i++) {
                products.add(productService.findById(orderItem.getProductList().get(0).getIdentity()));
            }
        }
        return products;
    }
}