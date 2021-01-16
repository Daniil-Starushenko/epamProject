package by.daniil.epam.project.action;

import by.daniil.epam.project.domain.Role;
import java.util.Arrays;

public abstract class AuthorizedUserAction extends Action {
    public AuthorizedUserAction() {
        getAllowRoles().addAll(Arrays.asList(Role.values()));
    }
}
