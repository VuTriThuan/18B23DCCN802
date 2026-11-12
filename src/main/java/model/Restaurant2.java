package model;

import java.util.ArrayList;

public class Restaurant2 {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private ArrayList<Employee2> employees;
    private ArrayList<Table2> tables;

    public Restaurant2() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Employee2> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee2> employees) {
        this.employees = employees;
    }

    public ArrayList<Table2> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table2> tables) {
        this.tables = tables;
    }
}
