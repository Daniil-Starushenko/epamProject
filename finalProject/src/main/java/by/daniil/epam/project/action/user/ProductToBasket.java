package by.daniil.epam.project.action.user;

import by.daniil.epam.project.domain.InfoMessage;
import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ProductToBasket extends UserAction {
    private static final String PRODUCT_WAS_ADDED_MESSAGE_BUNDLE = "user.shop.product.was.added";
    Logger logger = LogManager.getLogger(ProductInfo.class);
    private Product productToAdd = new Product();
    private List<Product> basket;

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession userSession = request.getSession(false);
        ProductService service = factory.getService(ProductService.class);
        String id = (String)request.getParameter("productIdentity");
        if (id != null) {
            basket = new ArrayList<>();
            int productId = Integer.parseInt(id);
            productToAdd = service.findById(productId);
            String quantity = request.getParameter("quantity");
            int numberOfProducts = Integer.parseInt(quantity);
            basket = (List<Product>)userSession.getAttribute("basket");
            for (int i = 0; i < numberOfProducts; i++) {
                basket.add(productToAdd);
            }
            userSession.setAttribute("basket", basket);
        }

        if (productToAdd != null) {
            request.setAttribute("product", productToAdd);
            request.setAttribute("messageType", InfoMessage.SUCCESS_TYPE);
            request.setAttribute("message", PRODUCT_WAS_ADDED_MESSAGE_BUNDLE);
            return new Forward("/user/product.jsp", false);
        } else {
            request.setAttribute("message", "доступ закрыт");
            logger.info(String.format("access was failed"));
        }
        return null;
    }
}