package thread;

/**
 * Author:xie-super
 * Time:2024/3/5
 * Name:IntelliJ IDEA
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;

public class CustomThreadPoolExample {
    public static void main(String[] args) {
        // 创建线程池
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        ExecutorService executor = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, unit,
                new ArrayBlockingQueue<>(2)); // 使用有界队列

        // 提交任务到线程池
        for (int i = 1; i <= 5; i++) {
            Task task = new Task("Task " + i);
            System.out.println("提交任务: " + task.getName());
            executor.submit(task);
        }

        // 关闭线程池
        executor.shutdown();
    }
}

// 任务类
class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 执行任务: " + name);
        try {
            Thread.sleep(2000); // 模拟任务执行时间
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " 完成任务: " + name);
    }
}
