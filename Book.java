public class Book{
    private String name;
    private boolean isBorrowed;
    private String borrower;

    public Book(String name) {
        this.name = name;
        this.isBorrowed = false;
        this.borrower = null;
    }

    public Book(String name, String borrower) {
        this.name = name;
        this.isBorrowed = true;
        this.borrower = borrower;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBorrowed() {
        return this.isBorrowed;
    }

    public String getBorrower() {
        return this.borrower;
    }

    public void setBorrower(String borrower) {
        //如果书已经有人借了，那一定被borrow了，所以绑定修改
        this.borrower = borrower;
        this.isBorrowed = true;
    }
    
    public void removeBorrower() {
        this.borrower = null;
        this.isBorrowed = false;
    }

    @Override
    public String toString() {
        /*  return "{" +
             " name='" + getName() + "'" +
             ", isBorrowed='" + isBorrowed() + "'" +
             ", borrower='" + getBorrower() + "'" +
             "}";                                     */
        // \t是制表符
        return "Book \"" + name + "\"\t" +
                (isBorrowed ? "------ borrowed by " + borrower : "------ not borrowed yet") + "\t";
    }
    
    
}