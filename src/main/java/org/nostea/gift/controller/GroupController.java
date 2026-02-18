package org.nostea.gift.controller;

import org.nostea.gift.model.Group;
import org.nostea.gift.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroupsResponse() {
        List<Group> groups = groupService.getAllGroups();
        if (groups.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupByIdResponse(@PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Group group = groupService.getGroupById(id);

        if (group == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(group);
        }
    }

    @PostMapping
    public ResponseEntity<Group> createGroupResponse(@RequestBody Group group) {
        if (group == null || group.getGroupName() == null || group.getGroupName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Group createdGroup = groupService.createGroup(group);
        return ResponseEntity.ok(createdGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupByIdResponse(@PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        boolean deleted = groupService.deleteGroup(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
