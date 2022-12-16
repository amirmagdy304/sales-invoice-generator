package model;

public class InvoiceLine {

    FileOperations fileOperations = new FileOperations();



    private String invoiceLinesFilePath = "D:/Git/SWCM CC/SIG/src/main/resources/InvoiceLines.csv";
    private String[] invoiceDetailsCols = fileOperations.getFileHeader(invoiceLinesFilePath);
    private String[][] invoiceDetailsData = fileOperations.getInvoiceDetailsData("SI1",invoiceLinesFilePath);

    public String[] getInvoiceDetailsCols() {
        return invoiceDetailsCols;
    }

    public String[][] getInvoiceDetailsData() {
        return invoiceDetailsData;
    }
}
