package cn.bugstack.reade.forum.api.controller.user;

import cn.bugstack.reade.forum.application.converter.R;
import cn.bugstack.reade.forum.application.converter.RestBean;
import cn.bugstack.reade.forum.application.service.IUserLoginService;
import cn.bugstack.reade.forum.infrastructure.utils.EmailUtil;
import cn.bugstack.reade.forum.infrastructure.utils.ValidateCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;

/**
 * @author zhd
 * @date 2023/4/13
 * @description:
 */
@Validated
@RestController
@RequestMapping("/api/auth")
public class UserController {

    // 邮箱正则验证
    private final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";

    @Resource
    private IUserLoginService iUserLoginService;

    @PostMapping("/valid-emial")
    public RestBean<String> validateEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("emailCode") String emailCode, HttpSession session) {
        if(iUserLoginService.sendValidateEmail(emailCode,session.getId()))
            return RestBean.success("邮件已发送，请注意查收");
        else
            return RestBean.failure(400,"邮件发送失败，请稍后重试");
    }
//
//        EmailUtil emailUtil = new EmailUtil();
//        //获取邮箱
////        String phone = user.getPhone();
//        if (StringUtils.isNotEmpty(emailCode)) {
//            //生成随机的4位验证码
//            String code = ValidateCodeUtils.generateValidateCode(4).toString();
////            log.info("code={}", code);
//            System.out.println("code={}  " + code);
//            //调用自己封装的qq邮箱发送器发送邮箱
//            boolean isFlag = emailUtil.sendAuthCodeEmail(emailCode, code,session.getId());
//            //需要将验证码保存到session中,做登录校验
////            session.setAttribute(phone, code);
////            session.setMaxInactiveInterval(60); //设置session有效期 60秒,这里以后可能会用redis,所以先不设置！
//            System.out.println(isFlag);
//            return RestBean.success("邮件已发送，请注意查收");
//        }
//        return RestBean.failure(400, "邮件发送失败，请稍后重试");
//    }
}
