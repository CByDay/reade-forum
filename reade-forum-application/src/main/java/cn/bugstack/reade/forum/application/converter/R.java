package cn.bugstack.reade.forum.application.converter;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
@Data
public class R<T> {

    private Integer code; //编码 1成功；0或者其他数字均为失败

    private String message; //信息

    private T data; //数据

    private Map map = new HashMap();    //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String message) {
        R r = new R();
        r.message = message;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
