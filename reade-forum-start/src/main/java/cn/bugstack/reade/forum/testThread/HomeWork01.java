package cn.bugstack.reade.forum.testThread;

import java.util.Scanner;

/**
 * @Author zhd
 * @Date 2023/5/14
 * @Description:
 */
public class HomeWork01 {

    public static void main(String[] args) {
        AThread aThread = new AThread();
        BThread bThread = new BThread(aThread);

        Thread threadA = new Thread(aThread);
        Thread threadB = new Thread(bThread);
        threadA.setName("A线程");
        threadB.setName("B线程");

        threadA.start();
        threadB.start();

    }
}

class AThread implements Runnable {

    // 设置控制 变量
    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println("flag= " + flag);
        while (flag) {
            System.out.println((int) (Math.random() * 100 + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class BThread implements Runnable {
    // A 对象
    private AThread aThread;

    //
    private Scanner scanner = new Scanner(System.in);


    // 构造方法注入，传入A 类对象
    public BThread(AThread aThread) {
        this.aThread = aThread;
    }

    @Override
    public void run() {
        while (true) {
            // 接收用户的输入
            System.out.println("请输入大写字母 Q 终止A线程");
            char key = scanner.next().toUpperCase().charAt(0);
            if (key == 'Q') {
                // 已通知的方式结束 A线程
                // 修改 A线程中的控制变量
                aThread.setFlag(false);
                System.out.println(Thread.currentThread().getName()+"退出");
                break;
            }
        }
    }
}