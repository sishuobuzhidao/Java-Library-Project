import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class BorrowBookFrame extends JFrame implements ActionListener{
    JTextField bookNameField;
    JTextField borrowerField;
    JButton yesButton;
    JButton noButton;
    DisplayFrame df;
    Library lib;

    public BorrowBookFrame(DisplayFrame df, Library lib){
        this.df = df;
        this.lib = lib;
        this.setAlwaysOnTop(true);
        this.setTitle("Borrow a Book");
        this.setSize(484, 340);
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

        JLabel borrowerLabel = new JLabel("Enter the name of the borrower:");
        borrowerLabel.setBounds(35, 110, 400, 30);
        borrowerLabel.setFont(new Font("Arial", 0, 15));
        this.getContentPane().add(borrowerLabel);

        borrowerField = new JTextField();
        borrowerField.setBounds(35, 140, 400, 30);
        this.getContentPane().add(borrowerField);

        yesButton = new JButton("Confirm");
        yesButton.setBounds(80, 210, 100, 40);
        yesButton.addActionListener(this);
        this.getContentPane().add(yesButton);

        noButton = new JButton("Cancel");
        noButton.setBounds(280, 210, 100, 40);
        noButton.addActionListener(this);
        this.getContentPane().add(noButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == yesButton) {
            String bookName = bookNameField.getText();
            String borrower = borrowerField.getText();
            boolean success = false;
            try {
                switch (lib.borrowBook(bookName, borrower)){
                    case 1 -> {
                        LibraryFrame.ThrowModal("Successfully borrowed book \"" + bookName + "\"!");
                        success = true;
                    }
                    case -1 -> LibraryFrame.ThrowModal("This book has already been borrowed!");
                    case -2 -> LibraryFrame.ThrowModal("Unable to find book!");
                    case -3 -> LibraryFrame.ThrowModal("Invalid borrower name or book name!");
                    default -> throw new IOException();
                }
                //刷新书的列表
                df.dtf.setRowCount(0);
                df.loadBooks();
                df.dtf.fireTableDataChanged();
            } catch (IOException e1) {
                System.out.println("Captured exception");
            }
            if (success) {
                bookNameField.setText("");
                borrowerField.setText("");
                this.setVisible(false);
            }
        }else if (obj == noButton){
            //初始化输入框中的内容
            bookNameField.setText("");
            borrowerField.setText("");
            this.setVisible(false);
        }
    }
}
