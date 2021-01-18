package by.daniil.epam.project.action;

import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionManagerImpl implements ActionManager {
    private ServiceFactory serviceFactory;

    public ActionManagerImpl(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        action.setFactory(serviceFactory);
        return action.exec(request, response);
    }

    @Override
    public void close() {

    }
}
