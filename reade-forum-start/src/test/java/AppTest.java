import cn.bugstack.reade.forum.StartApplication;
import cn.bugstack.reade.forum.streamTest.TestValue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
@SpringBootTest(classes = StartApplication.class)
public class AppTest {

    @Resource
    TestValue testValue;
    @Test

    public void test(){
        System.out.println(testValue);
    }

}
