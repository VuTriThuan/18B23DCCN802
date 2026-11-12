package model;

public class OrderDetail2 {
    private int id;
    private int quantity;
    private float price;
    private String note;
    private Dish2 dish;

    public OrderDetail2() {
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

    public Dish2 getDish() {
        return dish;
    }

    public void setDish(Dish2 dish) {
        this.dish = dish;
    }
}
