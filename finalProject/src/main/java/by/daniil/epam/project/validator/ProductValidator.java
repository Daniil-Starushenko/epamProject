package by.daniil.epam.project.validator;

import by.daniil.epam.project.domain.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

public class ProductValidator implements Validator<Product> {
    Logger logger = LogManager.getLogger(ProductValidator.class);
    //TODO: loggers

    @Override
    public Product validate(HttpServletRequest request) {
        Product product = new Product();

        if (request.getParameter("EditProductName") != null) {
            product.setProductName(request.getParameter("EditProductName"));
        }

        String imageAbsoluteName = getAbsolutePath(request);
        product.setPngPath(imageAbsoluteName);

        if (request.getParameter("description") != null) {
            product.setDefinition(request.getParameter("description"));
        }

        String param = request.getParameter("editPrice");

        try {
            Double price = Double.valueOf(param.replace(",", "."));
            product.setPrice(price);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "incorrect input of price");
            return null;
        }


        return product;
    }

    private String getAbsolutePath(HttpServletRequest request) {
        String userName = System.getProperty("user.name");
        File path = new File(File.separator
                + "Users"
                + File.separator
                + userName
                + File.separator
                + "photos");
        if (!path.exists()) {
            path.mkdir();
        }

        String imageAbsoluteName = path.getAbsolutePath()
                + File.separator
                + UUID.randomUUID().toString().concat(".jpg");
        File image = new File(imageAbsoluteName);

        try (InputStream fileContent = request.getPart("pngPath").getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(image)) {
            fileOutputStream.write(fileContent.readAllBytes());
            fileOutputStream.flush();
        } catch (Exception e) {
            request.setAttribute("message", "The attempt of loading image was failed");
            return null;
        }
        return imageAbsoluteName;
    }
}
