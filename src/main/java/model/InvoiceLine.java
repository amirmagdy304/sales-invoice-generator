package model;

public class InvoiceLine {


    String itemNameValue;
    double itemPriceValue;
    int itemCountValue;
    double itemTotal;

    public String getItemNameValue() {
        return itemNameValue;
    }

    public void setItemNameValue(String itemNameValue) {
        this.itemNameValue = itemNameValue;
    }

    public double getItemPriceValue() {
        return itemPriceValue;
    }

    public void setItemPriceValue(double itemPriceValue) {
        this.itemPriceValue = itemPriceValue;
    }

    public int getItemCountValue() {
        return itemCountValue;
    }

    public void setItemCountValue(int itemCountValue) {
        this.itemCountValue = itemCountValue;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public String getInvoiceLinesFilePath() {
        return "src/main/resources/InvoiceLines.csv";
    }
}
