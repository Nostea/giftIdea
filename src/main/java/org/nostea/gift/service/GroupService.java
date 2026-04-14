package org.nostea.gift.service;

import org.nostea.gift.GroupCsvEntity;
import org.nostea.gift.GroupsCsvRepository;
import org.nostea.gift.model.Group;
import org.nostea.gift.model.GroupMembership;
import org.nostea.gift.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    private final UserService userService;
    private final GroupMembershipService groupMembershipService;

    private final GroupsCsvRepository groupsCsvRepository = new GroupsCsvRepository();

    //dependency injection, make groupmembership and users visible here
    public GroupService(UserService userService, GroupMembershipService groupMembershipService) {
        this.userService = userService;
        this.groupMembershipService = groupMembershipService;
    }

    private Group convertCsvGroupToGroup(GroupCsvEntity csvGroup) {
        String placeholderDescription = "no description";
        int placeholderBudgetLimit = 100;
        User placeholderOwner = null;
        return new Group(csvGroup.id(), csvGroup.groupName(), "no description", 100, null);
    }

    public List<Group> getAllGroups() {
        try {
            List<GroupCsvEntity> csvGroups = groupsCsvRepository.getAllGroups();
            List<Group> groups = new ArrayList<>();

            for (GroupCsvEntity csvGroup : csvGroups) {
                groups.add(convertCsvGroupToGroup(csvGroup));
            }
            return groups;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //User user1 = userService.getUserById(1);
        //User user2 = userService.getUserById(2);

        //Group group1 = new Group(1,"Birthdaygroup 1", "Presents for birthday", 50, user1);
        //Group group2 = new Group(2,"Birthdaygroup 2", "Collab for Presents", 100 , user2);

        //List<Group> allGroupsList = List.of(group1,group2);
        //return allGroupsList;
    }

    public Group getGroupById(long id) {

        List<Group> groups = getAllGroups();
        for (Group group : groups) {
            if (group.getId() == id) {
                return group;
            }
        }
        System.out.println("Group with id " + id + " not found");
        return null;
    }

    public Group createGroup(Group groupRequest) {
        if (groupRequest == null || groupRequest.getGroupName() == null || groupRequest.getGroupName().isBlank()) {
            return null;
        }

        try {
            List<GroupCsvEntity> existingGroups = groupsCsvRepository.getAllGroups();

            for (GroupCsvEntity group : existingGroups) {
                if (group.groupName().equalsIgnoreCase(groupRequest.getGroupName().trim())) {
                    System.out.println("Group with this username already exists: " + groupRequest.getGroupName());
                    return null;
                }
            }

            int nextId = groupsCsvRepository.getNextGroupId();

            GroupCsvEntity newCsvGroup = new GroupCsvEntity(nextId, groupRequest.getGroupName().trim());

            boolean hasAddedNewCsvGroup = groupsCsvRepository.addGroupToCsv(newCsvGroup);

            if (!hasAddedNewCsvGroup) {
                return null;
            }

            Group group = convertCsvGroupToGroup(newCsvGroup);
            return group;

        } catch (Exception e) {
            System.out.println("Error creating user in CSV: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteGroup(long id) {
        if (id <= 0) {
            return false;
        }

        try {
            boolean groupExists = groupsCsvRepository.groupIdExists((int) id);
            if (!groupExists) {
                return false;
            }

            GroupCsvEntity groupDoBeDeleted = new GroupCsvEntity((int) id, "");
            return groupsCsvRepository.deleteGroup(groupDoBeDeleted);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Group getGroupOwner(User user) {
        List<Group> groups = getAllGroups();

        for (Group group : groups) {
            if (group.getOwner() == user) {
                return group;
            }
        }
        System.out.println("The specified group owner " + user + " not found");
        return null;
    }

    public GroupMembership getMembershipById(long membershipId) {
        List<Group> groups = getAllGroups();
        return groupMembershipService.getGroupMembershipById(membershipId, groups);
    }

    public List<GroupMembership> getAllMemberships() {
        List<Group> groups = getAllGroups();
        return groupMembershipService.getAllGroupMemberships(groups);
    }

    public Group addMemberToGroup(long groupId, long userId) {
        Group group = getGroupById(groupId);
        User user = userService.getUserById(userId);

        if (group == null) {
            System.out.println("Group with id " + groupId + " not found");
            return null;
        }

        if (user == null) {
            System.out.println("User with id " + userId + " not found");
            return null;
        }

        group.getMembers().add(user);  // [] + user object
        user.getMemberships().add(group); // automatically register membership in user object
        System.out.println("User " + user.getUsername() + " added to group " + group.getGroupName());
        return group;
    }

    public Group deleteMemberFromGroup(long groupId, long userId) {
        Group group = getGroupById(groupId);
        User user = userService.getUserById(userId);

        if (group == null) {
            System.out.println("Group with id " + groupId + " not found");
            return null;
        }

        if (user == null) {
            System.out.println("User with id " + userId + " not found");
            return null;
        }

        group.getMembers().remove(user);
        user.getMemberships().remove(group);
        System.out.println("User " + user.getUsername() + " removed from group " + group.getGroupName());
        return group;
    }

    /*
    public List<User> getAllGroupMembers() {
        List<>
    }
*/


}
