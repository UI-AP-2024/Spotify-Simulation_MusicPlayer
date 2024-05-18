package model;

public class UserNotFoundException extends FailedLoginException{
    public UserNotFoundException() {
        super("User not found. try logging in again or signing up.");
    }
}
