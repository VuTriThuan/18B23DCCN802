package model;

public class Customer2 extends User2 {
    private String customerType;
    private int rewardPoints;
    private MembershipCard2 membershipCard;

    public Customer2() {
        super();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public MembershipCard2 getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard2 membershipCard) {
        this.membershipCard = membershipCard;
    }
}
