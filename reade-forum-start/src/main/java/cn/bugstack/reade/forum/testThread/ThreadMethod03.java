package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/12
 * @Description: 线程方法第三组
 */
public class ThreadMethod03 {
    public static void main(String[] args) throws InterruptedException {
        Method03 method03 = new Method03();
        // 如果想主线程结束后，子线程自动结束，只需将子线程设置成守护线程即可，如下：
        method03.setDaemon(true);
        method03.start(); // 启动子线程



        // 主线程
        for (int i = 0; i < 10; i++) {
            System.out.println("沸羊羊在辛苦工作。。。");
            Thread.sleep(1000);
        }
    }
}

class Method03 extends Thread {
    @Override
    public void run() {
        for (; ; ) { // 这种写法 无限循环
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("喜羊羊和美羊羊再聊天~~~");
        }
    }
}
