package org.nostea.gift.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private long id;
    private String groupName;
    private String description;
    private GroupMode mode;
    private int budgetLimit;
    private List<User> members;
    private User owner;
    private LocalDate deadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GroupMode getMode() {
        return mode;
    }

    public void setMode(GroupMode mode) {
        this.mode = mode;
    }

    public int getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(int budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Group(long id, String groupName, String description, int budgetLimit, User owner) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;
        this.mode = GroupMode.OPEN;
        this.budgetLimit = budgetLimit;
        this.members = new ArrayList<>();
        this.owner = owner;
        this.deadline = LocalDate.of(2026,5,12);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    // === getter & setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
