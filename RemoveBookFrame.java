import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class RemoveBookFrame extends JFrame implements ActionListener {
    JTextField bookNameField;
    JButton yesButton;
    JButton noButton;
    DisplayFrame df;
    Library lib;

    public RemoveBookFrame(DisplayFrame df, Library lib){
        this.df = df;
        this.lib = lib;
        this.setAlwaysOnTop(true);
        this.setTitle("Remove a Book");
        this.setSize(484, 262);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        initFrame();
        this.setVisible(true);
    }

    public void initFrame(){
        JLabel bookNameLabel = new JLabel("Enter the book name:");
        bookNameLabel.setBounds(35, 30, 400, 30);
        bookNameLabel.setFont(new Font("Arial", 0, 15));
        this.getContentPane().add(bookNameLabel);

        bookNameField = new JTextField();
        bookNameField.setBounds(35, 60, 400, 30);
        this.getContentPane().add(bookNameField);

        yesButton = new JButton("Confirm");
        yesButton.setBounds(80, 130, 100, 40);
        yesButton.addActionListener(this);
        this.getContentPane().add(yesButton);

        noButton = new JButton("Cancel");
        noButton.setBounds(280, 130, 100, 40);
        noButton.addActionListener(this);
        this.getContentPane().add(noButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if (obj == yesButton) {
            boolean success = false;
            String bookName = bookNameField.getText();
            try {
                switch (lib.deleteBook(bookName)){
                    case 1 -> {
                        LibraryFrame.ThrowModal("Successfully removed the book \"" + bookName + "\"!");
                        success = true;
                    }
                    case -1 -> LibraryFrame.ThrowModal("This book has been borrowed! Unable to delete the book.");
                    case -2 -> LibraryFrame.ThrowModal("Unable to find book!");
                    default -> throw new IOException();
                }
                //刷新书的列表
                df.dtf.setRowCount(0);
                df.loadBooks();
                df.dtf.fireTableDataChanged();
            } catch (IOException e1) {
                System.out.println("Captured IOException");
            }
            if (success){
                bookNameField.setText("");
                this.setVisible(false);
            }
        }else if (obj == noButton){
            //初始化输入框中的内容
            bookNameField.setText("");
            this.setVisible(false);
        }
    }

}
