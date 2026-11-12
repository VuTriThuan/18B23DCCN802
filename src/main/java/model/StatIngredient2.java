package model;

public class StatIngredient2 extends Ingredient2 {
    private float totalImportQuantity;
    private float totalImportCost;

    public StatIngredient2() {
        super();
    }

    public float getTotalImportQuantity() {
        return totalImportQuantity;
    }

    public void setTotalImportQuantity(float totalImportQuantity) {
        this.totalImportQuantity = totalImportQuantity;
    }

    public float getTotalImportCost() {
        return totalImportCost;
    }

    public void setTotalImportCost(float totalImportCost) {
        this.totalImportCost = totalImportCost;
    }
}
