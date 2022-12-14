package view;


import controller.Controller;
import controller.MessageController;
import model.FileOperations;
import model.InvoiceHeader;
import model.InvoiceLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


class View extends JFrame {


//    Variables declaration


    InvoiceLine invoiceLine = new InvoiceLine();
    InvoiceHeader invoiceHeader = new InvoiceHeader();
    MessageController messageController = new MessageController();
    FileOperations fileOperations = new FileOperations(invoiceHeader, invoiceLine);
    Controller controller = new Controller(invoiceHeader, invoiceLine, fileOperations);
    //  Declare menu bar
    private JMenuBar MenuBar;
    private JMenu fileMenu;
    private JMenuItem loadFileMenuBtn;
    private JMenuItem saveFileMenuBtn;
    //  Declare main panels
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    //    Declare invoices header
    private JLabel invoicesTableTitleLBL;
    private JScrollPane invoicesTBLScrollPane;
    private JTable invoicesTBL;
    private JButton createNewInvoiceBtn;
    private JButton deleteInvoiceBtn;
    //    Declare invoice lines
    private JLabel invoiceNumberLBL;
    private JLabel invoiceNumberValueLBL;
    private JLabel invoiceDateLBL;
    private JTextField invoiceDateTXT;
    private JLabel customerNameLBL;
    private JTextField customerNameTXT;
    private JLabel invoiceTotalLBL;
    private JLabel invoiceTotalValueLBL;
    private JLabel invoicesItemsTableTitleLBL;
    private JButton deleteItemBtn;
    private JButton createItemBtn;


    // End of variables declaration
    private JScrollPane invoiceItemTBLScrollPane;
    private JTable invoiceLinesTBL;
    private JButton saveBtn;
    private JButton cancelBtn;

    public View() {
        initComponents();
    }

    public static void main(String[] args) {
        View window = new View();
        window.setTitle("Sales Invoice Generator");
        window.pack();
        window.setVisible(true);
    }

