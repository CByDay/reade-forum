package cn.bugstack.reade.forum.api.controller.user;

import cn.bugstack.reade.forum.application.converter.R;
import cn.bugstack.reade.forum.application.converter.RestBean;
import cn.bugstack.reade.forum.application.service.IUserLoginService;
import cn.bugstack.reade.forum.infrastructure.utils.EmailUtil;
import cn.bugstack.reade.forum.infrastructure.utils.ValidateCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
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

    private final String USERNAME_REGEX = "^[A-Za-z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";

    @Resource
    private IUserLoginService iUserLoginService;

    /**
     * @Description: 邮箱验证
     * @Author: zhd
     * @Date: 2023/4/15
     * @param: email
     * @param: session
     * @return: RestBean<String>
     **/
    @PostMapping("/valid-email")
    public RestBean<String> validateEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("emailCode") String email, HttpSession session) {
        String mes = iUserLoginService.sendValidateEmail(email, session.getId());
        if (mes == null)
            return RestBean.success("邮件已发送，请注意查收");
        else
            return RestBean.failure(400, mes);
    }

    /**
     * @Description: 新用户注册
     * @Author: zhd
     * @Date: 2023/4/14
     * @param: username
     * @param: password
     * @param: email
     * @param: emailCode
     * @param: session
     * @return: RestBean<String>
     **/
    @PostMapping("/register")
    public RestBean<String> registerUser(@Length(min = 2, max = 8) @RequestParam("username") String username,
                                         @Length(min = 6, max = 16) @RequestParam("password") String password,
                                         @RequestParam("email") String email,
                                         @Length(min = 6, max = 8) @RequestParam("emailCode") String emailCode,
                                         HttpSession session) {

        String mes = iUserLoginService.validateAndRegisterUser(username, password, email, emailCode, session.getId());
        if (mes == null) {
            return RestBean.success("注册成功");
        } else {
            return RestBean.failure(400, "注册失败，验证码填写错误");
        }
    }

    /**
     * @Description: 修改密码
     * @Author: zhd
     * @Date: 2023/4/15
     * @Param:
     * @param: email
     * @param: emailCode
     * @param: session
     * @return: RestBean<String>
     **/
    @PostMapping("/retrievePassword")
    public RestBean<String> retrievePassword(@RequestParam("email") String email,
                                             @Length(min = 6, max = 8) @RequestParam("emailCode") String emailCode,
                                             HttpSession session) {

        String mes = iUserLoginService.retrievePassword(email, emailCode, session.getId());
        if (mes == null) {
            return RestBean.success("注册成功");
        } else {
            return RestBean.failure(400, "注册失败，验证码填写错误");
        }
    }
}
