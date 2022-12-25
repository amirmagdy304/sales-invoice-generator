package model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import controller.Controller;
import controller.MessageController;
import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FileOperations {
    MessageController messageController = new MessageController();
    private InvoiceLine invoiceLine;
    private InvoiceHeader invoiceHeader;


    public FileOperations(InvoiceHeader invoiceHeader, InvoiceLine invoiceLine) {
//        super();
        this.invoiceHeader = invoiceHeader;
        this.invoiceLine = invoiceLine;
    }


    public void writeFile(String invoiceHeaderFilePath, List<String[]> myData) {
        CSVWriter invoiceFile = null;
        try {
            if (!(invoiceHeaderFilePath.contains(".csv"))) {
                messageController.displayErrorMessage("Wrong file format");
            }
            invoiceFile = new CSVWriter(new FileWriter(invoiceHeaderFilePath));
            invoiceFile.writeAll(myData);
        } catch (IOException e) {
            messageController.displayErrorMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                assert invoiceFile != null;
                invoiceFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * used on the first run.
     * used to get the invoice header data
     *
     * @param invoiceHeaderFilePath CSV File path invoiceHeaderFilePath
     * @return String[][]
     */
    public String[][] readTableData(String invoiceHeaderFilePath) {
        CSVReader invoiceFile = null;
        List<String[]> myData = new ArrayList<>();
        String[] currentRow;
        try {

            invoiceFile = new CSVReader(new FileReader(invoiceHeaderFilePath));

            invoiceFile.readNext();
            while ((currentRow = invoiceFile.readNext()) != null) {
                myData.add(0, currentRow);
            }
        } catch (IOException | CsvValidationException e) {

            messageController.displayErrorMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                assert invoiceFile != null;
                invoiceFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myData.toArray(new String[0][]);
    }

    /**
     * used to get the table header.
     * for the invoice line table ,the first column (Invoice number) will be removed from the display.
     * @param filePath CSV file Path
     * @return String[]
     */

//    public String[] readTableHeader(String filePath) {
//        CSVReader fileHeader = null;
//        String [] tableHeader = null;
//
//        try {
//            fileHeader = new CSVReader(new FileReader(filePath));
//            tableHeader = fileHeader.readNext();
////            Add below code in order to remove the first column [Invoice Number] from the invoice line sheet.
//            if (filePath.equals("src/main/resources/InvoiceLines.csv")){
//                tableHeader = ArrayUtils.removeElement(tableHeader, tableHeader[0]);
//            }
//        } catch (CsvValidationException | IOException e) {
//            messageController.displayErrorMessage(e.getMessage());
//            e.printStackTrace();
//        } finally {
//            try {
//                assert fileHeader != null;
//                fileHeader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return tableHeader;
//    }


    /**
     * used on the first run.
     * used to get invoice lines filtered by invoice number
     *
     * @param invoiceNumber        invoice Number which will be used on the filtration
     * @param invoiceLinesFilePath CSV file path invoiceLinesFilePath
     * @return String[][]
     */
    public String[][] readTableData(String invoiceLinesFilePath, String invoiceNumber) {
        CSVReader invoiceFile = null;
        List<String[]> myData = new ArrayList<>();
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
            messageController.displayErrorMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                assert invoiceFile != null;
                invoiceFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myData.toArray(new String[0][]);
    }


    /**
     * used on press the load button
     * update the invoices' header table.
     *
     * @param component component
     * @return String[][]
     */
    public String[][] loadInvoiceDataFromFIleChooser(Component component) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(component);
        String filePath = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getPath();
//            to ensure that selected file name is correct
            if (filePath.contains("InvoiceHeader.csv")) {
                invoiceHeader.setInvoiceHeaderFilePath(filePath);
            }
            if (filePath.contains("InvoiceLines.csv")) {
                invoiceLine.setInvoiceLinesFilePath(filePath);
            }
            String fileType = fileChooser.getTypeDescription(fileChooser.getSelectedFile());
            if (!(fileType.equals("Microsoft Excel Comma Separated Values File"))) {
                filePath = null;
                messageController.displayErrorMessage("Wrong file format, Please select CSV comma delimited file.");
            } else {

            }

        }
        return readTableData(filePath);

    }


    public void saveInvoiceDataFromFIleChooser(Component component, String filePath, String[] tableHeader) {

        List<String[]> myData = new ArrayList<>();
        myData.add(tableHeader);
        Collections.addAll(myData, readTableData(filePath));
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(component);
        String selectedFilePath;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFilePath = fileChooser.getSelectedFile().getPath();
//            String fileType = fileChooser.getName();
//                    getTypeDescription(fileChooser.getSelectedFile());
//            if (!(fileType.contains(".csv"))){
//                selectedFilePath = null;
//                messageController.displayErrorMessage("Wrong file format, Please select CSV comma delimited file.");
//            }else {
            writeFile(selectedFilePath, myData);
//            }
        }
    }

    /**
     * Delete Selected Invoice from both CSV files
     *
     * @param invoicesTBL invoicesTBL
     * @param filePath    filePath
     */
    public void deleteSelectedInvoiceRows(JTable invoicesTBL, String filePath) {

        String selectedInvoiceNumber = invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 0).toString();
        CSVReader readerAll = null;
        CSVReader readerRemove = null;
        List<String[]> myData = new ArrayList<>();
        String[] currentRow;
        try {
            readerAll = new CSVReader(new FileReader(filePath));
            myData = readerAll.readAll();
            readerRemove = new CSVReader(new FileReader(filePath));
            int currentRowIndex = 0;
            while ((currentRow = readerRemove.readNext()) != null) {
                String invoiceNumberValue = currentRow[0];
                if ((invoiceNumberValue.equals(selectedInvoiceNumber))) {
                    myData.remove(currentRowIndex);
                    currentRowIndex--;
                }
                currentRowIndex++;
            }
        } catch (IOException | CsvException e) {
            messageController.displayErrorMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                assert readerRemove != null;
                readerRemove.close();
                readerAll.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeFile(filePath, myData);
    }

    /**
     * used this method on delete item lines only without deleting the invoice [on press delete item button]
     * @param filePath
     * @param invoiceNumber
     * @param itemNumber
     */
    public void deleteSelectedInvoiceRows(String filePath,String invoiceNumber, String itemNumber) {

        CSVReader readerAll = null;
        CSVReader readerRemove = null;
        List<String[]> myData = new ArrayList<>();
        String[] currentRow;
        try {
            readerAll = new CSVReader(new FileReader(filePath));
            myData = readerAll.readAll();
            readerRemove = new CSVReader(new FileReader(filePath));
            int currentRowIndex = 0;
            while ((currentRow = readerRemove.readNext()) != null) {
                String invoiceNumberValue = currentRow[0];
                String itemNumberValue = currentRow[1];
                if ((invoiceNumberValue.equals(invoiceNumber))&& itemNumber.equals(itemNumberValue)) {
                    myData.remove(currentRowIndex);
                    currentRowIndex--;
                }
                currentRowIndex++;
            }
        } catch (IOException | CsvException e) {
            messageController.displayErrorMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                assert readerRemove != null;
                readerRemove.close();
                readerAll.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeFile(filePath, myData);
    }

    public static void main(String[] args) {
        InvoiceLine invoiceLine = new InvoiceLine();
        InvoiceHeader invoiceHeader = new InvoiceHeader();
        MessageController messageController = new MessageController();
        FileOperations fileOperations = new FileOperations(invoiceHeader, invoiceLine);
        Controller controller = new Controller(invoiceHeader, invoiceLine, fileOperations);



    }
}
