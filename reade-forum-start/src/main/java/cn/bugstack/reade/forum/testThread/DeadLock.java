package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/13
 * @Description: 模拟线程死锁
 */
public class DeadLock {

    public static void main(String[] args) {
        DeadLock01 deadLock01 = new DeadLock01(true);
        DeadLock01 deadLock02 = new DeadLock01(false);
        deadLock01.setName("A线程");
        deadLock02.setName("B线程");
        deadLock01.start();
        deadLock02.start();
    }
}

class DeadLock01 extends Thread {

    static Object object01 = new Object(); // 保证多个线程共享一个对象，这里使用 static
    static Object object02 = new Object();

    boolean flag;

    public DeadLock01(boolean flag){ // 构造器
        this.flag = flag;
    }

    public void run(){

        // 逻辑分析：
        // 1. 如果 flag 为 true ，线程A会先得到/持有 object01 对象锁，然后尝试去获取 object02 的对象锁
        // 2. 如果线程A 拿不到 object02 对象锁， 就会 Blocked
        // 3. 如果 flag 为 false ，线程B会先得到/持有 object02 对象锁，然后尝试去获取 object01 的对象锁
        // 4. 如果线程B 拿不到 object01 对象锁， 就会 Blocked

        if(flag){
           synchronized (object01){ // 添加了对象互斥锁，所以下面的就是同步代码
               System.out.println(Thread.currentThread().getName() + " 进入1"); // 拿不到 object02 对象锁 ，就会阻塞在这里无法往下继续执行
               synchronized (object02){
                   System.out.println(Thread.currentThread().getName() + " 进入2");
               }
           }
        }else {
            synchronized (object02){
                System.out.println(Thread.currentThread().getName() + " 进入3"); // 拿不到 object01 对象锁 ，就会阻塞在这里无法往下继续执行
                synchronized (object01){
                    System.out.println(Thread.currentThread().getName() + " 进入4");
                }
            }
        }
    }
}
