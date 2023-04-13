package cn.bugstack.reade.forum.application.service.impl;

import cn.bugstack.reade.forum.application.service.IUserLoginService;
import cn.bugstack.reade.forum.domain.entity.UserEntity;
import cn.bugstack.reade.forum.domain.service.UserTransferService;
import cn.hutool.log.Log;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhd
 * @date 2023/4/11
 * @description: 登录校验 （spring-boot-starter-security)
 */
@Service
public class UserLoginImpl implements IUserLoginService {

    @Value("${spring.mail.username}")
    String mailUsername;

    @Resource
    private UserTransferService userTransferService;

    @Resource
    StringRedisTemplate template;

    @Resource
    MailSender mailSender;

    @Resource
    private MailSenderAutoConfiguration mailSenderAutoConfiguration;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isEmpty()) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        UserEntity userEntity = userTransferService.loginUser(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名/密码错误");
        }
        return User
                .withUsername(userEntity.getUserName())
                .password(userEntity.getUserPassword())
                .roles("user")
                .build();
    }

    /**
     * @description:邮箱验证 1.先生成对应的验证码
     * 2.把邮箱和对应的验证码直接放到Redis里面（设置过期时间）
     * 3.发送验证码到指定邮箱
     * 4.如果发送失败，把Redis中插入的删除
     * 5.用户在注册时，再从Redis里面取出对应键值对，然后看验证码是否一致
     * @author: zhd
     * @date: 2023/4/13 1:23
     * @param: url
     * @return: boolean
     **/
    @Override
    public boolean sendValidateEmail(String email, String sessionId) {

        String authCode = (new Random().nextInt(8999999) + 100000)+"";
        System.out.println("sessionId:  "+ sessionId);
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("1959337028@qq.com");
//        message.setTo(emailCode);
//        message.setSubject("您的验证邮件");
//        message.setText("验证码是： "+code);
//        try {
//            mailSender.send(message);
//            return true;
//        } catch (MailException e) {
//            e.getMessage();
//            return false;
//        }

//
        String key = "email:" + sessionId + ":" + email;
        SimpleEmail mail = new SimpleEmail();
//        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        try {

            if(Boolean.TRUE.equals(template.hasKey(key))){
                Long expire =Optional.ofNullable(template.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
                if(expire > 120){
                    return false;
                }
            }

            mail.setHostName("smtp.qq.com");//发送邮件的服务器,这个是qq邮箱的，不用修改
            mail.setAuthentication("1959337028@qq.com", "sjtvybcgmzaobiad");//第一个参数是对应的邮箱用户名一般就是自己的邮箱第二个参数就是SMTP的密码,我们上面获取过了
            mail.setFrom("1959337028@qq.com", "mrs");  //发送邮件的邮箱和发件人
            mail.setSSLOnConnect(false); //使用安全链接 (原来是 true)
            mail.addTo(email);//接收的邮箱
            mail.setSubject("验证码");//设置邮件的主题
            mail.setMsg("尊敬的用户:你好!\n 登陆验证码为:" + authCode + "\n" + "     (有效期为一分钟)");//设置邮件的内容

//            存 redis
            template.opsForValue().set(key, authCode, 3, TimeUnit.MINUTES);

            mail.send();//发送
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }

    }
}
