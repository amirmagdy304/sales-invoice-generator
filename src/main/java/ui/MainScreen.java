package ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class MainScreen extends javax.swing.JFrame {

    //  Menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem loadFileMenuItem, saveFileMenuItem;
    private JSplitPane splitPaneVertical;
// Invoice Header
    private JPanel invoiceHeaderTablePNL;
    private JTable invoiceHeaderTable;
    String[] invoiceHeaderCols = {"No.", "Date", "Customer", "Total"};
    String[][] invoiceHeaderData = {
            {"1", "Amir", "Excellent", "4"},
            {"2", "Remonda", "Very Good", "4"},
            {"3", "Carl", "Good", "4"},
            {"4", "Patrick", "Very Good", "4"}};
    private JPanel invoiceHeaderBtnPNL;
    private JButton createNewInvoiceBtn,deleteInvoiceBtn;


//    Invoice Details
    private JPanel invoiceDetailsTfPNL;
    private JTextField invoiceDate,customerName;
    private JPanel invoiceDetailsTablePNL;
    private JTable invoiceDetailsTable;
    String[] invoiceDetailsCols = {"No.", "Item Name", "Item Price", "Count", "Item Total"};
    String[][] invoiceDetailsData = {
            {"1", "Amir", "Excellent", "4", "4"},
            {"2", "Remonda", "Very Good", "4", "4"},
            {"3", "Carl", "Good", "4", "4"},
            {"4", "Patrick", "Very Good", "4", "4"}};
    private JPanel invoiceDetailsBtnPNL;
    private JButton saveInvoiceBtn, cancelInvoiceBtn;

    public MainScreen() {
        MainScreenLayout customLayout = new MainScreenLayout();

        getContentPane().setFont(new Font("Helvetica", Font.PLAIN, 12));
        getContentPane().setLayout(customLayout);

//        Add Menu Bar.
        menuBar = new JMenuBar();
        menu = new JMenu("File");

        loadFileMenuItem = new JMenuItem("Load File", 'o');
        loadFileMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
//        loadFileMenuItem.addActionListener(this);
        loadFileMenuItem.setActionCommand("O");

        saveFileMenuItem = new JMenuItem("Save File", 's');
        saveFileMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
//        saveFileMenuItem.addActionListener(this);
        saveFileMenuItem.setActionCommand("S");

        menuBar.add(menu);
        menu.add(loadFileMenuItem);
        menu.add(saveFileMenuItem);
        setJMenuBar(menuBar);

//       Invoice Header Table
        invoiceHeaderTablePNL = new JPanel();
        invoiceHeaderTable = new JTable(invoiceHeaderData, invoiceHeaderCols);
        invoiceHeaderTable.setBounds(0,0,592,656);
        invoiceHeaderTablePNL.add(new JScrollPane(invoiceHeaderTable));
        getContentPane().add(invoiceHeaderTablePNL);

//        Invoice Header Buttons
        invoiceHeaderBtnPNL = new JPanel();
        getContentPane().add(invoiceHeaderBtnPNL);

        createNewInvoiceBtn = new JButton("Create New Invoice");
        invoiceHeaderBtnPNL.add(createNewInvoiceBtn);

        deleteInvoiceBtn = new JButton("Delete Invoice");
        invoiceHeaderBtnPNL.add(deleteInvoiceBtn);

//        Invoice Details Fields
        invoiceDetailsTfPNL = new JPanel();
        invoiceDetailsTfPNL.setLayout(new GridLayout(4,2));

        invoiceDetailsTfPNL.add(new JLabel("Invoice Number"));
        invoiceDetailsTfPNL.add(new JLabel("23"));

        invoiceDetailsTfPNL.add(new JLabel("Invoice Date"));
        invoiceDate = new JTextField(1);
        invoiceDetailsTfPNL.add(invoiceDate);

        invoiceDetailsTfPNL.add(new JLabel("Customer Name"));
        customerName = new JTextField(1);
        invoiceDetailsTfPNL.add(customerName);

        invoiceDetailsTfPNL.add(new JLabel("Invoice Total"));
        invoiceDetailsTfPNL.add(new JLabel("350.50"));

        getContentPane().add(invoiceDetailsTfPNL);

//        Add Invoice Details Table
        invoiceDetailsTablePNL = new JPanel();
        invoiceDetailsTable = new JTable(invoiceDetailsData, invoiceDetailsCols);
        invoiceDetailsTablePNL.add(new JScrollPane(invoiceDetailsTable));
        getContentPane().add(invoiceDetailsTablePNL);

//        Add Invoice Details Buttons
        invoiceDetailsBtnPNL = new JPanel();
        saveInvoiceBtn = new JButton("Save");
        invoiceDetailsBtnPNL.add(saveInvoiceBtn);

        cancelInvoiceBtn = new JButton("Cancel");
        invoiceDetailsBtnPNL.add(cancelInvoiceBtn);

        getContentPane().add(invoiceDetailsBtnPNL);




        setSize(getPreferredSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        MainScreen window = new MainScreen();
        window.setTitle("Sales Invoice Generator");
        window.pack();
        window.setVisible(true);
    }
}

