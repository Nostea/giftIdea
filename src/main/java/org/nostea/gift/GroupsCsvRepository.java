package org.nostea.gift;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupsCsvRepository {
    public List<GroupCsvEntity> getAllGroups() throws Exception {
        // TODO: Lies groups.csv ein, mache aus jeder Zeile der csv einen GroupCsvEntity eintrag in einer Liste, gib die Liste zurueck
        List<GroupCsvEntity> groupsList = CsvReaderWriter.readGroups(CsvFilePaths.GROUPS_CSV_PATH);

        if(groupsList.isEmpty()) {
            System.out.println("No groups exist yet");
            return groupsList;
        }

        for(GroupCsvEntity group : groupsList) {
            System.out.println(group);
        }
        return groupsList;
    }

    public int getNextGroupId() throws Exception {
        List<GroupCsvEntity> groupsList = CsvReaderWriter.readGroups(CsvFilePaths.GROUPS_CSV_PATH);

        int maxId = 0;
        for(GroupCsvEntity group : groupsList) {
            if (group.id() > maxId) {
                maxId = group.id();
            }
        }
        return maxId + 1;
    }

    public boolean addGroupToCsv(GroupCsvEntity newGroup) throws Exception {
        // TODO: User am Ende der CSV Datei hinzufuegen
        // TODO: Warum boolean: Kann ja sein, dass es schon einen Group mit Id aus dem Objekt gibt -> dann geht das offensichtlich nicht
        // Return false, wenn Group nicht hinzugefuegt werden konnte (aus welchen Gruenden auch immer)

        List<GroupCsvEntity> groupsList = CsvReaderWriter.readGroups(CsvFilePaths.GROUPS_CSV_PATH);

        for(GroupCsvEntity group : groupsList) {
            if(group.id() == newGroup.id() || group.groupName().equals(newGroup.groupName())) {
                System.out.println("Group with this name or id already exists... ABORT");
                return false;
            }
        }

        CsvReaderWriter.writeNewGroupCsv(newGroup);

        return true;
    }

    public boolean deleteGroup(GroupCsvEntity groupToBeDeleted) throws Exception {
        // TODO: Loesche korrespondierenden csv eintrag, WENN existent
        // TODO: Return false, wenn irgendwas schief geht (user existiert nicht, Datei nicht zugreifbar, etc)

        List<GroupCsvEntity> groupsList = CsvReaderWriter.readGroups(CsvFilePaths.GROUPS_CSV_PATH);
        boolean groupFound = groupsList.removeIf(group -> group.id() == groupToBeDeleted.id());

        if(!groupFound) {
            System.out.println("Group with ID " + groupToBeDeleted.id() + " not found. ABORT");
            return false;
        }

        CsvReaderWriter.clearCsv(CsvFilePaths.GROUPS_CSV_PATH);

        for(GroupCsvEntity group : groupsList) {
            CsvReaderWriter.writeNewGroupCsv(group);
        }

        return true;
    }


    public boolean groupExists(GroupCsvEntity group) throws Exception {
        // TODO: Wenn group mit id exisitert, dann true, ansonsten false

        List<GroupCsvEntity> groupsList = CsvReaderWriter.readGroups(CsvFilePaths.GROUPS_CSV_PATH);

        for(GroupCsvEntity groupItem : groupsList) {
            if(group.id() == groupItem.id()) {
                System.out.println("Group with ID " + groupItem.id() + " exists");
                return true;
            }
        }

        System.out.println("Group " + group.groupName() + " with ID " + group.id() + " doesn't exist");

        return false;
    }


    public boolean groupIdExists(int groupId) throws Exception {
        // TODO: Wenn in csv ein Eintrag mit id userid existiert, dann true, ansonsten false
        List<GroupCsvEntity> groupsList = CsvReaderWriter.readGroups(CsvFilePaths.GROUPS_CSV_PATH);

        for(GroupCsvEntity group : groupsList) {
            if(group.id() == groupId) {
                System.out.println("groupId " + groupId + " exists");
            }
        }
        System.out.println("groupId " + groupId + " doesn't exist");

        return false;
    }
}
