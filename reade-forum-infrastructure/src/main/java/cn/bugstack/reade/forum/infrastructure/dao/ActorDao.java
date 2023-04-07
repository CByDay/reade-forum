package cn.bugstack.reade.forum.infrastructure.dao;

import lombok.Data;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: cn.bugstack.reade.forum.infrastructure.domain
 * @Author: zhd
 * @CreateTime: 2023-04-05  01:36
 * @Description: 与数据库表 actor 对应
 * @Version: 1.0
 */


@Data
public class ActorDao {
    private String actorId;

    private String firstName;

    private String lastName;

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
