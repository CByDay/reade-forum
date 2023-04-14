package cn.bugstack.reade.forum.streamTest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */

@Component
public class TestValue implements InitializingBean {
    @Value("${kafka.bootstrap.servers}")
    private String kafkaServers;
    @Value("${kafka.servers.first.topic}")
    private String topic;

    @Value("${spring.mail.host}")
    private String host;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(kafkaServers);
        System.out.println(topic);
        System.out.println("mail-host：   "+host);
    }

}
