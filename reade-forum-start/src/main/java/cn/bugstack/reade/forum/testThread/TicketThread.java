package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/12
 * @Description: 售票
 */
public class TicketThread {

    public static void main(String[] args) {

        // Thread 方式
//        SellTicket01 sellTicket01 = new SellTicket01();
//        SellTicket01 sellTicket02 = new SellTicket01();
//        SellTicket01 sellTicket03 = new SellTicket01();
//        // 开始运行，会发现售票数出现负数，也就是超卖了；
//        sellTicket01.start();
//        sellTicket02.start();
//        sellTicket03.start();

        // Runnable 方式
        SellTicket02 sellTicket02 = new SellTicket02();
        Thread thread01 = new Thread(sellTicket02);
        Thread thread02 = new Thread(sellTicket02);
        Thread thread03 = new Thread(sellTicket02);

        thread01.start();
        thread02.start();
        thread03.start();

    }
}

// 继承Thread 方式
class SellTicket01 extends Thread{
    private static int ticketNum = 100; // 多个线程共享 票数

    @Override
    public void run() { // 重写 run 方法， 写上自己的业务逻辑
        while (true) {

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

// 实现 Runnable 接口方式
class SellTicket02 implements Runnable{
    private int ticketNum = 100;

    @Override
    public void run() {
        while (true) {

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
