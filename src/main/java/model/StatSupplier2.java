package model;

public class StatSupplier2 extends Supplier2 {
    private int totalImports;
    private float totalImportValue;

    public StatSupplier2() {
        super();
    }

    public int getTotalImports() {
        return totalImports;
    }

    public void setTotalImports(int totalImports) {
        this.totalImports = totalImports;
    }

    public float getTotalImportValue() {
        return totalImportValue;
    }

    public void setTotalImportValue(float totalImportValue) {
        this.totalImportValue = totalImportValue;
    }
}
