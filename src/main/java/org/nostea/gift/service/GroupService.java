package org.nostea.gift.service;

import org.nostea.gift.model.Group;
import org.nostea.gift.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final UserService userService;  //
    private final GroupMembershipService groupMembershipService;

    //dependency injection, make groupmembership and users visible here
    public GroupService(UserService userService, GroupMembershipService groupMembershipService) {
        this.userService = userService;
        this.groupMembershipService = groupMembershipService;
    }

    public List<Group> getAllGroups() {

        User user1 = userService.getUserById(1);  //
        User user2 = userService.getUserById(2);

        Group group1 = new Group(1,"Birthdaygroup 1", "Presents for birthday", 50, user1);
        Group group2 = new Group(2,"Birthdaygroup 2", "Collab for Presents", 100 , user2);

        List<Group> allGroupsList = List.of(group1,group2);
        return allGroupsList;
    }

    public Group getGroupById(long id) {
        List<Group> groups = getAllGroups();

        for(Group group : groups) {
            if(group.getId() == id) {
                return group;
            }
        }
        System.out.println("Group with id " + id + " not found");
        return null;
    }

    public Group getGroupOwner(User user) {
        List<Group> groups = getAllGroups();

        for(Group group : groups) {
            if(group.getOwner() == user) {
                return group;
            }
        }
        System.out.println("The specified group owner " + user + " not found");
        return null;
    }

    /*
    public List<User> getAllGroupMembers() {
        List<>
    }
*/



}
