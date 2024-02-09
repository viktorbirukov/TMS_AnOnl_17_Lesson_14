package lesson_14_1;


public class Potok {
    public static void main(String[] args) {
        Thread t3 = new Thread(new MyRunnable("T3"));
        Thread t2 = new Thread(new MyRunnable("T2", t3));
        Thread t1 = new Thread(new MyRunnable("T1", t2));

        t1.start();
    }
}

class MyRunnable implements Runnable {
    private String name;
    private Thread nextThread; // Ссылка на следующий поток

    public MyRunnable(String name) {
        this.name = name;
    }

    public MyRunnable(String name, Thread nextThread) {
        this.name = name;
        this.nextThread = nextThread;
    }

    @Override
    public void run() {
        if (nextThread != null) {
            try {
                nextThread.join(); // Дождаться завершения следующего потока
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " запущен");
        // Код, выполняемый потоком
        try {
            Thread.sleep(1000); // Имитация выполнения задачи
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " завершен");
    }
}