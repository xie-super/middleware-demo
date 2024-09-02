package proxy;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Author:xie-super
 * Time:2023/12/27
 * Name:IntelliJ IDEA
 */

class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    public MyRemoteImpl() throws RemoteException {
    }
    @Override
    public String sayHello() throws RemoteException {
        return "Server say hello";
    }
    public static void main(String[] args) {
        try {
            MyRemote service = new MyRemoteImpl();
            Naming.rebind("RemoteHello", service);
            System.out.println("Server is ready.");
            Thread.sleep(Long.MAX_VALUE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

