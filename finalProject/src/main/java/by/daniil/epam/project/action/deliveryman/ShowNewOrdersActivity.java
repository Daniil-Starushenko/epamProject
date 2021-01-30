package by.daniil.epam.project.action.deliveryman;

import by.daniil.epam.project.domain.Order;
import by.daniil.epam.project.domain.OrderItem;
import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.OrderItemService;
import by.daniil.epam.project.service.OrderService;
import by.daniil.epam.project.service.ProductService;
import by.daniil.epam.project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShowNewOrdersActivity extends DeliverymanAction {
    Logger logger = LogManager.getLogger(ShowNewOrdersActivity.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        OrderService orderService = factory.getService(OrderService.class);
        UserService userService = factory.getService(UserService.class);
        OrderItemService orderItemService = factory.getService(OrderItemService.class);
        ProductService productService = factory.getService(ProductService.class);

        if (orderService != null) {
            List<Order> newOrders = new ArrayList<>();
            newOrders = orderService.findByStatus("in_basket");
            User user = new User();
            for (Order order : newOrders ) {
                int userId = order.getCustomer().getIdentity();
                 user = takeUser(userId, userService);
                 order.setCustomer(user);
                 order.setOrderProducts(takeProducts(order.getIdentity(), orderItemService, productService));
                System.out.println();
            }

            request.setAttribute("newActiveOrder", newOrders);
            return new Forward("/deliveryman/new_orders.jsp", false);
        }
        return null;
    }

    private User takeUser(Integer id, UserService service) throws PersistentException {
        return service.findById(id);
    }

    private List<OrderItem> takeProducts(Integer id, OrderItemService service, ProductService productService) throws PersistentException {
        List<OrderItem> orderItems = service.findByOrderId(id);
        int quantity;
        Product product;
        for (OrderItem orderItem : orderItems) {
            quantity = orderItem.getQuantity();
            product = productService.findById(orderItem.getProductList().get(0).getIdentity());
            orderItem.setProductList(product, quantity);
            System.out.println();
        }
        return orderItems;
    }
}
