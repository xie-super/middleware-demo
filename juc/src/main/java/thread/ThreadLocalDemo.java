package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Author:xie-super
 * Time:2024/3/5
 * Name:IntelliJ IDEA
 */
public class ThreadLocalDemo implements Runnable{
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return new SimpleDateFormat("yyyyMMdd HHmm").format(new Date()).toString();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo obj = new ThreadLocalDemo();
        for(int i = 0; i<5; i++){
            Thread t = new Thread(obj, ""+i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();

        }
    }

    @Override
    public void run() {
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" default Formatter = "+threadLocal.get());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        threadLocal.set(new SimpleDateFormat("yyyyMMdd").format(new Date()).toString());

        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+threadLocal.get());

    }
}
