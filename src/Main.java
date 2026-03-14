class MyTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread is running using Runnable interface");
    }
}

public class GFG{

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task);
        t.start();
    }
}