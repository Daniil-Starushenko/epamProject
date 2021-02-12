package by.daniil.epam.project.validator;

import by.daniil.epam.project.domain.Entity;
import by.daniil.epam.project.domain.Order;

import javax.servlet.http.HttpServletRequest;

public class OrderValidator implements Validator<Order> {
    @Override
    public Order validate(HttpServletRequest request) {
        Order order = new Order();
        String parameter = request.getParameter("telNumber");
        if (complexValidator.isValidNumber(parameter)) {
           order.setPhoneNumber(parameter);
           return order;
        }
        return null;
    }
}
