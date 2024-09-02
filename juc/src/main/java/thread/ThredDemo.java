package thread;

/**
 * Author:xie-super
 * Time:2024/4/2
 * Name:IntelliJ IDEA
 */

public class ThredDemo {
    private static Object lock = new Object();
    private static int count = 0;
    static class OddPrint implements Runnable{
/*
* */
        @Override
        public void run() {
            while(count<100){
                synchronized (lock) {
                    if(count%2 == 1){
                        System.out.println("ODD:"+count++);
                        lock.notify();
                    }else{
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    static class EvenPrint implements Runnable{
        @Override
        public void run() {
            while(count<100){
                synchronized (lock) {
                    if(count%2 == 0){
                        System.out.println("EVEN:"+count++);
                        lock.notify();
                    }else{
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread thread1 = new Thread(new OddPrint());
        Thread thread2 = new Thread(new EvenPrint());
        thread1.start();
        thread2.start();
    }
}
