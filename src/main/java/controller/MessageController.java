package controller;

import javax.swing.*;

public class MessageController {

//    public void displayWrongFileFormatMessage() {
//        JOptionPane.showMessageDialog(null,
//                "Wrong file format, Please select CSV comma delimited file.",
//                "ERROR_MESSAGE",
//                JOptionPane.ERROR_MESSAGE);
//    }

    public void displayErrorMessage(String message) {
        if(message.contains("Unparseable date")){
            JOptionPane.showMessageDialog(null,
                    "Invalid date format. The valid format is dd/MM/yyyy \n "+message,
                    "ERROR_MESSAGE",
                    JOptionPane.ERROR_MESSAGE);
        }else{
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
