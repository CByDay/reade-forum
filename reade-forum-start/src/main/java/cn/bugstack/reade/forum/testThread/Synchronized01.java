package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/13
 * @Description: 锁
 */
public class Synchronized01 {

    public static void main(String[] args) {
        Object lock=new Object();
////设置外部锁 set注入方式
//        SellTicket03.setOutObj(lock);
        //下面是构造注入同一把锁
        // Thread 方式
        SellTicket03 sellTicket01 = new SellTicket03(lock);
        SellTicket03 sellTicket02 = new SellTicket03(lock);
        SellTicket03 sellTicket03 = new SellTicket03(lock);
        // 开始运行，会发现售票数出现负数，也就是超卖了；
        sellTicket01.start();
        sellTicket02.start();
        sellTicket03.start();

        // Runnable 方式
//        SellTicket04 sellTicket04 = new SellTicket04();
//        new Thread(sellTicket04).start();
//        new Thread(sellTicket04).start();
//        new Thread(sellTicket04).start();
    }
}

// 继承Thread 方式
class SellTicket03 extends Thread {
    //锁外部对象
    private static Object outObj;
    private  Object outObj2;
    public SellTicket03(Object lock){
        outObj2=lock;
    }

    public SellTicket03(){

    }

    public static void setOutObj(Object outObj) {
        SellTicket03.outObj = outObj;
    }

    private static int ticketNum = 300; // 多个线程共享 票数

    @Override
    public void run() { // 重写 run 方法， 写上自己的业务逻辑
        while (true) {
            synchronized (outObj2) {
                if (ticketNum <= 0) {
                    System.out.println("售票结束。。。");
                    break;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票"
                        + " 剩余票数= " + (--ticketNum));
            }
        }
    }
}

// 实现 Runnable 接口方式
class SellTicket04 implements Runnable {
    private int ticketNum = 300;
    private boolean flag = true;

    public  void sell() {
        synchronized (this) {
            if (ticketNum <= 0) {
                System.out.println("售票结束。。。");
                flag = false;
                return;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票"
                    + " 剩余票数= " + (--ticketNum));
        }
    }

    @Override
    public void run() {
        while (flag) {
            sell();
        }
    }
}




