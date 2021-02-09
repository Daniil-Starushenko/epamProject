package by.daniil.epam.project.action.admin;

import by.daniil.epam.project.domain.InfoMessage;
import by.daniil.epam.project.domain.Product;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.ProductService;
import by.daniil.epam.project.validator.ProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProductInfoAction extends AdminAction {
    Logger logger = LogManager.getLogger(EditProductInfoAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        ProductService productService = factory.getService(ProductService.class);
        String identity = request.getParameter("identity");
        if (identity != null) {
            int productId = Integer.parseInt(identity);
            Product product = productService.findById(productId);
            request.setAttribute("productToChange", product);
            return null;
        }
        String identityToChange = request.getParameter("identityToChange");
        if (identityToChange != null) {
            int idToChange = Integer.parseInt(identityToChange);
            request.setAttribute("productToChange", productService.findById(idToChange));
            ProductValidator productValidator = new ProductValidator();
            Product product = productValidator.validate(request);
            if (product == null) {
                return null;
            }
            String originalName = productService.findById(idToChange).getProductName();
            if (!product.getProductName().equals(originalName)) {
                if (productService.findByName(product.getProductName()) != null) {
                    request.setAttribute("message", "such product is exist");
                    return null;
                }
            }
            product.setIdentity(idToChange);
            productService.save(product);
            request.setAttribute("messageType", InfoMessage.SUCCESS_TYPE);
            request.setAttribute("message", "product is changed");
            return new Forward("/admin/show/products.jsp", false);
        }
        return null;
    }
}
