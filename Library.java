import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() throws IOException{
        this.books = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("files\\books.txt"));
        String str;
        while((str = br.readLine()) != null){
            // if borrowed book, the String will be "name=borrower"
            String[] tempArr = str.split("=");
            books.add(tempArr.length == 1 ? new Book(str) : new Book(tempArr[0], tempArr[1]));
        }
        br.close();
    }

    public int borrowBook(String bookName, String borrower) throws IOException{
        if (borrower == null) {
            System.out.println("Invalid borrower");
            return -3;
        }
        for (Book book : books) {
            if (book.getName().equals(bookName)){
                if (!book.isBorrowed()){
                    book.setBorrower(borrower);
                    System.out.println(borrower + " successfully borrowed book \"" + bookName + "\"");
                    int line = books.indexOf(book);
                    BufferedReader br = new BufferedReader(new FileReader("files\\books.txt"));
                    StringBuilder sb = new StringBuilder();
                    String str;
                    int num = 0;
                    while ((str = br.readLine()) != null) {
                        if (num == line) sb.append(str + "=" + borrower + "\r\n");
                        else sb.append(str + "\r\n");
                        num++;
                    }
                    BufferedWriter bw = new BufferedWriter(new FileWriter("files\\books.txt"));
                    bw.write(sb.toString());
                    bw.close();
                    br.close();
                    return 1;
                }else {
                    System.out.println("Sorry, the book has already been borrowed!");
                    return -1;
                }
            }
        }
        System.out.println("Unable to find book!");
        return -2;
    }

    public boolean borrowBook(Book b, String borrower) throws IOException{
        for (Book book : books) {
            if (book.getName().equals(b.getName())){
                if (!book.isBorrowed()){
                    book.setBorrower(borrower);
                    System.out.println(borrower + " successfully borrowed book \"" + b.getName() + "\"");
                    int line = books.indexOf(book);
                    BufferedReader br = new BufferedReader(new FileReader("files\\books.txt"));
                    StringBuilder sb = new StringBuilder();
                    String str;
                    int num = 0;
                    while ((str = br.readLine()) != null) {
                        if (num == line) sb.append(str + "=" + borrower + "\r\n");
                        else sb.append(str + "\r\n");
                        num++;
                    }
                    BufferedWriter bw = new BufferedWriter(new FileWriter("files\\books.txt"));
                    bw.write(sb.toString());
                    bw.close();
                    br.close();
                    return true;
                }else {
                    System.out.println("Sorry, the book has already been borrowed!");
                    return false;
                }
            }
        }
        System.out.println("Unable to find book!");
        return false;
    }

    public boolean addBook(Book newBook) throws IOException{
        //查重
        for (Book book : books) {
            if (book.getName().equals(newBook.getName())){
                System.out.println("This book is already added.");
                return false;
            }
        }
        books.add(newBook);
        BufferedWriter bw = new BufferedWriter(new FileWriter("files\\books.txt", true));
        bw.write(newBook.getName());
        bw.newLine();
        bw.close();
        //按理来说应该增加Book的其他属性
        System.out.println("Book \"" + newBook.getName() + "\" successfully added!");
        return true;
    }

    public boolean addBook(String bookName) throws IOException{
        for (Book book : books) {
            if (book.getName().equals(bookName)){
                System.out.println("This book is already added.");
                return false;
            }
        }
        books.add(new Book(bookName));
        BufferedWriter bw = new BufferedWriter(new FileWriter("files\\books.txt", true));
        bw.write(bookName);
        bw.newLine();
        bw.close();
        System.out.println("Book \"" + bookName +"\" successfully added!");
        //按理来说应该增加Book的其他属性
        return true;
    }

    public boolean returnBook(Book b) throws IOException{
        for (Book book : books) {
            if (book.equals(b)){
                //极端情况，用同一个对象
                book.removeBorrower();
                System.out.println("Book checked in! Thanks for returning.");

                //写入数据：先读取所有内容并在特定行上面增加/减少borrower，再用流写入
                int line = books.indexOf(book);
                BufferedReader br = new BufferedReader(new FileReader("files\\books.txt"));
                StringBuilder sb = new StringBuilder();
                String str;
                int num = 0;
                while ((str = br.readLine()) != null) {
                    if (num == line) sb.append(book.getName() + "\r\n");
                    else sb.append(str + "\r\n");
                    num++;
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter("files\\books.txt"));
                bw.write(sb.toString());
                bw.close();
                br.close();
                return true;
            } else if (book.getName().equals(b.getName())){
                if (book.isBorrowed()){
                    if (book.getBorrower().equals(b.getBorrower())){
                        book.removeBorrower();
                        System.out.println("Book checked in! Thanks for returning.");
                        int line = books.indexOf(book);
                        BufferedReader br = new BufferedReader(new FileReader("files\\books.txt"));
                        StringBuilder sb = new StringBuilder();
                        String str;
                        int num = 0;
                        while ((str = br.readLine()) != null) {
                            if (num == line) sb.append(book.getName() + "\r\n");
                            else sb.append(str + "\r\n");
                            num++;
                        }
                        BufferedWriter bw = new BufferedWriter(new FileWriter("files\\books.txt"));
                        bw.write(sb.toString());
                        bw.close();
                        br.close();
                        return true;
                    }
                    System.out.println("Borrower name not matched");
                    return false;
                }else {
                    System.out.println("Book not borrowed yet!");
                    return false;
                }
            }
        }
        System.out.println("Unable to find book!");
        return false;
    }

    public int returnBook(String bookName, String borrower) throws IOException {
        if (borrower == null){
            System.out.println("Invalid borrower!");
            return -3;
        }
        for (Book book : books) {
            if (book.getName().equals(bookName)){
                if (book.isBorrowed()){
                    if (book.getBorrower().equals(borrower)){
                        book.removeBorrower();
                        System.out.println("Book checked in! Thanks for returning.");
                        int line = books.indexOf(book);
                        BufferedReader br = new BufferedReader(new FileReader("files\\books.txt"));
                        StringBuilder sb = new StringBuilder();
                        String str;
                        int num = 0;
                        while ((str = br.readLine()) != null) {
                            if (num == line) sb.append(book.getName() + "\r\n");
                            else sb.append(str + "\r\n");
                            num++;
                        }
                        BufferedWriter bw = new BufferedWriter(new FileWriter("files\\books.txt"));
                        bw.write(sb.toString());
                        bw.close();
                        br.close();
                        return 1;
                    }
                    System.out.println("Borrower name not matched");
                    return 0;
                }else {
                    System.out.println("Book not borrowed yet!");
                    return -1;
                }
            }
        }
        System.out.println("Unable to find book!");
        return -2;
    }
    
    public boolean deleteBook(Book b) throws IOException{
        if (b.isBorrowed()){
            System.out.println("This book has already been borrowed! Unable to delete the book.");
            return false;
        }
        for (Book book : books) {
            if (book.getName().equals(b.getName())){
                if (book.isBorrowed()){
                    System.out.println("This book has been borrowed! Unable to delete the book.");
                    return false;
                }
                int line = books.indexOf(book);
                BufferedReader br = new BufferedReader(new FileReader("files\\books.txt"));
                StringBuilder sb = new StringBuilder();
                String str;
                int num = 0;
                while ((str = br.readLine()) != null) {
                    if (num != line) sb.append(str + "\r\n");
                    num++;
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter("files\\books.txt"));
                bw.write(sb.toString());
                bw.close();
                br.close();
                System.out.println("Book \"" + b.getName() + "\" successfully removed!");
                books.remove(line);
                return true;
            }
        }
        System.out.println("Unable to find book!");
        return false;
    }

    public int deleteBook(String bookName) throws IOException{
        for (Book book : books) {
            if (book.getName().equals(bookName)){
                if (book.isBorrowed()){
                    System.out.println("This book has been borrowed! Unable to delete the book.");
                    return -1;
                }
                int line = books.indexOf(book);
                BufferedReader br = new BufferedReader(new FileReader("files\\books.txt"));
                StringBuilder sb = new StringBuilder();
                String str;
                int num = 0;
                while ((str = br.readLine()) != null) {
                    if (num != line) sb.append(str + "\r\n");
                    num++;
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter("files\\books.txt"));
                bw.write(sb.toString());
                bw.close();
                br.close();
                System.out.println("Book \"" + bookName + "\" successfully removed!");
                books.remove(line);
                return 1;
            }
        }
        System.out.println("Unable to find book!");
        return -2;
    }

    public void listBooks(){
        System.out.println("{");
        for (Book b : books) {
            System.out.println(b);
        }
        System.out.println("}");
    }

    
}
