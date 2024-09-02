/**
 * ReentrantLock的选择性通知
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SelectiveNotificationExample {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile boolean conditionMet = false;

    public void waitForCondition() throws InterruptedException {
        lock.lock();
        try {
            while (!conditionMet) {
                condition.await(); // 等待条件满足
            }
            // 执行需要在条件满足时执行的代码
            System.out.println("Condition is met, proceeding...");
        } finally {
            lock.unlock();
        }
    }

    public void notifyConditionMet() {
        lock.lock();
        try {
            conditionMet = true;
            // 满足条件后，发出通知
            condition.signal(); // 通知等待在条件上的一个线程
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SelectiveNotificationExample example = new SelectiveNotificationExample();

        // 创建一个等待条件的线程
        Thread waitingThread = new Thread(() -> {
            try {
                System.out.println("Waiting for condition...");
                example.waitForCondition();
                System.out.println("Condition is met, continuing...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 创建一个触发条件的线程
        Thread notifyingThread = new Thread(() -> {
            try {
                Thread.sleep(2000); // 模拟一些操作
                System.out.println("Notifying condition is met...");
                example.notifyConditionMet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        waitingThread.start();
        notifyingThread.start();
    }
}
