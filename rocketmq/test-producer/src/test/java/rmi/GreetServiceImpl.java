package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GreetServiceImpl extends UnicastRemoteObject implements GreetService {
    protected GreetServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String str) throws RemoteException {
        return "hello" + str +", this is a test!!!";
    }
}
