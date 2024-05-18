package model;

import java.time.LocalDate;

final public class PremiumListener extends Listener {
    private int subscriptionRemainedDays;

    public PremiumListener(String userName, String accountPassword, String fullName, String email, String phoneNumber, LocalDate birthDate, int subscriptionRemainedDays) throws Exception {
        super(userName, accountPassword, fullName, email, phoneNumber, birthDate);
        super.setSubscriptionExpiryDate(LocalDate.now().plusDays(subscriptionRemainedDays));
        this.subscriptionRemainedDays = subscriptionRemainedDays;
    }

    public int getSubscriptionRemainedDays() {
        return subscriptionRemainedDays;
    }

    public void setSubscriptionRemainedDays(int subscriptionRemainedDays) {
        this.subscriptionRemainedDays = subscriptionRemainedDays;
    }

    @Override
    public String toString() {
        return super.toString() + "subscription type: Premium";
    }
}
