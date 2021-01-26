package by.daniil.epam.project.action.deliveryman;

import by.daniil.epam.project.action.Action;
import by.daniil.epam.project.domain.Role;

public abstract class DeliverymanAction extends Action {
    public DeliverymanAction() { getAllowRoles().add(Role.DELIVERY_MAN); }
}
