# Java-Library-Project
Created using Java for AP CSA Course project at school, in 2024

///////////////////////
Description.txt (May 2024)
3.Library Management System
Build a system to manage a library’s book inventory. 
Implement functionality to add new books, check out books, and return books.

books.txt should have one empty line for further addition of books.
Any borrowed book will be stored in the form "name=borrower"
Just do not add any "=" in the book name or borrower name

Author note: I wrote two versions of the system.
The ConsoleApp class is a console version of the library, where Scanners and while loops implemented.
The LibraryFrame class is used to describe the GUI version of the library, along with four other classes ended with "Frame"
A test class can create an object of LibraryFrame to start the program.
///////////////////////

2026.1.14 Note:
This project was created using Java swing with Object-oriented Design, using IO to keep track of status of currently stored books.
The directories in the project was:

AP CSA Project/
  codes/
    * about 10ish java files
  files/
    books.txt
    books1.txt
    description.txt

For books.txt and books1.txt, they are the list of books that the code will read upon starting and display in the frames. For a book that was borrowed, write BookName=Borrower for the line; for a non-borrowed book, write just the book name for the line.
