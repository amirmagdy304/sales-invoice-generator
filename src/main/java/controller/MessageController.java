package controller;

import javax.swing.*;

public class MessageController {

    public void displayDescriptiveMessage(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "INFORMATION_MESSAGE",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public int displayConfirmationMessage(String message) {
        int userResponse = JOptionPane.showConfirmDialog(null,
                message,
                "Confirmation Message",
                JOptionPane.YES_NO_OPTION);
        return userResponse;
    }

    public void displayErrorMessage(String message) {
        if (message.contains("Unparseable date")) {
            JOptionPane.showMessageDialog(null,
                    "Invalid date format. The valid format is dd/MM/yyyy \n " + message,
                    "ERROR_MESSAGE",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    message,
                    "ERROR_MESSAGE",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

//    public void displayInvalidDateFormatMessage(String message) {
//        JOptionPane.showMessageDialog(null,
//                "Invalid date format. The valid format is dd/MM/yyyy \n "+message,
//                "ERROR_MESSAGE",
//                JOptionPane.ERROR_MESSAGE);
//    }
}
