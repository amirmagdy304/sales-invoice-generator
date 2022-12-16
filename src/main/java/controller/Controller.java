package controller;


import model.InvoiceHeader;
import model.InvoiceLine;

public class Controller {


    InvoiceHeader invoiceHeader = new InvoiceHeader();
    InvoiceLine invoiceLine = new InvoiceLine();

    public String[] getInvoiceHeaderCols() {
        String[] invoiceHeaderCols= invoiceHeader.getInvoiceHeaderCols();
        return invoiceHeaderCols;
    }

    public String[][] getInvoiceHeaderData() {
        String[][] invoiceHeaderData = invoiceHeader.getInvoiceHeaderData();
        return invoiceHeaderData;
    }

    public String[] getInvoiceDetailsCols() {
        String[] invoiceDetailsCols= invoiceLine.getInvoiceDetailsCols();
        return invoiceDetailsCols;
    }

    public String[][] getInvoiceDetailsData() {
        String[][] invoiceDetailsData = invoiceLine.getInvoiceDetailsData();
        return invoiceDetailsData;
    }
}
