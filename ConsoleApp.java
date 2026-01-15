import java.io.IOException;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) throws InterruptedException, IOException {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);
        mainLoop : while(true){
            Thread.sleep(1000);
            show();
            String action = sc.nextLine();
            switch (action) {
                case "1" -> addBook(lib, sc);
                case "2" -> borrowBook(lib, sc);
                case "3" -> returnBook(lib, sc);
                case "4" -> {
                    lib.listBooks();
                    Thread.sleep(2000);
                }
                case "5" -> deleteBook(lib, sc);
                case "6" -> {
                    System.out.println("Thanks for coming! See you next time at the library.");
                    break mainLoop;
                }
                default -> System.out.println("Invalid action. Please try again.");
            }
        }
        sc.close();
    }

    public static void show(){
        System.out.println("--------------------------");
        System.out.println("Welcome to the library");
        System.out.println("Enter the action you would like to perform");
        System.out.println("1 = add new book");
        System.out.println("2 = borrow book");
        System.out.println("3 = return book");
        System.out.println("4 = list all books");
        System.out.println("5 = delete book");
        System.out.println("6 = exit");
        System.out.println("--------------------------");
    }

    public static void addBook(Library lib, Scanner sc) throws IOException{
        System.out.println("Please enter the name of the new book:");
        String bookName = sc.nextLine();
        lib.addBook(bookName);
    }

    public static void borrowBook(Library lib, Scanner sc) throws IOException{
        System.out.println("Please enter the name of the book you would like to borrow:");
        String bookName = sc.nextLine();
        System.out.println("Please enter the name of the borrower:");
        String borrower = sc.nextLine();
        lib.borrowBook(bookName, borrower);
    }

    public static void returnBook(Library lib, Scanner sc) throws IOException{
        System.out.println("Please enter the name of the book you would like to return:");
        String bookName = sc.nextLine();
        System.out.println("Please enter the name of the borrower:");
        String borrower = sc.nextLine();
        lib.returnBook(bookName, borrower);
    }

    public static void deleteBook(Library lib, Scanner sc) throws IOException{
        System.out.println("Please enter the name of the book you would like to delete:");
        String bookName = sc.nextLine();
        lib.deleteBook(bookName);
    }
}
