import cn.bugstack.reade.forum.StartApplication;
import cn.bugstack.reade.forum.streamtest.TestValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
@SpringBootTest(classes = StartApplication.class)
class AppTest {

    @Resource
    TestValue testValue;

    @Resource
    StringRedisTemplate template;

    @Test
    @DisplayName("redisTemp")
    void redisTemp() {
        template.opsForValue().set("a", "888");
        template.opsForValue().set("b", "999");
        System.out.println(template.opsForValue().get("a"));
        template.delete("a");
        template.hasKey("a");
        System.out.println(template.hasKey("a"));
        System.out.println(Boolean.TRUE.equals(template.hasKey("b")));

    }
}
