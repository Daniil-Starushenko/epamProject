package by.daniil.epam.project.action.user;

import by.daniil.epam.project.action.Action;
import by.daniil.epam.project.domain.Role;

public abstract class UserAction extends Action {
    public UserAction() {
        getAllowRoles().add(Role.USER);
    }
}
