package by.daniil.epam.project.action.user;

import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductInfo extends UserAction {
    Logger logger = LogManager.getLogger(ProductInfo.class);
    private Product productToAdd = new Product();

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession userSession = request.getSession(false);
        ProductService service = factory.getService(ProductService.class);
        String id = (String)request.getParameter("identity");
        if (id != null) {
           int productId = Integer.parseInt(id);
           productToAdd = service.findById(productId);
        }

        if (productToAdd != null) {
            request.setAttribute("product", productToAdd);
            return new Forward("/user/product.jsp", false);
        } else {
            request.setAttribute("message", "доступ закрыт");
            logger.info(String.format("access was failed"));
        }
        return null;
    }
}
