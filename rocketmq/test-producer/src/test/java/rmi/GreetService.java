package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GreetService extends Remote {
    String sayHello(String str) throws RemoteException;
}
