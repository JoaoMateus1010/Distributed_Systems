import java.io.*;
import java.nio.channels.FileChannel;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
public class Servente extends UnicastRemoteObject implements RemoteInterface{

    public Servente() throws RemoteException{
        super();
    }
    @Override
    public String getStatus() throws RemoteException {
        return "Ok";
    }

    @Override
    public File[] getListFiles(String Name) throws RemoteException {
        File files = new File(Name);
        File listFiles[] = files.listFiles();
        return listFiles;
    }

    @Override
    public boolean cpFileServer(String Name) throws RemoteException {
        boolean ret = false;

        String dirOrigem = "FILES_SERVER/";
        String dirDestino = "FILES_CLIENT/";

        FileChannel channelDestination = null;
        FileChannel channelSource = null;
        try{
            channelSource = new FileInputStream(dirOrigem+Name).getChannel();
            channelDestination = new FileOutputStream(dirDestino+Name).getChannel();
            channelSource.transferTo(0,channelSource.size(),channelDestination);
            if((channelSource != null)&&(channelSource.isOpen())){
                channelSource.close();
            }
            if((channelDestination != null)&&(channelDestination.isOpen())){
                channelDestination.close();
            }
            ret=true;
        }catch (IOException e){
            System.out.println("Houve um erro ao transferir o arquivo");
        }
        return ret;
    }
}
