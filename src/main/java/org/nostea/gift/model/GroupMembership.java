package org.nostea.gift.model;

import java.time.LocalDateTime;
import java.util.List;

public class GroupMembership {
    private long id;
    private List<User> members;
    private Group group;
    private LocalDateTime joinedAt;

    public GroupMembership(long id, List<User> members, Group group) {
        this.id = id;
        this.members = members;
        this.group = group;
        this.joinedAt = LocalDateTime.now();
    }

    public void leaveGroup(){
        System.out.println("Removes Member from group");
    }

    // ===== Getter & Setter ====

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}

