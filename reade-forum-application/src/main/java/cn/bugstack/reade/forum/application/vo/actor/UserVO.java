package cn.bugstack.reade.forum.application.vo.actor;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户手机号码
     * */
    private String phone;

    /**
     * 是否可以发布文章 0不能，1可以发布
     */
    private Integer userPublishArticle;

    /**
     * 是否冻结，0正常，1冻结（冻结后无法登陆）
     */
    private Integer userFrozen;

    /**
     * 注册时间
     */
    private Date userRegisterTime;
}
