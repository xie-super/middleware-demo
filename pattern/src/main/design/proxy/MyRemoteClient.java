package proxy;

import java.rmi.*;

/**
 * Author:xie-super
 * Time:2023/12/27
 * Name:IntelliJ IDEA
 */
public class MyRemoteClient {
    public static void main(String[] args) {
        new MyRemoteClient().go();
    }
    public void go() {
        try {
            MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
            String s = service.sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
