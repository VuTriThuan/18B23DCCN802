package model;

import java.util.ArrayList;
import java.util.Date;

public class Menu2 {
    private int id;
    private String name;
    private Date effectiveDate;
    private String description;
    private ArrayList<MenuDish2> menuDishes;
    private ArrayList<MenuCombo2> menuCombos;

    public Menu2() {
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

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<MenuDish2> getMenuDishes() {
        return menuDishes;
    }

    public void setMenuDishes(ArrayList<MenuDish2> menuDishes) {
        this.menuDishes = menuDishes;
    }

    public ArrayList<MenuCombo2> getMenuCombos() {
        return menuCombos;
    }

    public void setMenuCombos(ArrayList<MenuCombo2> menuCombos) {
        this.menuCombos = menuCombos;
    }
}
