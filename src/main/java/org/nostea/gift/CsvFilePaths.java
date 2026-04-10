package org.nostea.gift;

public class CsvFilePaths {

    private static final String CSV_BASE_PATH = "src/main/java/org/nostea/gift/db/";

    public static final String USERS_CSV_PATH = CSV_BASE_PATH + "users.CSV";
    public static final String GROUPS_CSV_PATH = CSV_BASE_PATH + "groups.CSV";
    public static final String IS_MEMBER_OF_CSV_PATH = CSV_BASE_PATH + "is_member_of.CSV";

    private CsvFilePaths() {
        throw new AssertionError("Don't make instances of this class");
    }
}