    private void initComponents() {
//       menu bar
        MenuBar = new JMenuBar();
        fileMenu = new JMenu();
        loadFileMenuBtn = new JMenuItem();
        saveFileMenuBtn = new JMenuItem();

//          main panels
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
//          invoices header
        invoicesTableTitleLBL = new JLabel();
        invoicesTBLScrollPane = new JScrollPane();

        invoicesTBL = new JTable();
        DefaultTableModel invoicesTableModel = new DefaultTableModel(null, invoiceHeader.getInvoiceTableHeaders());
        invoicesTBL.setModel(invoicesTableModel);
        invoicesTBL.getSelectionModel().addListSelectionListener(evt -> selectRowActionPerformed());

        createNewInvoiceBtn = new JButton();
        deleteInvoiceBtn = new JButton();
//          invoice lines
        invoiceNumberLBL = new JLabel();
        invoiceNumberValueLBL = new JLabel();
        invoiceDateLBL = new JLabel();
        invoiceDateTXT = new JTextField();
        customerNameLBL = new JLabel();
        customerNameTXT = new JTextField();
        invoiceTotalLBL = new JLabel();
        invoiceTotalValueLBL = new JLabel();
        invoicesItemsTableTitleLBL = new JLabel();
        deleteItemBtn = new javax.swing.JButton();
        createItemBtn = new javax.swing.JButton();
        invoiceItemTBLScrollPane = new JScrollPane();

        invoiceLinesTBL = new JTable();
        DefaultTableModel invoicesLinesTableModel = new DefaultTableModel(null, invoiceLine.getInvoiceLinesTableHeaders());
        invoiceLinesTBL.setModel(invoicesLinesTableModel);

        saveBtn = new JButton();
        cancelBtn = new JButton();


        invoicesTBLScrollPane.setViewportView(invoicesTBL);
        createNewInvoiceBtn.setText("Create New Invoice");
        createNewInvoiceBtn.addActionListener(evt -> createNewInvoiceBtnActionPerformed());

        deleteInvoiceBtn.setText("Delete Invoice");
        deleteInvoiceBtn.addActionListener(
                evt -> deleteInvoiceBtnActionPerformed());

        invoicesTableTitleLBL.setText("Invoices Table");

        GroupLayout leftPanelLayout = new GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
                leftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addGroup(leftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(invoicesTBLScrollPane)
                                        .addGroup(leftPanelLayout.createSequentialGroup()
                                                .addGroup(leftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(leftPanelLayout.createSequentialGroup()
                                                                .addGap(75, 75, 75)
                                                                .addComponent(createNewInvoiceBtn, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(80, 80, 80)
                                                                .addComponent(deleteInvoiceBtn, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(leftPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(invoicesTableTitleLBL, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 130, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
                leftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(invoicesTableTitleLBL)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(invoicesTBLScrollPane)
                                .addGap(18, 18, 18)
                                .addGroup(leftPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(createNewInvoiceBtn)
                                        .addComponent(deleteInvoiceBtn))
                                .addContainerGap())
        );

        invoiceNumberLBL.setText("Invoice Number");
        invoiceNumberValueLBL.setText(null);
        invoiceDateLBL.setText("Invoice Date");
        invoiceDateTXT.setText(null);

        customerNameLBL.setText("Customer Name");
        customerNameTXT.setText(null);
        invoiceTotalLBL.setText("Invoice Total");
        invoiceTotalValueLBL.setText(null);

        invoiceItemTBLScrollPane.setViewportView(invoiceLinesTBL);
        saveBtn.setText("Save Invoice");
        saveBtn.addActionListener(
                evt -> saveBtnActionPerformed());

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(
                evt -> cancelBtnActionPerformed());

        invoicesItemsTableTitleLBL.setText("Invoice Items");
        deleteItemBtn.setText("Delete Line");
        deleteItemBtn.addActionListener(this::deleteItemBtnActionPerformed);

        createItemBtn.setText("Create Item");
        createItemBtn.addActionListener(this::createItemBtnActionPerformed);

        GroupLayout rightPanelLayout = new GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(invoiceTotalLBL, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(customerNameLBL, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                        .addComponent(invoiceDateLBL, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(invoiceNumberLBL, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(invoiceNumberValueLBL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(invoiceDateTXT, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                        .addComponent(customerNameTXT)
                                        .addComponent(invoiceTotalValueLBL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(invoiceItemTBLScrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                                        .addGroup(rightPanelLayout.createSequentialGroup()
                                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(rightPanelLayout.createSequentialGroup()
                                                                .addGap(92, 92, 92)
                                                                .addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(69, 69, 69)
                                                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(rightPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(invoicesItemsTableTitleLBL, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(createItemBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(deleteItemBtn)))
                                .addContainerGap()));
        rightPanelLayout.setVerticalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(invoiceNumberLBL)
                                        .addComponent(invoiceNumberValueLBL))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(invoiceDateLBL)
                                        .addComponent(invoiceDateTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(customerNameLBL)
                                        .addComponent(customerNameTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(invoiceTotalLBL)
                                        .addComponent(invoiceTotalValueLBL))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(invoicesItemsTableTitleLBL)
                                .addComponent(deleteItemBtn)
                                .addComponent(createItemBtn))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(invoiceItemTBLScrollPane, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveBtn)
                                        .addComponent(cancelBtn))
                                .addContainerGap())
        );

        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        fileMenu.setText("File");

        loadFileMenuBtn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        loadFileMenuBtn.setText("Load File");
        loadFileMenuBtn.addActionListener(
                evt -> loadFileMenuBtnActionPerformed());
        fileMenu.add(loadFileMenuBtn);

        saveFileMenuBtn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveFileMenuBtn.setText("Save FIle");
        saveFileMenuBtn.addActionListener(
                evt -> saveFileMenuBtnActionPerformed());
        fileMenu.add(saveFileMenuBtn);

        MenuBar.add(fileMenu);

        setJMenuBar(MenuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    private void createNewInvoiceBtnActionPerformed() {
        controller.createNewInvoice(invoicesTBL,
                invoiceLinesTBL,
                invoiceNumberValueLBL,
                invoiceDateTXT,
                customerNameTXT,
                invoiceTotalValueLBL);

    }

    private void deleteInvoiceBtnActionPerformed() {

            controller.deleteSelectedInvoiceRows(invoicesTBL, invoiceLine.getInvoiceLinesFilePath(), invoiceHeader.getInvoiceHeaderFilePath());
            controller.clearLinesTable(invoiceNumberValueLBL,
                    invoiceLinesTBL,
                    invoiceDateTXT,
                    customerNameTXT,
                    invoiceTotalValueLBL);
    }


    private void saveBtnActionPerformed() {

            controller.saveNewInvoice(invoiceNumberValueLBL,
                    invoiceDateTXT,
                    customerNameTXT,
                    invoiceLinesTBL,
                    invoicesTBL,
                    invoiceTotalValueLBL);
    }

    private void cancelBtnActionPerformed() {
        if (messageController.displayConfirmationMessage("are you sure you want to cancel") == 0) {
            controller.clearLinesTable(invoiceNumberValueLBL,
                    invoiceLinesTBL,
                    invoiceDateTXT,
                    customerNameTXT,
                    invoiceTotalValueLBL);
            messageController.displayDescriptiveMessage("The invoice is cancelled successfully");
        }

    }

    private void loadFileMenuBtnActionPerformed() {
        messageController.displayDescriptiveMessage("Please select the InvoiceHeader.csv file");
        controller.loadFromFile(this, invoicesTBL, invoiceHeader.getInvoiceTableHeaders());
        messageController.displayDescriptiveMessage("Please select the InvoiceLines.csv file");
        controller.loadFromFile(this, invoiceLinesTBL, invoiceLine.getInvoiceLinesTableHeaders());
        messageController.displayDescriptiveMessage("CSV files are loaded successfully");
    }

    private void selectRowActionPerformed() {

        controller.loadSelectedInvoiceLines(invoicesTBL,
                invoiceLinesTBL,
                invoiceNumberValueLBL,
                invoiceDateTXT,
                customerNameTXT,
                invoiceTotalValueLBL);
    }

    private void saveFileMenuBtnActionPerformed() {
        messageController.displayDescriptiveMessage("Please select path to Save InvoiceHeader.csv file");
        fileOperations.saveInvoiceDataFromFIleChooser(this, invoiceHeader.getInvoiceHeaderFilePath(), invoiceHeader.getInvoiceTableHeaders());
        messageController.displayDescriptiveMessage("Please select path to Save InvoiceHeader.csv file");
        fileOperations.saveInvoiceDataFromFIleChooser(this, invoiceLine.getInvoiceLinesFilePath(), invoiceLine.getInvoiceLinesTableHeaders());
    }

    /**
     *Used while creating a new invoice
     * on pressing it the system will add a new empty item row
     */

    private void createItemBtnActionPerformed(java.awt.event.ActionEvent evt) {
        controller.createNewItemRow(invoiceLinesTBL,invoiceTotalValueLBL,invoiceNumberValueLBL);
    }

    /**
     * Used while creating a new invoice,
     * on pressing it the system will delete the selected item row.
     *
     */
    private void deleteItemBtnActionPerformed(java.awt.event.ActionEvent evt) {
        controller.deleteSelectedItemRow(invoiceLinesTBL,invoiceNumberValueLBL,invoiceLine.getInvoiceLinesFilePath());
    }

}
