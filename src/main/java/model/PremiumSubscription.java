package model;

public enum PremiumSubscription {
    THIRTY_DAYS(5), SIXTY_DAYS(9), ONE_HUNDRED_EIGHTY_DAYS(14);
    private final int subscriptionCost;

    PremiumSubscription(int subscriptionCost) {
        this.subscriptionCost = subscriptionCost;
    }

    public int getSubscriptionCost() {
        return subscriptionCost;
    }
}
