package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/12
 * @Description: 线程方法第一组
 */
public class ThreadMethod01 {

    public static void main(String[] args) throws InterruptedException {
        Method01 method01 = new Method01();
        method01.setName("小疯子"); // 设置线程名称
        method01.setPriority(Thread.MIN_PRIORITY); // 设置线程优先级
        method01.start(); // 启动

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("hi " + i);
        }

        method01.interrupt(); // 中断休眠
    }
}

class Method01 extends Thread {

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " 吃包子。。。。" + i);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "休眠中断，被 interrupt");
            }
        }
    }
}