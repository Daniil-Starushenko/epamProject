package by.daniil.epam.project.action.admin;

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
        //TODO: add photo to mySql
        product.setPngPath("photo");
        //
        ProductService productService = factory.getService(ProductService.class);
        productService.create(product);
        return new Forward("/admin/main.html");
    }
}
