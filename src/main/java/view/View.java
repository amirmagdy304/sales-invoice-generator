package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import static java.awt.EventQueue.invokeLater;

class View extends JFrame {


//    Variables declaration

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
    private JScrollPane invoiceItemTBLScrollPane;
    private JTable invoiceItemTBL;
    private JButton saveBtn;
    private JButton cancelBtn;

    // End of variables declaration

    public View() {
        initComponents();
    }

    Controller controller = new Controller();

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
        invoicesTBL = new JTable(controller.getInvoiceHeaderData(),controller.getInvoiceHeaderCols());
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
        invoiceItemTBLScrollPane = new JScrollPane();
        invoiceItemTBL = new JTable(controller.getInvoiceDetailsData(),controller.getInvoiceDetailsCols());
        saveBtn = new JButton();
        cancelBtn = new JButton();



//        invoicesTBL.setModel(new table.DefaultTableModel(
//                new Object[][]{fileOperations.getInvoiceHeaderData()},
//                new String[]{String.valueOf(fileOperations.getFileHeader(fileOperations.invoiceHeaderFilePath))}
//        ));

        invoicesTBLScrollPane.setViewportView(invoicesTBL);
        createNewInvoiceBtn.setText("Create New Invoice");
        createNewInvoiceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                createNewInvoiceBtnActionPerformed(evt);
            }
        });

        deleteInvoiceBtn.setText("Delete Invoice");
        deleteInvoiceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteInvoiceBtnActionPerformed(evt);
            }
        });

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
        invoiceNumberValueLBL.setText("23");
        invoiceDateLBL.setText("Invoice Date");
        invoiceDateTXT.setText("1/1/2022");
        invoiceDateTXT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                invoiceDateTXTActionPerformed(evt);
            }
        });

        customerNameLBL.setText("Customer Name");
        customerNameTXT.setText("Amir");
        invoiceTotalLBL.setText("Invoice Total");
        invoiceTotalValueLBL.setText("350.50");

//        invoiceItemTBL.setModel(new table.DefaultTableModel(
//                new Object[][]{
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null},
//                        {null, null, null, null}
//                },
//                new String[]{
//                        "Title 1", "Title 2", "Title 3", "Title 4"
//                }
//        ));
        invoiceItemTBLScrollPane.setViewportView(invoiceItemTBL);
        saveBtn.setText("Save");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        invoicesItemsTableTitleLBL.setText("Invoice Items");
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
                                                                .addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(69, 69, 69)
                                                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(rightPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(invoicesItemsTableTitleLBL, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
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
                                .addComponent(invoicesItemsTableTitleLBL)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(invoiceItemTBLScrollPane, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveBtn)
                                        .addComponent(cancelBtn))
                                .addContainerGap())
        );

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(new GridLayout(1,2));
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
/*
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
*/

        fileMenu.setText("File");

        loadFileMenuBtn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        loadFileMenuBtn.setText("Load File");
        loadFileMenuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadFileMenuBtnActionPerformed(evt);
            }
        });
        fileMenu.add(loadFileMenuBtn);

        saveFileMenuBtn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveFileMenuBtn.setText("Save FIle");
        saveFileMenuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveFileMenuBtnActionPerformed(evt);
            }
        });
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

    private void createNewInvoiceBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void deleteInvoiceBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cancelBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void loadFileMenuBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveFileMenuBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void invoiceDateTXTActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void customerNameTXTActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }


    public static void main(String args[]) {
//        invokeLater(new Runnable() {
//            public void run() {
//                View window = new View();
//                window.setTitle("Sales Invoice Generator");
//                window.setVisible(true);
//            }
//        });

        View window = new View();
        window.setTitle("Sales Invoice Generator");
        window.pack();
        window.setVisible(true);
    }
}
