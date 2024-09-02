package thread;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

 class MyCallable implements Callable<String> {
     private String taskName;

     public MyCallable(String taskName) {
         this.taskName = taskName;
     }

     public String call() throws Exception {
         // 模拟任务执行，这里只是简单地返回任务名称
         Thread.sleep(2000); // 模拟耗时操作
         return "Result of " + taskName;
     }
}


public class MyCallableMain {
    public static void main(String[] args) {
        // 创建固定大小的线程池，大小为 3
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 提交 Callable 任务
        Future<String> future1 = executor.submit(new MyCallable("Task 1"));
        Future<String> future2 = executor.submit(new MyCallable("Task 2"));
        Future<String> future3 = executor.submit(new MyCallable("Task 3"));

        // 获取任务执行结果
        try {
            String result1 = future1.get();
            System.out.println("Result of Task 1: " + result1);

            String result2 = future2.get();
            System.out.println("Result of Task 2: " + result2);

            String result3 = future3.get();
            System.out.println("Result of Task 3: " + result3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executor.shutdown();
    }
}