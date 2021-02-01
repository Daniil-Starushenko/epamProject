package by.daniil.epam.project.action.admin;

import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowProductsListAction extends AdminAction {
    Logger logger = LogManager.getLogger(ShowProductsListAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        ProductService productService = factory.getService(ProductService.class);
        List<Product> productList = productService.findAll();
        if (productList != null) {
            request.setAttribute("adminProductList", productList);
            return new Forward("/admin/show/products.jsp", false);
        } else {
            request.setAttribute("message", "empty product list");
            return null;
        }
    }
}
