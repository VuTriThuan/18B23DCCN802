package model;

public class ImportReceiptDetail2 {
    private int id;
    private float quantity;
    private float unitPrice;
    private Ingredient2 ingredient;

    public ImportReceiptDetail2() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Ingredient2 getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient2 ingredient) {
        this.ingredient = ingredient;
    }
}
