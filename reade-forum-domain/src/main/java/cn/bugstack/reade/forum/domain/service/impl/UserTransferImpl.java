package cn.bugstack.reade.forum.domain.service.impl;

import cn.bugstack.reade.forum.domain.entity.UserEntity;
import cn.bugstack.reade.forum.domain.repository.user.UserRepository;
import cn.bugstack.reade.forum.domain.service.UserTransferService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
@Service
@Slf4j
public class UserTransferImpl implements UserTransferService {

    // 主键id
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }

    @Value("${spring.mail.username}")
    String mailUsername;

    @Resource
    StringRedisTemplate template;

    @Resource
    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * @Description: 登录
     * @Author: zhd
     * @Date: 2023/4/15
     * @param: userName
     * @return: UserEntity
     **/
    @Override
    public UserEntity loginUser(String userName) {
        return userRepository.loadUserByUserNameOrUserEmail(userName);
    }

    /**
     * @Description: 创建按用户
     * @Author: zhd
     * @Date: 2023/4/15
     * @param: username
     * @param: password
     * @param: email
     * @return: int
     **/
    @Override
    public String creatUser(String username, String password, String email, String emailCode, String sessionId) {
        String key = "email:" + sessionId + ":" + email;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            log.info(key);
            String s = template.opsForValue().get(key);
            if (s == null) {
                return "401";
            }
            if (s.equals(emailCode)) {
                password = encoder.encode(password);
                // 数据库执行 创建用户
                if (userRepository.creatUserRepository(username, password, email, getUUID()) > 0) {
                    //创建完成后删除 redis 里面的key
                    template.delete(key);
                    return "200";
                } else {
                    return "500";
                }
            } else {
                return "402";
            }

        } else {
            return "403";
        }
    }

    /**
     * @Description: 找回密码
     * 1.先对将要接收验证码的邮箱进行校验是否注册使用
     *
     * @Author: zhd
     * @Date: 2023/4/15
     * @Param:
     * @param: email
     * @param: emailCode
     * @param: sessionId
     * @return: int
     **/
    @Override
    public String retrievePasswordAA(String email, String emailCode, String sessionId) {
        String authCode = (new Random().nextInt(8999999) + 100000) + "";
        String key = "email:" + sessionId + ":" + email;
        SimpleEmail mail = new SimpleEmail();
        if (null == userRepository.loadUserByUserNameOrUserEmail(email)) {
            return "0";
        } else {
            try {

                mail.setHostName("smtp.qq.com");//发送邮件的服务器,这个是qq邮箱的，不用修改
                mail.setAuthentication("1959337028@qq.com", "sjtvybcgmzaobiad");//第一个参数是对应的邮箱用户名一般就是自己的邮箱第二个参数就是SMTP的密码,我们上面获取过了
                mail.setFrom("1959337028@qq.com", "mrs");  //发送邮件的邮箱和发件人
                mail.setSSLOnConnect(false); //使用安全链接 (原来是 true)
                mail.addTo(email);//接收的邮箱
                mail.setSubject("验证码");//设置邮件的主题
                mail.setMsg("尊敬的用户:你好!\n 验证码为:" + authCode + "\n" + "     (有效期为一分钟)");//设置邮件的内容
                // 存 redis
                template.opsForValue().set(key, authCode, 3, TimeUnit.MINUTES);
                mail.send();//发送
                //创建完成后删除 redis 里面的key
                template.delete(key);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return "401";
            }
        }
    }

    @Override
    public String retrievePasswordBB(String email, String passWord, String emailCode, String sessionId,String type){

        userRepository.changePassword(email,passWord,type);
        return null;
    }


}
