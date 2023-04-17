import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhd
 * @date 2023/4/9
 * @description:
 */

@SpringBootTest
public class TestUtil {

    @Test
    @DisplayName("updateLod")
    void updateLod(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());


        System.out.println(filtered);
    }

    @Value("${kafka.bootstrap.servers}")
    private String kafkaServers;
    @Value("${kafka.servers.first.topic}")
    private String topic;

    @Test
    @DisplayName("testName")
    void testName(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("12345"));

    }


}
