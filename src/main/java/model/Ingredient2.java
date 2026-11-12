package model;

public class Ingredient2 {
    private int id;
    private String name;
    private String unit;
    private float quantityInStock;

    public Ingredient2() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(float quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
