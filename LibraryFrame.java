import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;

public class LibraryFrame extends JFrame implements ActionListener{

    JButton addBook;
    JButton borrowBook;
    JButton returnBook;
    JButton removeBook;
    JMenuItem listBooks;
    JMenuItem info;

    //防止重复打开页面，各个页面只创建一个对象重复使用
    Library lib;
    DisplayFrame df;
    AddBookFrame abf;
    BorrowBookFrame bbf;
    ReturnBookFrame retBf;
    RemoveBookFrame remBf;
    
    public LibraryFrame() throws IOException{

        this.setAlwaysOnTop(true);
        this.setTitle("Library System User Interface");
        this.setSize(484, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        lib = new Library();

        df = new DisplayFrame();
        df.setVisible(false);
        abf = new AddBookFrame(df, lib);
        abf.setVisible(false);
        bbf = new BorrowBookFrame(df, lib);
        bbf.setVisible(false);
        retBf = new ReturnBookFrame(df, lib);
        retBf.setVisible(false);
        remBf = new RemoveBookFrame(df, lib);
        remBf.setVisible(false);
        

        initFrame();
        initJMenuBar();
        this.setVisible(true);
    }

    public void initFrame(){
        JLabel title = new JLabel();
        title.setText("Library");
        title.setFont(new Font("Times New Roman", 0, 36));
        title.setBounds(178, 30, 200, 40);
        this.getContentPane().add(title);

        addBook = new JButton();
        addBook.setBounds(35, 120, 400, 40);
        addBook.setText("Add a book");
        addBook.setFont(new Font("Arial", 0, 20));
        addBook.addActionListener(this);
        this.getContentPane().add(addBook);

        borrowBook = new JButton();
        borrowBook.setBounds(35, 220, 400, 40);
        borrowBook.setText("Borrow a book");
        borrowBook.setFont(new Font("Arial", 0, 20));
        borrowBook.addActionListener(this);
        this.getContentPane().add(borrowBook);

        returnBook = new JButton();
        returnBook.setBounds(35, 320, 400, 40);
        returnBook.setText("Return a book");
        returnBook.setFont(new Font("Arial", 0, 20));
        returnBook.addActionListener(this);
        this.getContentPane().add(returnBook);

        removeBook = new JButton();
        removeBook.setBounds(35, 420, 400, 40);
        removeBook.setText("Remove a book");
        removeBook.setFont(new Font("Arial", 0, 20));
        removeBook.addActionListener(this);
        this.getContentPane().add(removeBook);
    }

    public void initJMenuBar(){
        JMenuBar menubar = new JMenuBar();
        menubar.setSize(30, 500);
        
        JMenu menu = new JMenu("Options");
        menubar.add(menu);

        listBooks = new JMenuItem("Books");
        listBooks.addActionListener(this);
        menu.add(listBooks);

        info = new JMenuItem("Info");
        info.addActionListener(this);
        menu.add(info);

        this.setJMenuBar(menubar);
        this.getContentPane().repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == addBook){
            System.out.println("Add a book");
            abf.setVisible(true);
        }else if (obj == borrowBook){
            System.out.println("Borrow a book");
            bbf.setVisible(true);
        }else if (obj == returnBook){
            System.out.println("Return a book");
            retBf.setVisible(true);
        }else if (obj == removeBook){
            System.out.println("Remove a book");
            remBf.setVisible(true);
        }else if (obj == listBooks){
            df.dtf.setRowCount(0);
            try {
                df.loadBooks();
                df.dtf.fireTableDataChanged();
            } catch (IOException e1) {
                System.out.println("Captured IOException");
            }
            df.setVisible(!df.isVisible());
        }else if (obj == info){
            System.out.println("Created by sishuo 2024.5.23 All Rights Reserved");
            ThrowModal("Created by sishuo 2024.5.23 All Rights Reserved");
        }
    }
    
    public static void ThrowModal(String text){
        JDialog jd = new JDialog();
        jd.setLayout(null);
        jd.setSize(375 + 5 * text.length(), 290);
        jd.setModal(true);
        jd.setAlwaysOnTop(true);
        jd.setLocationRelativeTo(null);
        jd.setTitle("Message");

        JLabel message = new JLabel(text);
        message.setBounds(50, 100, 384 + 5 * text.length(), 40);
        message.setFont(new Font("Arial", 1, 20));
        jd.getContentPane().add(message);

        jd.setVisible(true);
    }
}
