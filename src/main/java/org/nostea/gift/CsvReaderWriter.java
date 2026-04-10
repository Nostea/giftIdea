package org.nostea.gift;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderWriter {

    public static List<UserCsvEntity> readUsers(String filePath) throws Exception {

        List<UserCsvEntity> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        reader.readLine();  // erste Zeile mit Titeln überspringen

        String line = reader.readLine();

        while (line != null) {
            String[] parts = line.split(";");

            int id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];

            UserCsvEntity user = new UserCsvEntity(id, username, password);
            users.add(user);

            line = reader.readLine();
        }
        reader.close();
        return users;
    }

    public static void writeNewUserCsv (UserCsvEntity user) throws Exception {
        FileWriter fileWriter = new FileWriter(CsvFilePaths.USERS_CSV_PATH, true);  // true macht Append
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(user.id() + ";" + user.userName() + ";" + user.password() + "\n");
        bufferedWriter.close();
        System.out.println("Wrote user " + user.userName() + " to users.csv");
    }


    public static void clearCsv (String filepath) throws IOException {
        new FileWriter(filepath, false).close();  // false deaktiviert append, Inhalt wird dadurch geleert
    }

    public static void writeCsvFileHeader(String csvFilename) throws IOException {
        switch(csvFilename) {
            case "users.CSV":
                FileWriter filewriter1 = new FileWriter(CsvFilePaths.USERS_CSV_PATH, true);
                BufferedWriter bufferedWriter1 = new BufferedWriter(filewriter1);

                bufferedWriter1.write("id;username;password" + "\n");
                bufferedWriter1.close();
                break;

            case "groups.CSV":
                FileWriter filewriter2 = new FileWriter(CsvFilePaths.GROUPS_CSV_PATH, true);
                BufferedWriter bufferedWriter2 = new BufferedWriter(filewriter2);

                bufferedWriter2.write("id;groupName" + "\n");
                bufferedWriter2.close();
                break;

            case "is_member_of.CSV":
                FileWriter filewriter3 = new FileWriter(CsvFilePaths.IS_MEMBER_OF_CSV_PATH, true);
                BufferedWriter bufferedWriter3 = new BufferedWriter(filewriter3);

                bufferedWriter3.write("userId;groupId;joined_at" + "\n");
                bufferedWriter3.close();
                break;

            default:
                System.out.println("This file doesn't ");
        }

    }


    public static List<GroupCsvEntity> readGroups(String filePath) throws Exception {

        List<GroupCsvEntity> groupsList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        reader.readLine();  // erste Zeile mit Titeln überspringen

        String line = reader.readLine();

        while (line != null) {
            String[] parts = line.split(";");

            int id = Integer.parseInt(parts[0]);
            String groupName = parts[1];

            GroupCsvEntity group = new GroupCsvEntity(id, groupName);
            groupsList.add(group);

            line = reader.readLine();
        }
        reader.close();
        return groupsList;
    }

    public static void writeNewGroupCsv(GroupCsvEntity newGroup) throws IOException {
        FileWriter fileWriter = new FileWriter(CsvFilePaths.GROUPS_CSV_PATH, true);  // true macht Append
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(newGroup.id() + ";" + newGroup.groupName() + ";" + "\n");
        bufferedWriter.close();
        System.out.println("Wrote new group " + newGroup.groupName() + " to groups.csv");
    }
}

/*

    public static void overwriteUsersCsv(List<UserCsvEntity> users) throws Exception {
        FileWriter fileWriter = new FileWriter(CsvFilePaths.USERS_CSV_PATH, false);  // false = überschreiben
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("id;username;password\n");  // Header neu schreiben
        for (UserCsvEntity user : users) {
            bufferedWriter.write(user.id() + ";" + user.userName() + ";" + user.password() + "\n");
        }
        bufferedWriter.close();
        System.out.println("users.CSV neu geschrieben mit " + users.size() + " Einträgen");
    }

*/
