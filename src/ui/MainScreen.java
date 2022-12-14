package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainScreen extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem loadFile;
    private JMenuItem saveFile;

    public MainScreen() {
        super("Sales Invoice Generator");
        menuBar = new JMenuBar();
        menu = new JMenu("File");

        loadFile = new JMenuItem("Load File", 'o');
        loadFile.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
        loadFile.addActionListener(this);
        loadFile.setActionCommand("O");

        saveFile = new JMenuItem("Save File", 's');
        saveFile.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        saveFile.addActionListener(this);
        saveFile.setActionCommand("S");

        menuBar.add(menu);
        menu.add(loadFile);
        menu.add(saveFile);
        setJMenuBar(menuBar);




        setSize(400, 300);
        setLocation(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new MainScreen().setVisible(true);
    }
}
