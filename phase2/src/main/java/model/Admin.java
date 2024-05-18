package model;

import java.time.LocalDate;

final public class Admin extends UserAccount {

    private static Admin admin;

    private Admin(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate) throws Exception {
        super(userName, accountPassword, fullName, email, phoneNumber, birthDate);
    }

    public static Admin getAdmin(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate) throws Exception {
        if (admin == null) {
            admin = new Admin(userName, accountPassword, fullName, email, phoneNumber, birthDate);
            Database.getDatabase().getAllUsers().add(admin);
        }
        return admin;
    }

    public static Admin getAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "Admin's " + super.toString();
    }
}
