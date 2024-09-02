package thread;

/**
 * Author:xie-super
 * Time:2024/4/14
 * Name:IntelliJ IDEA
 */
//死锁 demo
public class DeadlockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        Runnable runnable1 = new Runnable(){

            @Override
            public void run() {
                synchronized (lock1){
                    System.out.println(Thread.currentThread()+"get resource 1");
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource2");
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread() + "get resource2");
                    }

                }
            }
        };
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                synchronized (lock2){
                    System.out.println(Thread.currentThread()+"get resource 2");
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource1");
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread() + "get resource1");
                    }

                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }
}
