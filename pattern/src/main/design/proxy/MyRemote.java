package proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author:xie-super
 * Time:2023/12/27
 * Name:IntelliJ IDEA
 */
public interface MyRemote extends Remote {
    public String sayHello() throws RemoteException;
}
