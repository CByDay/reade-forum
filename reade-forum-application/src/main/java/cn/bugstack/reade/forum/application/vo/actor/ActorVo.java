package cn.bugstack.reade.forum.application.vo.actor;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: cn.bugstack.reade.forum.client.vo
 * @Author: zhd
 * @CreateTime: 2023-04-05  16:25
 * @Description:
 * @Version: 1.0
 */
@Data
public class ActorVo implements Serializable {

    private String actorId;

    private String firstName;

    private String lastName;

}
