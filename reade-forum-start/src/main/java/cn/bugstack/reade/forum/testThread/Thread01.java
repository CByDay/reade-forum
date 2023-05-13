package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/11
 * @Description: Thread 基础
 */
public class Thread01 {
    public static void main(String[] args) throws InterruptedException {

        // 在 主线程 中开启 子线程
        // 创建的 Cat 对象， 可以当作线程使用
        Cat cat = new Cat();

        /*
         start() 源码：
         1.
         public synchronized void start() {
            start0(); // 核心方法
         }

         2. start0() 是本地方法， 是 JVM 调用；底层是 C/C++ 实现
            真正实现 多线程 效果的是， start0() ，而不是 run()
            理解：start0() 里面 它用多线程的这种机制 来调用我们的 run() 方法
         private native void start0();

         3. start() 方法调用 start0() 方法后，该线程并不会马上执行，只是将线程变成了可运行状态。具体什么时候执行，取决于 CPU，由CPU统一调度
         */
        cat.start(); // 启动线程

        // 注：当 main 线程启动 一个子线程 Cat ，主线程不会阻塞，会继续执行
        // 此时 主线程 和 子线程 呈现 交替执行的状态
        // 理解：
        // 1.启动 Thread01 相当于启动一个 Application（进程）
        // 2.在此进程中 开启了一个 主线程，名字叫 main
        // 3.在这个 main 线程 中开启了另一个 线程， 名叫 Thread-0
        // 4.当 main 线程执行完毕后，并不会影响到 Thread-0 线程的执行，当 Thread-0 结束后，整个进程才结束
        // 总结：在多线程编程里面，主线程结束了， 并不会造成 这个进程（应用程序）的结束，如果还有其 他子线程 在运行，会继续执行，直至全部执行完毕

        System.out.println("主线程继续执行，线程名字：" + Thread.currentThread().getName());
        for (int i = 0; i < 60; i++) {
            System.out.println("主线程 i= " + i);
            // 主线程休眠 1 秒
            Thread.sleep(1000);
        }
    }
}

// 1. 当一个类继承了 Thread 类，该类就可以当做一个线程使用
// 2. 我们会重写 run 方法，写上自己的业务代码
// 3. run Thread 类 实现了 Runnable 接口的 run 方法，Thread 类里面本身没有run 方法，如下：
    /*
        @Override
        public void run() { //
            super.run();
        }
     */
class Cat extends Thread {

    int times = 0;

    @Override
    public void run() { // 重写 run 方法， 写上自己的业务逻辑
        while (true) {
            System.out.println("喵喵，我是小猫咪" + (++times) + "线程名字：" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (times == 80) { // times == 80, 退出 while 循环 ，此时线程也就退出
                break;
            }
        }

        // 在 子线程中 开启 线程
        Dog dog = new Dog();
        dog.start();
    }
}

class Dog extends Thread {
    int nums = 0;

    @Override
    public void run() { // 重写 run 方法， 写上自己的业务逻辑
        while (true) {
            System.out.println("喵喵，我是小猫咪" + (++nums) + "线程名字：" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (nums == 60) { // times == 80, 退出 while 循环 ，此时线程也就退出
                break;
            }
        }
    }
}
