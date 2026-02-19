package org.nostea.gift.controller;

import org.nostea.gift.model.Group;
import org.nostea.gift.model.User;
import org.nostea.gift.service.GroupService;
import org.nostea.gift.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final GroupService groupService;

    public UserController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsersResponse() {
        List<User> users = userService.getAllUsers();
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByIdResponse(@PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/{userId}/memberships/{groupId}")
    public ResponseEntity<User> addGroupMembershipByUserId(@PathVariable long userId, @PathVariable long groupId) {
        if (userId <= 0 || groupId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Group group = groupService.getGroupById(groupId);
        User updatedUser = userService.addGroupToUser(userId, group);

        if(updatedUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedUser);

    }

}
