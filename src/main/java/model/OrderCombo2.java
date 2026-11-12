package model;

public class OrderCombo2 {
    private int id;
    private int quantity;
    private float price;
    private String note;
    private Combo2 combo;

    public OrderCombo2() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Combo2 getCombo() {
        return combo;
    }

    public void setCombo(Combo2 combo) {
        this.combo = combo;
    }
}
