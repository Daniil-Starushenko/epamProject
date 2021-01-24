package by.daniil.epam.project.action.admin;

import by.daniil.epam.project.action.Action;
import by.daniil.epam.project.domain.Role;

public abstract class AdminAction extends Action {
    public AdminAction() {
        getAllowRoles().add(Role.ADMINISTRATOR);
    }
}
