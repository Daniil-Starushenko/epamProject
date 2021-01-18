package by.daniil.epam.project.service;

import by.daniil.epam.project.dao.mysql.TransactionFactoryImpl;
import by.daniil.epam.project.dao.transaction.Transaction;
import by.daniil.epam.project.dao.transaction.TransactionFactory;
import by.daniil.epam.project.exception.PersistentException;

import by.daniil.epam.project.service.interceptor.ServiceInvocationHandlerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ServiceFactoryImpl implements ServiceFactory {
    private  static Logger logger = LogManager.getLogger(ServiceFactoryImpl.class);

    private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(UserService.class, UserServiceImpl.class);
        SERVICES.put(ProductService.class, ProductServiceImpl.class);
    }

    private TransactionFactory factory;

    public ServiceFactoryImpl(TransactionFactory transactionFactory){
        factory = transactionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Type extends Service> Type getService(Class<Type> key) throws PersistentException {
        Class<? extends ServiceImpl> value = SERVICES.get(key);
        if(value != null) {
            try {
                ClassLoader classLoader = value.getClassLoader();
                Class<?>[] interfaces = {key};
                Transaction transaction = factory.createTransaction();
                ServiceImpl service = value.newInstance();
                service.setTransaction(transaction);
                InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
                return (Type)Proxy.newProxyInstance(classLoader, interfaces, handler);
            } catch(PersistentException e) {
                throw e;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("It is impossible to instance service class", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void close() {

    }
}
