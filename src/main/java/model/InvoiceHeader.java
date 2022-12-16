package model;

public class InvoiceHeader {

    FileOperations fileOperations = new FileOperations();

    private String invoiceHeaderFilePath = "D:/Git/SWCM CC/SIG/src/main/resources/InvoiceHeader.csv";
    private String[] invoiceHeaderCols = fileOperations.getFileHeader(invoiceHeaderFilePath);
    private String[][] invoiceHeaderData = fileOperations.getInvoiceHeaderData(invoiceHeaderFilePath);



    public String[] getInvoiceHeaderCols() {
        return invoiceHeaderCols;
    }

    public String[][] getInvoiceHeaderData() {
        return invoiceHeaderData;
    }



//    private String[] InvoiceLines() {
//        return new String[0];
//    }
}
