/**
 * Author:xie-super
 * Time:2024/3/4
 * Name:IntelliJ IDEA
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {
        Message message = new Message();

        Thread producerThread = new Thread(new Producer(message));
        Thread consumerThread = new Thread(new Consumer(message));

        producerThread.start();
        consumerThread.start();
    }
}

class Message {
    private String message;
    private boolean empty = true;

    public synchronized String read() {
        while (empty) {
            try {
                wait(); // 如果消息为空，等待生产者生产消息
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        empty = true;
        notifyAll(); // 唤醒等待中的生产者线程
        return message;
    }

    public synchronized void write(String message) {
        while (!empty) {
            try {
                wait(); // 如果消息不为空，等待消费者消费消息
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        empty = false;
        this.message = message;
        notifyAll(); // 唤醒等待中的消费者线程
    }
}

class Producer implements Runnable {
    private Message message;

    public Producer(Message message) {
        this.message = message;
    }

    public void run() {
        String[] messages = {"Hello", "World", "From", "Producer"};
        for (String msg : messages) {
            message.write(msg);
            System.out.println("Produced: " + msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private Message message;

    public Consumer(Message message) {
        this.message = message;
    }

    public void run() {
        for (int i = 0; i < 4; i++) {
            String msg = message.read();
            System.out.println("Consumed: " + msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
