package cn.bugstack.reade.forum.streamTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhd
 * @date 2023/4/9
 * @description: 用于学习 stream 流
 */

@Data
@EqualsAndHashCode // 用于后期去重
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private Long id;

    private String name;

    private Integer age;

    private String address;

    private List<Book> books;


}
