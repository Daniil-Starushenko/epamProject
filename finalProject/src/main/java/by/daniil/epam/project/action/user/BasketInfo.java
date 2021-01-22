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

public class BasketInfo extends UserAction {
    Logger logger = LogManager.getLogger(BasketInfo.class);

    private List<Product> basket = new ArrayList<>();
    private Product product = new Product();


    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession userSession = request.getSession(false);
        String id = (String)request.getParameter("identityToDelete");
        if (id != null) {
            ProductService service = factory.getService(ProductService.class);
            int productId = Integer.parseInt(id);
            product = service.findById(productId);
            basket = (List<Product>)userSession.getAttribute("basket");
            basket.remove(product);
            userSession.setAttribute("basket", basket);
        }
        basket = (List<Product>)userSession.getAttribute("basket");
        if (basket.size() != 0) {
            request.setAttribute("basketList", basket);
            double totalPrice = calculateTotalPrice(basket);
            request.setAttribute("totalPrice", totalPrice);
            return new Forward("/user/basket.jsp", false);
        } else {
        return new Forward("/user/basket.jsp", false);
        }
    }

    public double calculateTotalPrice(List<Product> basket) {
        double totalPrice = 0;
        for (int i = 0; i < basket.size(); i++) {
            totalPrice += basket.get(i).getPrice();
        }
        return totalPrice;
    }
}
