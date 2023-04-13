package cn.bugstack.reade.forum.application.converter;

import lombok.Data;

/**
 * @author zhd
 * @date 2023/4/11
 * @description: 请求返回 JSON
 */
@Data
public class RestBean<T> {

    //状态码
    private int status;

    //是否成功
    private boolean success;

    //信息
    private T message;

    private RestBean(int status, boolean success, T message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    public static <T> RestBean<T> success() {
        return new RestBean<>(200, true, null);
    }

    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(200, true, data);
    }

    public static <T> RestBean<T> failure(int status) {
        return new RestBean<>(200, true, null);
    }

    public static <T> RestBean<T> failure(int status, T data) {
        return new RestBean<>(200, true, data);
    }

}
