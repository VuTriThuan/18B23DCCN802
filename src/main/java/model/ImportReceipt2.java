package model;

import java.util.ArrayList;
import java.util.Date;

public class ImportReceipt2 {
    private int id;
    private Date importDate;
    private float totalAmount;
    private String status;
    private String note;
    private Supplier2 supplier;
    private WarehouseStaff2 warehouseStaff;
    private ArrayList<ImportReceiptDetail2> details;

    public ImportReceipt2() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Supplier2 getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier2 supplier) {
        this.supplier = supplier;
    }

    public WarehouseStaff2 getWarehouseStaff() {
        return warehouseStaff;
    }

    public void setWarehouseStaff(WarehouseStaff2 warehouseStaff) {
        this.warehouseStaff = warehouseStaff;
    }

    public ArrayList<ImportReceiptDetail2> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<ImportReceiptDetail2> details) {
        this.details = details;
    }
}
