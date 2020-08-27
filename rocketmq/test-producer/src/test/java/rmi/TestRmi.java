package rmi;

import org.junit.Test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TestRmi {
    @Test
    public void testCreateRmi() throws Exception {
        LocateRegistry.createRegistry(1098);
        Naming.bind("rmi://127.0.0.1:1098/GreetService", new GreetServiceImpl());
        while (true){}
    }

    @Test
    public void testRmi() throws Exception {
        GreetService  greetService  =  (GreetService) Naming.lookup("rmi://127.0.0.1:1098:1098/GreetService");
        System.out.println(greetService.sayHello("Jobs"));
    }
}
