package cn.bugstack.reade.forum.application.service.impl;

import cn.bugstack.reade.forum.application.service.IUserLoginService;
import cn.bugstack.reade.forum.domain.entity.UserEntity;
import cn.bugstack.reade.forum.domain.service.UserTransferService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@Slf4j
public class UserLoginImpl implements IUserLoginService {

    @Value("${spring.mail.username}")
    String mailUsername;

    @Resource
    private UserTransferService userTransferService;

    @Resource
    StringRedisTemplate template;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Resource
    MailSender mailSender;

    @Resource
    private MailSenderAutoConfiguration mailSenderAutoConfiguration;

    /**
     * @Description: 登录
     * @Author: zhd
     * @Date: 2023/4/15
     * @param: username （用户名/邮箱） 均可登录
     * @return: UserDetails
     **/
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
     * @description:发送验证码进行邮箱验证
     * 1.先生成对应的验证码
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
    public String sendValidateEmail(String email, String sessionId) {

        String authCode = (new Random().nextInt(8999999) + 100000) + "";
        log.info("sessionId:  " + sessionId);
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
        try {

            if (Boolean.TRUE.equals(template.hasKey(key))) {
                Long expire = Optional.ofNullable(template.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
                if (expire > 120) {
                    return "操作频繁，请稍后再试";
                }
            }
            if (userTransferService.loginUser(email) != null) {
                return "此邮箱已被使用";
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
            log.info("key:  "+key);
            mail.send();//发送
            return null;
        } catch (EmailException e) {
            e.printStackTrace();
            return "邮件发送失败，请联系管理员";
        }

    }

    /**
     * @description: 新用户注册
     * @author: zhd
     * @date: 2023/4/13 19:51
     * @param:
     * @param: username
     * @param: password
     * @param: email
     * @param: emailCode
     * @return: boolean
     **/
    @Override
    public String validateAndRegisterUser(String username, String password, String email, String emailCode, String sessionId) {

        String str = userTransferService.creatUser(username, password, email, emailCode, sessionId);
        if ("500".equals(str)) {
            return "验证码错误，请重新发送";
        } else if ("401".equals(str)) {
            return "验证码失效，请重新发送";
        } else if ("402".equals(str)) {
            return "验证码错误，请重新发送";
        } else if ("403".equals(str)) {
            return "请先发起验证码";
        } else {
            return null;
        }
    }

    /**
     * @Description: 找回密码。
     * 1.先进行邮箱校验，查询是否注册过
     * 2.如果有，则可以发送验证码供其使用
     * 3.如果则提示让其使用该邮箱注册
     * @Author: zhd
     * @Date: 2023/4/14
     * @param: email
     * @param: emailCode
     * @param: sessionId
     * @return: String
     **/
    @Override
    public String retrievePasswordA(String email, String emailCode, String sessionId) {
        String str = userTransferService.retrievePasswordAA(email, emailCode, sessionId);

        if ("0".equals(str)) {
            return "此邮箱并未注册使用，请先去注册";
        } else if ("401".equals(str)) {
            return "邮件发送失败，请联系管理员";
        } else {
            return null;
        }

//
//        String authCode = (new Random().nextInt(8999999) + 100000) + "";
//        String key = "email:" + sessionId + ":" + email;
//        SimpleEmail mail = new SimpleEmail();
//        if (null == userTransferService.loginUser(email)) {
//            return "此邮箱并未注册使用，请先去注册";
//        } else {
//            try {
//
//                mail.setHostName("smtp.qq.com");//发送邮件的服务器,这个是qq邮箱的，不用修改
//                mail.setAuthentication("1959337028@qq.com", "sjtvybcgmzaobiad");//第一个参数是对应的邮箱用户名一般就是自己的邮箱第二个参数就是SMTP的密码,我们上面获取过了
//                mail.setFrom("1959337028@qq.com", "mrs");  //发送邮件的邮箱和发件人
//                mail.setSSLOnConnect(false); //使用安全链接 (原来是 true)
//                mail.addTo(email);//接收的邮箱
//                mail.setSubject("验证码");//设置邮件的主题
//                mail.setMsg("尊敬的用户:你好!\n 验证码为:" + authCode + "\n" + "     (有效期为一分钟)");//设置邮件的内容
//                // 存 redis
//                template.opsForValue().set(key, authCode, 3, TimeUnit.MINUTES);
//                mail.send();//发送
//                //创建完成后删除 redis 里面的key
//                template.delete(key);
//                return null;
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "邮件发送失败，请联系管理员";
//            }
//        }


    }

    @Override
    public String retrievePasswordB(String email, String passWord, String emailCode, String sessionId,String type) {
        String str = userTransferService.retrievePasswordBB(email,passWord, emailCode, sessionId,type);

        if ("0".equals(str)) {
            return "此邮箱并未注册使用，请先去注册";
        } else if ("401".equals(str)) {
            return "邮件发送失败，请联系管理员";
        } else {
            return null;
        }
    }
}
