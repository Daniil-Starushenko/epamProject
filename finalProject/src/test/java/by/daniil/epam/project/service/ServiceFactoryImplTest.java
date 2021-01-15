package by.daniil.epam.project.service;

import by.daniil.epam.project.controller.DispatcherServlet;
import by.daniil.epam.project.dao.transaction.TransactionFactory;
import by.daniil.epam.project.exception.PersistentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactoryImplTest {
    private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(UserService.class, UserServiceImpl.class);
    }

    private DispatcherServlet dispatcherServlet;
    private ServiceFactory serviceFactory;
    private TransactionFactory factory;
    private UserService userService = new UserServiceImpl();

    @BeforeMethod
    public void setUp() throws PersistentException {
        dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();
        serviceFactory = dispatcherServlet.getFactory();
    }

    @AfterMethod
    public void tearDown() throws PersistentException{
       userService = serviceFactory.getService(UserServiceImpl.class);
    }

    @Test
    public void testGetService() throws PersistentException{
        serviceFactory.getService(UserServiceImpl.class);
    }
}