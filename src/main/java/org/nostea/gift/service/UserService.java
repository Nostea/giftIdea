package org.nostea.gift.service;

import org.nostea.gift.model.User;
import org.nostea.gift.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> getAllUsers() {
        User user1 = new User(1,"Testuser1", "1234", "user@testmail.com", "defaultAvatar.jpg");
        User user2 = new User(2,"Knapfel2", "Passwort123", "Knapfel2@testmail.com", "defaultAvatar.jpg");
        User user3 = new User(3,"TheDude33", "PassPass33", "TheDude33@testmail.com", "defaultAvatar.jpg");

        List<User> users = List.of(user1,user2,user3);
        return users;
    }

    public User getUserById(long id) {
        List<User> users = getAllUsers();

        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        System.out.println("Book with id " + id + " not found");
        return null;
    }

    public List<User> getUsersByRole(UserRole role) {
        List<User> users = getAllUsers();
        if(!users.isEmpty()) {
            List<User> filteredUsers = new ArrayList<>();

            for(User user : users) {
                if(user.getRole() == role) {
                    filteredUsers.add(user);
                }
            }
            return filteredUsers;
        }
        System.out.println("No users with role " + role + " found");
        return null;
    }

}
