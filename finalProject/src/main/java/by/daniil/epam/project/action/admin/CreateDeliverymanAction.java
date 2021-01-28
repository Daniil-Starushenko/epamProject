package by.daniil.epam.project.action.admin;

import by.daniil.epam.project.domain.DeliveryMan;
import by.daniil.epam.project.domain.Role;
import by.daniil.epam.project.domain.User;
import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.DeliverymanService;
import by.daniil.epam.project.service.UserService;
import by.daniil.epam.project.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDeliverymanAction extends AdminAction {
    Logger logger = LogManager.getLogger(CreateDeliverymanAction.class);
    //TODO: проверки
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        UserValidator userValidator = new UserValidator();
        User user = userValidator.validate(request);
        user.setRole(Role.DELIVERY_MAN);

        UserService userService = factory.getService(UserService.class);
        int id = userService.create(user);
        user.setIdentity(id);
        DeliverymanService deliveryManService = factory.getService(DeliverymanService.class);
        DeliveryMan deliveryMan = new DeliveryMan();
        deliveryMan.setUser(user);
        deliveryMan.setPhoneNumber(request.getParameter("telNumber"));
        deliveryManService.create(deliveryMan);

        return new Forward("/admin/main.html");
    }
}
