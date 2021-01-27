package by.daniil.epam.project.action.deliveryman;

import by.daniil.epam.project.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTokenOrdersAction extends DeliverymanAction {
    Logger logger = LogManager.getLogger(ShowTokenOrdersAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {


        return null;
    }
}
