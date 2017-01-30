import java.rmi.*;
import java.rmi.registry.*;

public class DisplayPerfectTime {

    public static void main(String[] args) throws Exception {
        //System.setSecurityManager(new RMISecurityManager());
        PerfectTimeI t = (PerfectTimeI) Naming.lookup("rmi://localhost/PerfectTime");
        for (int i = 0; i < 10; i++)
            System.out.println("Perfect time = " + t.getPerfectTime());
   }
}