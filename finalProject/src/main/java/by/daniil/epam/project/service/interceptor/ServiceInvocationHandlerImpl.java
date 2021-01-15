package by.daniil.epam.project.service.interceptor;

import by.daniil.epam.project.exception.PersistentException;
import by.daniil.epam.project.service.ServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceInvocationHandlerImpl implements InvocationHandler {
    private static Logger logger = (Logger) LogManager.getLogger(ServiceInvocationHandlerImpl.class);

    private ServiceImpl service;


    public ServiceInvocationHandlerImpl(ServiceImpl service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object result = method.invoke(service, args);
            service.transaction.commit();
            return result;
        } catch(PersistentException e) {
            rollback(method);
            throw e;
        } catch(InvocationTargetException e) {
            rollback(method);
            throw e.getCause();
        }
    }

    private void rollback(Method method) {
        try {
            service.transaction.rollback();
        } catch(PersistentException e) {
            logger.warn("It is impossible to rollback transaction", e);
        }
    }
}
