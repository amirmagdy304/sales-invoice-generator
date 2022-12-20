package model;

public class InvoiceHeader {

    String[] invoiceTableHeaders = {"Invoice No.", "Invoice Date", "Customer Name", "Total Amount"};
    String InvoiceNumber;
    String invoiceDate;
    String customerName;
    double totalAmount;
    String invoiceHeaderFilePath;

    public String[] getInvoiceTableHeaders() {
        return invoiceTableHeaders;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceHeaderFilePath() {
        return invoiceHeaderFilePath;
    }

    public void setInvoiceHeaderFilePath(String invoiceHeaderFilePath) {
        this.invoiceHeaderFilePath = invoiceHeaderFilePath;
    }

}
