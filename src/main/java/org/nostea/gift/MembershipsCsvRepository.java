package org.nostea.gift;

import java.io.IOException;
import java.util.List;

public class MembershipsCsvRepository {

    public List<MembershipCsvEntity> getAllMemberships() {
        List<MembershipCsvEntity> membershipsList = CsvReaderWriter.readMemberships(CsvFilePaths.IS_MEMBER_OF_CSV_PATH);

        if (membershipsList.isEmpty()) {
            System.out.println("no memberships exist yet");
            return membershipsList;
        }

        //DEBUG only
        for (MembershipCsvEntity membership : membershipsList) {
            System.out.println(membership);
        }

        return membershipsList;
    }

    public boolean addMembershipToCsv(MembershipCsvEntity newMembership) {
        List<MembershipCsvEntity> membershipsList = CsvReaderWriter.readMemberships(CsvFilePaths.IS_MEMBER_OF_CSV_PATH);

        for (MembershipCsvEntity membership : membershipsList) {
            if (membership.userId() == newMembership.userId() && membership.groupId() == newMembership.groupId()) {
                System.out.println("...user with this id in the same group already exist... ABORT");
                return false;
            }
        }
        CsvReaderWriter.writeNewMembershipCsv(newMembership);
        return true;
    }

    public boolean deleteMembership(MembershipCsvEntity membershipToBeDeleted) throws IOException {
        List<MembershipCsvEntity> membershipsList = CsvReaderWriter.readMemberships(CsvFilePaths.IS_MEMBER_OF_CSV_PATH);

        boolean membershipFound = membershipsList.removeIf(membership -> membership.userId() == membershipToBeDeleted.userId() && membership.groupId() == membershipToBeDeleted.groupId());


        if (!membershipFound) {
            System.out.println("Membership with userID " + membershipToBeDeleted.userId() + " not found. ABORT");
            return false;
        }

        CsvReaderWriter.clearCsv(CsvFilePaths.IS_MEMBER_OF_CSV_PATH);

        for (MembershipCsvEntity membership : membershipsList) {
            CsvReaderWriter.writeNewMembershipCsv(membership);
        }

        return true;

    }


}
