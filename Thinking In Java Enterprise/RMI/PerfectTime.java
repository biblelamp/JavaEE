import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;

public class PerfectTime extends UnicastRemoteObject implements PerfectTimeI {

    // The implementation of the interface
    public long getPerfectTime() throws RemoteException {
        return System.currentTimeMillis();
    }

    // Requires the constructor which throws an exception
    public PerfectTime() throws RemoteException {
    }

    // Registration for RMI service
    public static void main(String[] args) throws Exception {
        //System.setSecurityManager(new RMISecurityManager());
        PerfectTime pt = new PerfectTime();
        Naming.bind("PerfectTime", pt);
        System.out.println("Ready to do time");
   }
}