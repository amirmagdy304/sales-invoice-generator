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


    InvoiceHeader invoiceHeader = new InvoiceHeader();
    InvoiceLine invoiceLine = new InvoiceLine();
    FileOperations fileOperations = new FileOperations();
    MessageController messageController = new MessageController();

    public void loadFromFile(Component component, JTable table) {
        DefaultTableModel tableModel = new DefaultTableModel(fileOperations.loadInvoiceDataFromFIleChooser(component),
                fileOperations.readTableHeader(invoiceHeader.getInvoiceHeaderFilePath()));
        table.setModel(tableModel);
    }

    public void loadSelectedInvoiceLines(JTable invoicesTBL, JTable invoiceLinesTBL, JLabel invoiceNumberValueLBL
                                   , JTextField invoiceDateTXT, JTextField customerNameTXT, JLabel invoiceTotalValueLBL )
    {
        if (invoicesTBL.getSelectedRow() > -1 && invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 0)!=null) {
            invoiceHeader.setInvoiceNumber(invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 0).toString()) ;
            invoiceHeader.setInvoiceDate(invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 1).toString());
            invoiceHeader.setCustomerName(invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 2).toString());
            invoiceHeader.setTotalAmount(Double.parseDouble((invoicesTBL.getValueAt(invoicesTBL.getSelectedRow(), 3).toString())));
            DefaultTableModel tableModel = getDefaultTableModel(invoiceHeader.getInvoiceNumber());
            invoiceLinesTBL.setModel(tableModel);
            invoiceNumberValueLBL.setText(invoiceHeader.getInvoiceNumber());
            invoiceDateTXT.setText(invoiceHeader.getInvoiceDate());
            customerNameTXT.setText(invoiceHeader.getCustomerName());
            invoiceTotalValueLBL.setText(String.valueOf(invoiceHeader.getTotalAmount()));
        }
    }

    private DefaultTableModel getDefaultTableModel(String invoiceNumber) {
        return new DefaultTableModel(
                        fileOperations.readTableData(invoiceLine.getInvoiceLinesFilePath(),invoiceNumber),
                        fileOperations.readTableHeader(invoiceLine.getInvoiceLinesFilePath()));
    }

    /**
     * this method used when user press on the Save button.
     * @param invoiceNumberValueLBL invoiceNumberValueLBL
     * @param invoiceDateTXT invoiceDateTXT
     * @param customerNameTXT customerNameTXT
     * @param invoiceLinesTBL invoiceLinesTBL
     * @param invoicesTBL invoicesTBL
     */
    public void saveNewInvoice(JLabel invoiceNumberValueLBL,
                               JTextField invoiceDateTXT,
                               JTextField customerNameTXT,
                               JTable invoiceLinesTBL,
                               JTable invoicesTBL)
    {
        //      Add new Lines to the InvoiceLines.csv

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
                invoiceTotal += getLineTotal(invoiceLinesTBL,currentRowNumber);
                invoiceLine.setItemTotal(Double.parseDouble(String.valueOf(getLineTotal(invoiceLinesTBL,currentRowNumber))));

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
            Date invoiceDate=null;
            try {
                invoiceDate=new SimpleDateFormat("dd/MM/yyyy").parse(invoiceDateTXT.getText());
            } catch (ParseException e) {
                messageController.displayErrorMessage(e.getMessage());
                e.printStackTrace();
            }
            String[] invoiceRecord = new String[]{  invoiceNumberValueLBL.getText(),
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
        refreshTableData(invoicesTBL,invoiceHeader.getInvoiceHeaderFilePath());
    }


    /**
     * get total per line
     * @param invoiceLinesTBL invoiceLinesTBL
     * @param currentRowNumber currentRowNumber
     * @return double {total per line }
     */
    public double getLineTotal(JTable invoiceLinesTBL,int currentRowNumber){
        DefaultTableModel invoiceLinesModel = (DefaultTableModel)invoiceLinesTBL.getModel();
        double itemPrice= Double.parseDouble(((String) invoiceLinesModel.getValueAt(currentRowNumber,2)));
        double itemCount= Double.parseDouble(((String) invoiceLinesModel.getValueAt(currentRowNumber,3)));
        return itemPrice*itemCount;
    }


    public void createNewInvoice(JTable invoicesTBL,
                                 JTable invoiceLinesTBL,
                                 JLabel invoiceNumberValueLBL,
                                 JTextField invoiceDateTXT,
                                 JTextField customerNameTXT,
                                 JLabel invoiceTotalValueLBL){

        clearLinesTable(invoiceNumberValueLBL,
                        invoiceLinesTBL,
                        invoiceDateTXT,
                        customerNameTXT,
                        invoiceTotalValueLBL);

        DefaultTableModel invoiceModel = (DefaultTableModel)invoicesTBL.getModel();
        invoiceNumberValueLBL.setText(String.valueOf((invoiceModel.getRowCount()+1)));
        DefaultTableModel invoiceLinesModel = (DefaultTableModel)invoiceLinesTBL.getModel();
        invoiceLinesModel.addRow(new String[invoiceLinesModel.getColumnCount()]);
        invoiceLinesModel.addRow(new String[invoiceLinesModel.getColumnCount()]);
    }

    public void clearLinesTable(JLabel invoiceNumberValueLBL,
                                JTable invoiceLinesTBL,
                                JTextField invoiceDateTXT,
                                JTextField customerNameTXT,
                                JLabel invoiceTotalValueLBL) {
        invoiceNumberValueLBL.setText("");
        invoiceDateTXT.setText("");
        customerNameTXT.setText("");
        invoiceTotalValueLBL.setText("");

        DefaultTableModel invoiceLinesModel = (DefaultTableModel)invoiceLinesTBL.getModel();
        int rowCount = invoiceLinesModel.getRowCount();
        int currentRowNumber = 0;
        while(currentRowNumber < rowCount){
            invoiceLinesModel.removeRow(0);
            currentRowNumber++;
        }

    }


    public void deleteSelectedRows(JTable invoicesTBL, String invoiceLinesFilePath, String invoiceHeaderFilePath) {
        fileOperations.deleteSelectedRows(invoicesTBL,invoiceLinesFilePath);
        fileOperations.deleteSelectedRows(invoicesTBL,invoiceHeaderFilePath);
        refreshTableData(invoicesTBL,invoiceHeaderFilePath);
    }


    public void refreshTableData(JTable invoicesTBL,String filePath) {
        DefaultTableModel invoicesTableModel = new DefaultTableModel(
                fileOperations.readTableData(filePath)
                , fileOperations.readTableHeader(filePath));
        invoicesTBL.setModel(invoicesTableModel);
    }
}
