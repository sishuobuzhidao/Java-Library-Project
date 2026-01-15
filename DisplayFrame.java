import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DisplayFrame extends JFrame{
    JTable books = null;//表格
    DefaultTableModel dtf = null;
    
    public DisplayFrame() throws IOException{
        this.setAlwaysOnTop(true);
        this.setTitle("Library Book List");
        this.setSize(484, 600);
        this.setLocationRelativeTo(books);
        this.setLayout(null);
        initFrame();
        loadBooks();
        this.setVisible(true);
    }

    public void initFrame() {
        //this.removeAll();
        books = new JTable();
        dtf = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 所有单元格都不可编辑
            }
        };
        Object[] columns = new Object[]{"Book name", "Borrower"};
        //表头里面的数据
        Object[][] mains = {};
        dtf = new DefaultTableModel(mains, columns);
        books.setBounds(50, 50, 400, 400);
        books.setSelectionMode(0);
        books.setModel(dtf);
        //books.setBorder(new LineBorder(Color.BLACK, 1));
        books.setCellSelectionEnabled(false);

        //滚动界面的对象，可以使得数据过多时可以上下移动
        JScrollPane jsp = new JScrollPane(books);
        jsp.setBounds(35, 120, 400, 300);
        this.getContentPane().add(jsp);

        this.getContentPane().repaint();
    }

    public void loadBooks() throws IOException{
        //this.removeAll();
        BufferedReader br = new BufferedReader(new FileReader("files\\books.txt"));
        String str;
        while((str = br.readLine()) != null){
            String[] arr = str.split("=");
            if (arr.length == 1) {
                dtf.addRow(new Object[]{str, "Not borrowed yet"});
            }else {
                dtf.addRow(new Object[]{arr[0], "Borrowed by " + arr[1]});
            }
        }
        br.close();
    }
}
