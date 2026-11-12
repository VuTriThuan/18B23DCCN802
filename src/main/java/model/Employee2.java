package model;

import java.util.Date;

public abstract class Employee2 extends User2 {
    private String position;
    private Date hireDate;

    public Employee2() {
        super();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
