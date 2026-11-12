package model;

import java.util.Date;

public class Bill2 {
    private int id;
    private Date paymentTime;
    private float discount;
    private float totalAmount;
    private String paymentMethod;
    private Order2 order;
    private SaleStaff2 saleStaff;
    private MembershipCard2 membershipCard;

    public Bill2() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Order2 getOrder() {
        return order;
    }

    public void setOrder(Order2 order) {
        this.order = order;
    }

    public SaleStaff2 getSaleStaff() {
        return saleStaff;
    }

    public void setSaleStaff(SaleStaff2 saleStaff) {
        this.saleStaff = saleStaff;
    }

    public MembershipCard2 getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard2 membershipCard) {
        this.membershipCard = membershipCard;
    }
}
