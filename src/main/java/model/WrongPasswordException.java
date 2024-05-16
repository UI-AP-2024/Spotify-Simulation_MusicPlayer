package model;

public class WrongPasswordException extends FailedLoginException{
    public WrongPasswordException() {
        super("password is incorrect.");
    }
}
