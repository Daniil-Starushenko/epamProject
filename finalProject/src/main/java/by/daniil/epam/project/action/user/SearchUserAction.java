package by.daniil.epam.project.action.user;

import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchUserAction  extends UserAction {
    Logger logger = LogManager.getLogger(SearchUserAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        String productIdToAdd = request.getParameter("productToAdd");
        ProductService service = factory.getService(ProductService.class);
        List<Product> products = service.findAll();
        if (!products.isEmpty()) {
            request.setAttribute("products", products);
            return new Forward("/user/search.jsp", false);
        } else {
            request.setAttribute("message", "Имя пользователя или пароль не опознанны");
            logger.info(String.format("access was failed"));
        }
        return null;
    }
}
