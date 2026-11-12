package model;

import java.util.ArrayList;
import java.util.Date;

public class Order2 {
    private int id;
    private Date orderTime;
    private String orderType;
    private float totalAmount;
    private String status;
    private Customer2 customer;
    private SaleStaff2 saleStaff;
    private Table2 table;
    private Reservation2 reservation;
    private ArrayList<OrderDetail2> orderDetails;
    private ArrayList<OrderCombo2> orderCombos;

    public Order2() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer2 getCustomer() {
        return customer;
    }

    public void setCustomer(Customer2 customer) {
        this.customer = customer;
    }

    public SaleStaff2 getSaleStaff() {
        return saleStaff;
    }

    public void setSaleStaff(SaleStaff2 saleStaff) {
        this.saleStaff = saleStaff;
    }

    public Table2 getTable() {
        return table;
    }

    public void setTable(Table2 table) {
        this.table = table;
    }

    public Reservation2 getReservation() {
        return reservation;
    }

    public void setReservation(Reservation2 reservation) {
        this.reservation = reservation;
    }

    public ArrayList<OrderDetail2> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetail2> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public ArrayList<OrderCombo2> getOrderCombos() {
        return orderCombos;
    }

    public void setOrderCombos(ArrayList<OrderCombo2> orderCombos) {
        this.orderCombos = orderCombos;
    }
}
