package org.nostea.gift.controller;

import org.nostea.gift.model.GroupMembership;
import org.nostea.gift.service.GroupMembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups/groupmemberships")
public class GroupMembershipController {
    private final GroupMembershipService groupMembershipService;

    public GroupMembershipController(GroupMembershipService groupMembershipService) {
        this.groupMembershipService = groupMembershipService;
    }

    @GetMapping
    public ResponseEntity<List<GroupMembership>> getAllGroupMemberships() {
        List<GroupMembership> groupMemberships = groupMembershipService.getAllGroupMemberships();

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

        GroupMembership groupMembership = groupMembershipService.getGroupMembershipById(id);
                if (groupMembership == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    return ResponseEntity.ok(groupMembership);
                }
    }


}
