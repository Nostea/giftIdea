package org.nostea.gift.controller;

import org.nostea.gift.model.GroupMembership;
import org.nostea.gift.service.GroupMembershipService;
import org.nostea.gift.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups/groupmemberships")
public class GroupMembershipController {
    private final GroupService groupService;

    public GroupMembershipController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<GroupMembership>> getAllGroupMemberships() {
        List<GroupMembership> groupMemberships = groupService.getAllMemberships();

        if(groupMemberships.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(groupMemberships);
    }

    @GetMapping("/{id}")
    ResponseEntity<GroupMembership> getGroupMembershipByIdResponse(@PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        GroupMembership groupMembership = groupService.getMembershipById(id);
                if (groupMembership == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    return ResponseEntity.ok(groupMembership);
                }
    }

}
