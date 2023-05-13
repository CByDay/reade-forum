package cn.bugstack.reade.forum.testThread;

/**
 * @Author zhd
 * @Date 2023/5/12
 * @Description:
 */
public class Runnable01 {

    public static void main(String[] args) {
        Sheep sheep = new Sheep();
        // 实现 Runnable 接口的线程 不能通过 直接调用 start() 的方式。 sheep.start() 是错误的
        // 必须使用 如下这种模式，这种模式称为 （静态）代理模式
        Thread thread  = new Thread(sheep);
        thread.start();

    }
}

// 实现 Runnable 接口
class Sheep implements Runnable{
    @Override
    public void run() {

        int count = 0;

        while (true){
            System.out.println("小羊，咩咩咩。。。" + (++count) +"线程名称："+ Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(count == 10){
                break;
            }
        }
    }
}
