import java.io.File;
import java.rmi.*;
public interface RemoteInterface extends Remote{
    public String getStatus() throws RemoteException;
    public File[] getListFiles(String Name) throws RemoteException;
    public boolean cpFileServer(String Name) throws RemoteException;
}
