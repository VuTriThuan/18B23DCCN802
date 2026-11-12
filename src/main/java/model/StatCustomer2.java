package model;

public class StatCustomer2 extends Customer2 {
    private int totalVisits;
    private float totalSpending;

    public StatCustomer2() {
        super();
    }

    public int getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(int totalVisits) {
        this.totalVisits = totalVisits;
    }

    public float getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(float totalSpending) {
        this.totalSpending = totalSpending;
    }
}
