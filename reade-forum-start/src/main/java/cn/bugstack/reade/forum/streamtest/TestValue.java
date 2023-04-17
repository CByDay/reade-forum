package cn.bugstack.reade.forum.streamtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */

@Component
@Slf4j
public class TestValue implements InitializingBean {
    @Value("${kafka.bootstrap.servers}")
    private String kafkaServers;
    @Value("${kafka.servers.first.topic}")
    private String topic;

    @Value("${spring.mail.host}")
    private String host;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(kafkaServers);
        log.info(topic);
        log.info("mail-host：   "+host);
    }

}
