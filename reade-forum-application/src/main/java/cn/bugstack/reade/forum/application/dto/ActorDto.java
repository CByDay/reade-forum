package cn.bugstack.reade.forum.application.dto;

import lombok.Data;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: cn.bugstack.reade.forum.client.dto
 * @Author: zhd
 * @CreateTime: 2023-04-05  17:53
 * @Description: actor 实体
 * @Version: 1.0
 */
@Data
public class ActorDto {

    private String actorId;

    private String firstName;

    private String lastName;
}
