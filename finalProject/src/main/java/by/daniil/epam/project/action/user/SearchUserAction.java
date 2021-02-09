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

public class SearchUserAction  extends UserAction {
    Logger logger = LogManager.getLogger(SearchUserAction.class);
    private List<Product> basket;
    private Product productToAdd;

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        String currentPageParameter = request.getParameter("currentPage");
        Integer pageSize = (Integer) request.getAttribute("pageSize");

        int currentPage = 1;
        if (currentPageParameter != null) {
            currentPage = Integer.parseInt(currentPageParameter);
        }
        if (pageSize == null) {
            pageSize = 3;
        }

        HttpSession userSession = request.getSession(false);
        if (userSession.getAttribute("basket") == null && userSession.getAttribute("productToAdd") == null) {
            basket = new ArrayList<>();
            productToAdd = new Product();
            userSession.setAttribute("basket", basket);
            userSession.setAttribute("productToAdd", productToAdd);
        }

        int offset = pageSize * (currentPage - 1);
        ProductService service = factory.getService(ProductService.class);
        List<Product> products = service.findLimitedNumberOfProducts(pageSize, offset);
        int totalRecords = service.countNumberOfRows();
        int pages = totalRecords / pageSize;
        int lastPage = pages * pageSize < totalRecords ? pages + 1 : pages;
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lastPage", lastPage);
        if (!products.isEmpty()) {
            request.setAttribute("products", products);
            return new Forward("/user/search.jsp", false);
        } else {
            request.setAttribute("message", "нет продуктов");
            logger.info(String.format("access was failed"));
        }
        return null;
    }
}
