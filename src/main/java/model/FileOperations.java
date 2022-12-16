package model;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.ArrayUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class FileOperations {
//    InvoiceHeader invoiceHeader = new InvoiceHeader();
//    public String invoiceHeaderFilePath = "D:/Git/SWCM CC/SIG/src/main/resources/InvoiceHeader.csv";
//    public String invoiceLinesFilePath = "D:/Git/SWCM CC/SIG/src/main/resources/InvoiceLines.csv";

    public String[] getFileHeader(String filePath) {
        CSVReader fileHeader = null;
        String [] tableHeader = null;

        try {
            fileHeader = new CSVReader(new FileReader(filePath));
            tableHeader = fileHeader.readNext();
            if (filePath.equals("D:/Git/SWCM CC/SIG/src/main/resources/InvoiceLines.csv")){
                tableHeader = ArrayUtils.removeElement(tableHeader, tableHeader[0]);
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileHeader != null;
                fileHeader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tableHeader;
    }

    public String[][] getInvoiceHeaderData(String invoiceHeaderFilePath) {
        CSVReader invoiceFile = null;
        String[][] myData = null;
        try {
            invoiceFile = new CSVReader(new FileReader(invoiceHeaderFilePath));
            myData = invoiceFile.readAll().toArray(new String[0][]);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } finally {
            try {
                assert invoiceFile != null;
                invoiceFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return myData;
    }

    public String[][] getInvoiceDetailsData(String invoiceNumber,String invoiceLinesFilePath) {
        CSVReader invoiceFile = null;
        List<String[]> myData = new ArrayList<String[]>();
        String[] currentRow;
        try {
            invoiceFile = new CSVReader(new FileReader(invoiceLinesFilePath));
            while ((currentRow = invoiceFile.readNext()) != null) {
                String invoiceNoColumn = currentRow[0];
                currentRow = ArrayUtils.removeElement(currentRow, currentRow[0]);
                if (invoiceNoColumn.equals(invoiceNumber)) {
                    myData.add(0, currentRow);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        } finally {
            try {
                assert invoiceFile != null;
                invoiceFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
        }
        return myData.toArray(new String[0][]);
    }


//    public static void main(String[] args) {
//        FileOperations fileOperations = new FileOperations();
//        fileOperations.getInvoiceDetailsData("SI1","D:/Git/SWCM CC/SIG/src/main/resources/InvoiceLines.csv");
//    }


}
