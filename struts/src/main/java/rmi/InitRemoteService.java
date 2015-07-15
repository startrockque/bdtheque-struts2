package rmi;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Fabien on 14/07/2015.
 */
public class InitRemoteService implements ServletContextListener {

    private static boolean isRegistered = false;
    private static RMIService service;

    public void contextInitialized(ServletContextEvent sce) {
        if(!isRegistered){
            try {
                String path = sce.getServletContext().getRealPath("/");
                service = new RMIServiceImpl(path);
                RMIService stub = (RMIService) UnicastRemoteObject.exportObject(service, 0);
                Registry registry = LocateRegistry.createRegistry(9345);
                registry.rebind(RMIService.SERVICE_NAME, stub);
                isRegistered = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    public static RMIService getService() {
        return service;
    }
    public InitRemoteService(){

    }
}
