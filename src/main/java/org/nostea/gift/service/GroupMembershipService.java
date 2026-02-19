package org.nostea.gift.service;

import org.nostea.gift.model.Group;
import org.nostea.gift.model.GroupMembership;
import org.nostea.gift.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMembershipService {
    // for dependency injection in constructor
    private final UserService userService;

    //dependency injection, make users visible here
    public GroupMembershipService(UserService userService) {
        this.userService = userService;
    }

    public List<GroupMembership> getAllGroupMemberships(List<Group> groups) {

        User user1 = userService.getUserById(1);
        User user2 = userService.getUserById(2);
        User user3 = userService.getUserById(3);

        List<User> membersList1 = List.of(user1, user2, user3);
        List<User> membersList2 = List.of(user2, user3);

        Group group1 = null;
        Group group2 = null;

        for(Group group : groups) {
            if(group.getId() == 1) {
                group1 = group;
            }
            if(group.getId()== 2) {
                group2 = group;
            }
        }

        GroupMembership groupMembership1 = new GroupMembership(1, membersList1, group1);
        GroupMembership groupMembership2 = new GroupMembership(2, membersList2, group2);

        List<GroupMembership> allMembersList = List.of(groupMembership1,groupMembership2);
        return allMembersList;
    }


    public GroupMembership getGroupMembershipById(long groupmembershipId, List<Group> groups) {
        List<GroupMembership> allGroupMemberships = getAllGroupMemberships(groups);

        for(GroupMembership membership : allGroupMemberships) {
            if(membership.getId() == groupmembershipId) {
                return membership;
            }
        }
        System.out.println("GroupMembership with id " + groupmembershipId + " not found");
        return null;
    }

}
