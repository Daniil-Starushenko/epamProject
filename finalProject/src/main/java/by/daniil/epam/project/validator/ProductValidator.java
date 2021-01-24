package by.daniil.epam.project.validator;

import by.daniil.epam.project.domain.Product;

import javax.servlet.http.HttpServletRequest;

public class ProductValidator implements Validator<Product> {
    @Override
    public Product validate(HttpServletRequest request) {
        Product product = new Product();
        if (request.getParameter("EditProductName") != null) {
            product.setProductName(request.getParameter("EditProductName"));
        }
        if (request.getParameter("description") != null) {
            product.setDefinition(request.getParameter("description"));
        }
        String param = request.getParameter("editPrice");
        if (param != null) {
            Double price = Double.valueOf(param);
            product.setPrice(price);
        }
        return product;
    }
}
