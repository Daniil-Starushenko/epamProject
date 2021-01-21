package by.daniil.epam.project.action.user;

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
            basket = (List<Product>)userSession.getAttribute("basket");
            basket.add(productToAdd);
            userSession.setAttribute("basket", basket);
        }

        if (productToAdd != null) {
            request.setAttribute("product", productToAdd);
            return new Forward("/user/search.html");
        } else {
            request.setAttribute("message", "доступ закрыт");
            logger.info(String.format("access was failed"));
        }
        return null;
    }
}