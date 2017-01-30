import java.rmi.*;

public interface PerfectTimeI extends Remote {
    long getPerfectTime() throws RemoteException;
}