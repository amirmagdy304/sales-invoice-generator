package model;

public class InvoiceHeader {

    String InvoiceNumber;
    String invoiceDate;
    String customerName;
    double totalAmount;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }



    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }


    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getInvoiceHeaderFilePath() {
        return "src/main/resources/InvoiceHeader.csv";
    }

}
