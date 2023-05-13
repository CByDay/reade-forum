package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/12
 * @Description: 线程退出
 */
public class ThreadExit {

    public static void main(String[] args) throws InterruptedException {

        T t = new T();
        new Thread(t).start();

        // 主线程 休眠10秒
        Thread.sleep(10 * 1000);
        // 控制 子线程的终止；此种方式称为通知方式
        t.setFlag(false);
    }
}

class T implements Runnable{

    private static int nums= 0;

    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            System.out.println("线程名字：" + Thread.currentThread().getName() + "运行了多少次= " + (++nums) );
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
