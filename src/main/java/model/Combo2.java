package model;

import java.util.ArrayList;

public class Combo2 {
    private int id;
    private String name;
    private float price;
    private String description;
    private ArrayList<ComboDish2> comboDishes;

    public Combo2() {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ComboDish2> getComboDishes() {
        return comboDishes;
    }

    public void setComboDishes(ArrayList<ComboDish2> comboDishes) {
        this.comboDishes = comboDishes;
    }
}
