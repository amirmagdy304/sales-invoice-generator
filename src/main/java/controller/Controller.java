package controller;


import com.opencsv.CSVWriter;
import model.FileOperations;
import model.InvoiceHeader;
import model.InvoiceLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {


    MessageController messageController = new MessageController();
    private InvoiceHeader invoiceHeader;
    private InvoiceLine invoiceLine;
    private FileOperations fileOperations;

    public Controller(InvoiceHeader invoiceHeader, InvoiceLine invoiceLine, FileOperations fileOperations) {
        this.invoiceHeader = invoiceHeader;
        this.invoiceLine = invoiceLine;
        this.fileOperations = fileOperations;
    }

    public void loadFromFile(Component component, JTable table, String[] tableHeader) {
        DefaultTableModel tableModel = new DefaultTableModel(
                fileOperations.loadInvoiceDataFromFIleChooser(component),
                tableHeader);
        table.setModel(tableModel);
    }

    public void loadSelectedInvoiceLines(JTable invoicesTBL, JTable invoiceLinesTBL, JLabel invoiceNumberValueLBL
            , JTextField invoiceDateTXT, JTextField customerNameTXT, JLabel invoiceTotalValueLBL) {
        if (invoicesTBL.getSelectedRow() > -1 && invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 0) != null) {
            invoiceHeader.setInvoiceNumber(invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 0).toString());
            invoiceHeader.setInvoiceDate(invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 1).toString());
            invoiceHeader.setCustomerName(invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 2).toString());
            invoiceHeader.setTotalAmount(Double.parseDouble((invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 3).toString())));
            DefaultTableModel tableModel = getDefaultTableModel(invoiceHeader.getInvoiceNumber(), invoiceLine.getInvoiceLinesTableHeaders());
            invoiceLinesTBL.setModel(tableModel);
            invoiceNumberValueLBL.setText(invoiceHeader.getInvoiceNumber());
            invoiceDateTXT.setText(invoiceHeader.getInvoiceDate());
            customerNameTXT.setText(invoiceHeader.getCustomerName());
            invoiceTotalValueLBL.setText(String.valueOf(invoiceHeader.getTotalAmount()));
        }
    }

    private DefaultTableModel getDefaultTableModel(String invoiceNumber, String[] tableHeader) {
        return new DefaultTableModel(
                fileOperations.readTableData(invoiceLine.getInvoiceLinesFilePath(), invoiceNumber),
                tableHeader);
    }

    /**
     * this method used when user press on the Save button.
     *
     * @param invoiceNumberValueLBL invoiceNumberValueLBL
     * @param invoiceDateTXT        invoiceDateTXT
     * @param customerNameTXT       customerNameTXT
     * @param invoiceLinesTBL       invoiceLinesTBL
     * @param invoicesTBL           invoicesTBL
     */
    public void saveNewInvoice(JLabel invoiceNumberValueLBL,
                               JTextField invoiceDateTXT,
                               JTextField customerNameTXT,
                               JTable invoiceLinesTBL,
                               JTable invoicesTBL,
                               JLabel invoiceTotalValueLBL) {
        //      Add new Lines to the InvoiceLines.csv
        if (messageController.displayConfirmationMessage("are you sure you want to save new invoice") == 0) {
        Date invoiceDate = null;
        if (invoiceNumberValueLBL.getText()==null || !(invoiceTotalValueLBL.getText()==null)){
            messageController.displayErrorMessage("you should press on the 'Create new invoice' button first");
        }else if (invoiceDateTXT.getText().equals("")){
            messageController.displayErrorMessage("Invoice date is mandatory, please make sure to add \n correct date format dd/mm/yyyy");
        }else if(customerNameTXT.getText().equals("")){
            messageController.displayErrorMessage("Please enter the customer name");
        }else if(invoiceLinesTBL.getRowCount()<1){
            messageController.displayErrorMessage("Please add one item at least");
        }else{
            try {
                invoiceDate = new SimpleDateFormat("dd/MM/yyyy").parse(invoiceDateTXT.getText());
            } catch (ParseException e) {
                messageController.displayErrorMessage(e.getMessage());
                e.printStackTrace();
            }
            CSVWriter invoiceLinesWriter = null;
            double invoiceTotal = 0;
            try {
                invoiceLinesWriter = new CSVWriter(new FileWriter(invoiceLine.getInvoiceLinesFilePath(), true));
                DefaultTableModel invoiceLinesModel = (DefaultTableModel) invoiceLinesTBL.getModel();
                int rowCount = invoiceLinesModel.getRowCount();
                int currentRowNumber = 0;
                while (currentRowNumber < rowCount) {
//                String itemNumberValue = invoiceLinesModel.getValueAt(currentRowNumber, 0).toString();
                    invoiceLine.setItemNameValue(invoiceLinesModel.getValueAt(currentRowNumber, 1).toString());
                    invoiceLine.setItemPriceValue(Double.parseDouble(invoiceLinesModel.getValueAt(currentRowNumber, 2).toString()));
                    invoiceLine.setItemCountValue(Integer.parseInt(invoiceLinesModel.getValueAt(currentRowNumber, 3).toString()));
                    invoiceTotal += getLineTotal(invoiceLinesTBL, currentRowNumber);
                    invoiceLine.setItemTotal(Double.parseDouble(String.valueOf(getLineTotal(invoiceLinesTBL, currentRowNumber))));

                    String[] invoiceLineRecord = new String[]{invoiceNumberValueLBL.getText(),
//                        itemNumberValue,
                            invoiceLine.getItemNameValue(),
                            String.valueOf(invoiceLine.getItemPriceValue()),
                            String.valueOf(invoiceLine.getItemPriceValue()),
                            String.valueOf(invoiceLine.getItemTotal())};
                    invoiceLinesWriter.writeNext(invoiceLineRecord, false);
                    currentRowNumber++;
                }
            } catch (IOException e) {
                messageController.displayErrorMessage(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    assert invoiceLinesWriter != null;
                    invoiceLinesWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

//        Add new Line to the InvoiceHeader.csv
            CSVWriter invoiceWriter = null;
            try {
                invoiceWriter = new CSVWriter(new FileWriter(invoiceHeader.getInvoiceHeaderFilePath(), true));

                String[] invoiceRecord = new String[]{invoiceNumberValueLBL.getText(),
                        invoiceDate.toString(),
                        customerNameTXT.getText(),
                        String.valueOf(invoiceTotal)};
                invoiceWriter.writeNext(invoiceRecord, false);

            } catch (IOException e) {
                messageController.displayErrorMessage(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    assert invoiceWriter != null;
                    invoiceWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

//        Refresh the invoice header list so the new invoice reflect immediately on the GUI
            refreshTableData(invoicesTBL, invoiceHeader.getInvoiceHeaderFilePath(), invoiceHeader.getInvoiceTableHeaders());
            messageController.displayDescriptiveMessage("The invoice is saved successfully");
        }
        }
    }


    /**
     * get total per line
     *
     * @param invoiceLinesTBL  invoiceLinesTBL
     * @param currentRowNumber currentRowNumber
     * @return double {total per line }
     */
    public double getLineTotal(JTable invoiceLinesTBL, int currentRowNumber) {
        DefaultTableModel invoiceLinesModel = (DefaultTableModel) invoiceLinesTBL.getModel();
        double itemPrice = Double.parseDouble(((String) invoiceLinesModel.getValueAt(currentRowNumber, 2)));
        double itemCount = Double.parseDouble(((String) invoiceLinesModel.getValueAt(currentRowNumber, 3)));
        return itemPrice * itemCount;
    }


    public void createNewInvoice(JTable invoicesTBL,
                                 JTable invoiceLinesTBL,
                                 JLabel invoiceNumberValueLBL,
                                 JTextField invoiceDateTXT,
                                 JTextField customerNameTXT,
                                 JLabel invoiceTotalValueLBL) {

        if (invoiceHeader.getInvoiceHeaderFilePath()== null){
            messageController.displayErrorMessage("please load the InvoiceHeader.csv and InvoiceLines.csv first,\n press on File Menu ==> load File button");
        }else{
            messageController.displayDescriptiveMessage("Please fill the invoice data");
            clearLinesTable(invoiceNumberValueLBL,
                    invoiceLinesTBL,
                    invoiceDateTXT,
                    customerNameTXT,
                    invoiceTotalValueLBL);
            DefaultTableModel invoiceModel = (DefaultTableModel) invoicesTBL.getModel();
            invoiceNumberValueLBL.setText(String.valueOf((invoiceModel.getRowCount() + 1)));
            createNewItemRow(invoiceLinesTBL,invoiceTotalValueLBL,invoiceNumberValueLBL);
        }
    }

    public void createNewItemRow(JTable invoiceLinesTBL,JLabel invoiceTotalValueLBL,JLabel invoiceNumberValueLBL) {
        DefaultTableModel invoiceLinesModel = (DefaultTableModel) invoiceLinesTBL.getModel();
        if (invoiceTotalValueLBL.getText()==null && !(invoiceNumberValueLBL.getText()==null)){
            invoiceLinesModel.addRow(new String[invoiceLinesModel.getColumnCount()]);
        }else {

            messageController.displayErrorMessage("you should press on the 'Create new invoice' button first");
        }


    }

    public void deleteSelectedItemRow(JTable invoiceLinesTBL) {
        DefaultTableModel invoiceLinesModel = (DefaultTableModel) invoiceLinesTBL.getModel();

        int selectedRow = invoiceLinesTBL.getSelectedRow();
        if(selectedRow <0){
            messageController.displayErrorMessage("Please select any item row");
        }else {
            if (messageController.displayConfirmationMessage("are you sure you to delete this item line?") == 0){
                invoiceLinesModel.removeRow(selectedRow);
            }
        }


    }

    public void clearLinesTable(JLabel invoiceNumberValueLBL,
                                JTable invoiceLinesTBL,
                                JTextField invoiceDateTXT,
                                JTextField customerNameTXT,
                                JLabel invoiceTotalValueLBL) {
        invoiceNumberValueLBL.setText(null);
        invoiceDateTXT.setText(null);
        customerNameTXT.setText(null);
        invoiceTotalValueLBL.setText(null);

        DefaultTableModel invoiceLinesModel = (DefaultTableModel) invoiceLinesTBL.getModel();
        int rowCount = invoiceLinesModel.getRowCount();
        int currentRowNumber = 0;
        while (currentRowNumber < rowCount) {
            invoiceLinesModel.removeRow(0);
            currentRowNumber++;
        }

    }


    public void deleteSelectedInvoiceRows(JTable invoicesTBL, String invoiceLinesFilePath, String invoiceHeaderFilePath) {
        if (invoicesTBL.getSelectedRow()<0){
            messageController.displayErrorMessage("please select an invoice");
        }else {
            if ((messageController.displayConfirmationMessage("are you sure you want to delete this invoice") == 0)){
                fileOperations.deleteSelectedInvoiceRows(invoicesTBL, invoiceLinesFilePath);
                fileOperations.deleteSelectedInvoiceRows(invoicesTBL, invoiceHeaderFilePath);
                refreshTableData(invoicesTBL, invoiceHeaderFilePath, invoiceHeader.getInvoiceTableHeaders());
                messageController.displayDescriptiveMessage("The invoice is deleted successfully");
            }

        }

    }


    public void refreshTableData(JTable invoicesTBL, String filePath, String[] tableHeader) {
        DefaultTableModel invoicesTableModel = new DefaultTableModel(
                fileOperations.readTableData(filePath)
                , tableHeader);
        invoicesTBL.setModel(invoicesTableModel);
    }
}
