package model;

public class SupplierIngredient2 {
    private int id;
    private float supplyPrice;
    private Ingredient2 ingredient;

    public SupplierIngredient2() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(float supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public Ingredient2 getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient2 ingredient) {
        this.ingredient = ingredient;
    }
}
