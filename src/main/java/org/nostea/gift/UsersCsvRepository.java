package org.nostea.gift;

import java.util.List;

public class UsersCsvRepository {

    public List<UserCsvEntity> getAllUsers() throws Exception {
        // TODO: Lies users.csv ein, mache aus jeder Zeile der csv einen UsersCsvEntity eintrag in einer Liste, gib die Liste zurueck
        List<UserCsvEntity> usersList = CsvReaderWriter.readUsers(CsvFilePaths.USERS_CSV_PATH);

        if(usersList.isEmpty()) {
            System.out.println("no users exist yet");
            return usersList;
        }

        for(UserCsvEntity user : usersList) {
            System.out.println(user);
        }
        return usersList;
    }

    // for incrementing id upon creation of a new user
    public int getNextUserId() throws Exception {
        List<UserCsvEntity> usersList = CsvReaderWriter.readUsers(CsvFilePaths.USERS_CSV_PATH);

        int maxId = 0;
        for(UserCsvEntity user : usersList) {
            if (user.id() > maxId) {
                maxId = user.id();
            }
        }
        return maxId + 1;
    }

    public boolean addUserToCsv( UserCsvEntity newUser) throws Exception {
        List<UserCsvEntity> usersList = CsvReaderWriter.readUsers(CsvFilePaths.USERS_CSV_PATH);

        for (UserCsvEntity user : usersList) {
            if (user.userName().equals(newUser.userName()) || user.id() == newUser.id()) {
                System.out.println("...user with this name or id already exist... ABORT");
                return false;
            }
        }
        CsvReaderWriter.writeNewUserCsv(newUser);
        return true;
    }


    // einfach nur neuen user mit auto inkrementierung anlegen
    public boolean addUserToCsv( String newUsername, String newPassword) throws Exception {
        UserCsvEntity newUser = new UserCsvEntity(getNextUserId(), newUsername, newPassword);
        return addUserToCsv(newUser);
    }


    public boolean deleteUser(UserCsvEntity userTobeDeleted) throws Exception {
        List<UserCsvEntity> usersList = CsvReaderWriter.readUsers(CsvFilePaths.USERS_CSV_PATH);

        boolean userFound = usersList.removeIf(user -> user.id() == userTobeDeleted.id());

        if (!userFound) {
            System.out.println("User with id " + userTobeDeleted.id() + " not found. ABORT");
            return false;
        }

        CsvReaderWriter.clearCsv(CsvFilePaths.USERS_CSV_PATH);

        //clear all content of users.csv and rewrite with updated usersList
        for(UserCsvEntity user : usersList) {
            CsvReaderWriter.writeNewUserCsv(user);
        }

        return true;
    }


    public boolean userExists(UserCsvEntity user) throws Exception {
        // TODO: Wenn user mit id exisitert, dann true, ansonsten false
        List<UserCsvEntity> usersList = CsvReaderWriter.readUsers(CsvFilePaths.USERS_CSV_PATH);

        for(UserCsvEntity userItem : usersList) {
            if(user.id() == userItem.id()) {
                System.out.println("User with ID: " + user.id() + " exists");
                return true;
            }
        }

        System.out.println("User " + user.userName() + " doesn't exist");

        return false;
    }

    public boolean userIdExists(int userId) throws Exception {
        // TODO: Wenn in csv ein Eintrag mit id userid existiert, dann true, ansonsten false

        List<UserCsvEntity> usersList = CsvReaderWriter.readUsers(CsvFilePaths.USERS_CSV_PATH);

        for(UserCsvEntity user : usersList) {
            if(user.id() == userId) {
                System.out.println("userId " + userId + " exists");
                return true;
            }
        }

        System.out.println("userId " + userId + " doesn't exist");

        return false;
    }
}

