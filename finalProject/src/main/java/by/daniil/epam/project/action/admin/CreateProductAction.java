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

public class CreateProductAction extends AdminAction {
    Logger logger = LogManager.getLogger(CreateProductAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        ProductValidator productValidator = new ProductValidator();
        Product product = productValidator.validate(request);

        ProductService productService = factory.getService(ProductService.class);
        productService.create(product);
        request.setAttribute("messageType", InfoMessage.SUCCESS_TYPE);
        request.setAttribute("message", "admin.created.product.message");
        return new Forward("/admin/main.jsp", false);
    }
}
