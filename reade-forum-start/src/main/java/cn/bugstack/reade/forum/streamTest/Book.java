package cn.bugstack.reade.forum.streamTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhd
 * @date 2023/4/9
 * @description: 用于学习 stream 流
 */
@Data
@EqualsAndHashCode // 用于后期去重
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;

    private String name;

    private String category;

    private Integer score;

    private String intro;
}
