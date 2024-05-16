package model;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class UserAccount {
    private String userName;
    private String accountPassword;
    private String fullName;
    private String Email;
    private String phoneNumber;
    private LocalDate birthDate;

    public UserAccount(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate) throws Exception {
        String phoneRegex = "^09\\d{9}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher phoneMatcher = phonePattern.matcher(phoneNumber);
        if (phoneMatcher.matches()) {
            this.phoneNumber = phoneNumber;
        } else throw new Exception("Phone Number Pattern Doesn't Match. Please Try Again.");
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (emailMatcher.matches()) {
            this.Email = email;
        } else throw new Exception("Email Pattern Doesn't Match. Please Try Again.");
        this.userName = userName;
        this.accountPassword = accountPassword;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Name: " + this.getFullName() + ", UserName: " + this.getUserName();
    }
}
