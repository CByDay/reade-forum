package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/12
 * @Description: 线程方法第二组
 */
public class ThreadMethod02 {

    public static void main(String[] args) throws InterruptedException {
        Method02 method02 =new Method02();
        method02.start();

        for (int i = 0; i < 20; i++) {
            Thread.sleep(50);
            System.out.println("小疯子" + i);
            if(i == 5) {
                System.out.println("让 子线程 先运行");
                // join
                method02.join();
            }
        }

        // yield
        // Thread.yield();
    }
}

class Method02 extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("JoinThread-------" + i);
        }
    }
}
