package by.daniil.epam.project.action.deliveryman;

import by.daniil.epam.project.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainDeliveryManAction extends DeliverymanAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        return new Forward("/deliveryman/mainDeliveryman.jsp", false);
    }
}
