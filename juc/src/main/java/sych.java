/**
 * Author:xie-super
 * Time:2024/3/13
 * Name:IntelliJ IDEA
 */
class  Counter {
    private static int count = 0;

    public synchronized static void increment() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}
public class sych {

    public static void main(String[] args) {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter1.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter2.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter count: " + Counter.getCount());
    }
}

