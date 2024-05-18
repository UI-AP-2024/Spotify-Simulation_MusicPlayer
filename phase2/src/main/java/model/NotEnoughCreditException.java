package model;

public class NotEnoughCreditException extends Exception {
    public NotEnoughCreditException() {
        super("your credit is not enough. please increase your account's credit.");
    }
}
