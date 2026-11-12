package model;

import java.util.ArrayList;
import java.util.Date;

public class Reservation2 {
    private int id;
    private Date bookingTime;
    private int guestCount;
    private String status;
    private String note;
    private Customer2 customer;
    private SaleStaff2 confirmedBy;
    private ArrayList<ReservationTable2> reservationTables;

    public Reservation2() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Customer2 getCustomer() {
        return customer;
    }

    public void setCustomer(Customer2 customer) {
        this.customer = customer;
    }

    public SaleStaff2 getConfirmedBy() {
        return confirmedBy;
    }

    public void setConfirmedBy(SaleStaff2 confirmedBy) {
        this.confirmedBy = confirmedBy;
    }

    public ArrayList<ReservationTable2> getReservationTables() {
        return reservationTables;
    }

    public void setReservationTables(ArrayList<ReservationTable2> reservationTables) {
        this.reservationTables = reservationTables;
    }
}
