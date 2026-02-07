package com.cafeteria.billing;

public class TaxConfig {
    private static TaxConfig instance;
    private double taxRate = 0.15;

    private TaxConfig() {}

    public static TaxConfig getInstance() {
        if (instance == null) {
            instance = new TaxConfig();
        }
        return instance;
    }
    public double calculateTotalWithTax(double subtotal) {
        return subtotal * 1.15;
    }

    public double getTaxRate() { return taxRate; }
    public void setTaxRate(double rate) { this.taxRate = rate; }
}
