package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/14
 * @Description: 互斥锁练习
 */
public class HomeWork02 {

    public static void main(String[] args) {
        GetMoney getMoney = new GetMoney();
        new Thread(getMoney).start();
        new Thread(getMoney).start();
        new Thread(getMoney).start();
        new Thread(getMoney).start();

    }
}

class GetMoney implements Runnable {

    private int total = 10000;
    private boolean flag = true;

    public void getTotal() {
        // 互斥锁
        synchronized (this) {
            if (total <= 100) {
                System.out.println("余额不足...");
                flag = false;
                return;
            }
            total -= 300;
            System.out.println(Thread.currentThread().getName() + "取走了300，余额是 " + total);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        while (flag) {
            getTotal();
        }
    }
}


