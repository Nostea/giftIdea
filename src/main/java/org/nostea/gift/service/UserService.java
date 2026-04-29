package org.nostea.gift.service;

import org.nostea.gift.MembershipCsvEntity;
import org.nostea.gift.MembershipsCsvRepository;
import org.nostea.gift.UserCsvEntity;
import org.nostea.gift.UsersCsvRepository;
import org.nostea.gift.model.Group;
import org.nostea.gift.model.User;
import org.nostea.gift.model.UserRole;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    //dependency injection
    private final UsersCsvRepository usersCsvRepository = new UsersCsvRepository();
    private final GroupService groupService;
    private final MembershipsCsvRepository membershipsCsvRepository;

    public UserService(GroupService groupService, MembershipsCsvRepository membershipsCsvRepository) {
        this.groupService = groupService;
        this.membershipsCsvRepository = membershipsCsvRepository;
    }

    public List<User> getAllUsers() {
        //User user1 = new User(1,"Testuser1", "1234", "user@testmail.com", "defaultAvatar.jpg");
        //User user2 = new User(2,"Knapfel2", "Passwort123", "Knapfel2@testmail.com", "defaultAvatar.jpg");
        //User user3 = new User(3,"TheDude33", "PassPass33", "TheDude33@testmail.com", "defaultAvatar.jpg");

        //List<User> users = List.of(user1,user2,user3);

        try {
            List<UserCsvEntity> csvUsers = usersCsvRepository.getAllUsers();
            List<User> users = new ArrayList<>();

            for (UserCsvEntity csvUser : csvUsers) {
                users.add(convertCsvUserToUser(csvUser));  // TODO: mein user in der CSV sieht noch nicht so aus wie im Model
            }

            return users;

        } catch (Exception e) {
            System.out.println("Error reading users from CSV: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public User getUserById(long id) {
        List<User> users = getAllUsers();

        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        System.out.println("User with id " + id + " not found");
        return null;
    }

    public User createUser(User userRequest) {
        if (userRequest == null || userRequest.getUsername() == null || userRequest.getUsername().isBlank() || userRequest.getPassword() == null || userRequest.getPassword().isBlank()) {
            return null;
        }

        try {

            List<UserCsvEntity> existingUsers = usersCsvRepository.getAllUsers();

            for (UserCsvEntity user : existingUsers) {
                if (user.userName().equalsIgnoreCase(userRequest.getUsername().trim())) {
                    System.out.println("User with username already exists: " + userRequest.getUsername());
                    return null;
                }
            }

            int nextId = usersCsvRepository.getNextUserId();

            UserCsvEntity newCsvUser = new UserCsvEntity(nextId, userRequest.getUsername().trim(), userRequest.getPassword());

            boolean hasAddedNewCsvUser = usersCsvRepository.addUserToCsv(newCsvUser);

            if (!hasAddedNewCsvUser) {
                return null;
            }

            User user = convertCsvUserToUser(newCsvUser);
            return user;

        } catch (Exception e) {
            System.out.println("Error creating user in CSV: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteUser(long id) {
        if (id <= 0) {
            return false;
        }

        try {
            boolean userExists = usersCsvRepository.userIdExists((int) id);
            if (!userExists) {
                return false;
            }

            // TODO: kann man besser schreiben?
            // ich brauche nur die ID, aber usersCsvRepository braucht die volle Entity für die Methode deleteUser()
            UserCsvEntity userToDelete = new UserCsvEntity((int) id, "", "");
            return usersCsvRepository.deleteUser(userToDelete);

        } catch (Exception e) {
            System.out.println("Error deleting user from CSV: " + e.getMessage());
            return false;
        }
    }

    public List<User> getUsersByRole(UserRole role) {
        List<User> users = getAllUsers();
        List<User> filteredUsers = new ArrayList<>();

        for (User user : users) {
            if (user.getRole() == role) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }

    public User addGroupToUser(long userId, Group group) {
        User user = getUserById(userId);

        if (user == null) {
            System.out.println("User with id " + userId + " not found");
            return null;
        }

        if (group == null) {
            System.out.println("Group not found");
            return null;
        }

        user.getMemberships().add(group);   // [] + group object
        System.out.println("User " + user.getUsername() + " is now member of the group " + group.getGroupName());
        return user;
    }

    private User convertCsvUserToUser(UserCsvEntity csvUser) {
        String placeholderMail = csvUser.userName().replace(" ", "").toLowerCase() + "@testdomain.de";
        return new User(csvUser.id(), csvUser.userName(), csvUser.password(), placeholderMail, "defaultAvatar.jpg");
    }

    public User deleteMemberFromGroupById(long groupId, long userId) {
        User user = getUserById(userId);
        Group group = groupService.getGroupById(groupId);

        if (group == null) {
            System.out.println("Group with id " + groupId + " not found");
            return null;
        }

        if (user == null) {
            System.out.println("User with id " + userId + " not found");
            return null;
        }

        try {
            MembershipCsvEntity memberShipToDelete = new MembershipCsvEntity(userId, groupId, null);
            boolean deleted = membershipsCsvRepository.deleteMembership(memberShipToDelete);

            if (!deleted) {
                System.out.println("Error deleting membership from CSV is_member_of");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error deleting membership from CSV: " + e.getMessage());
            return null;
        }
    }
}
