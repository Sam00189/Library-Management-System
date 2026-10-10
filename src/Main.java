//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch(choice) {
                case 1:{
                    System.out.print("Enter title: ");
                    String title = scanner.next();
                    System.out.print("Enter author: ");
                    String author = scanner.next();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.next();
                    Book book = new Book(title, author, isbn, true);
                    BookDAO bookDAO = new BookDAO();
                    try {
                        bookDAO.addBook(book);
                        System.out.println("Book added successfully");
                    } catch (SQLException e) {
                        System.out.println("Failed: " + e.getMessage());
                    }
                    break;
                    }
                case 2:{
                    System.out.print("Enter name :");
                    String name=scanner.next();
                    System.out.print("Enter email :");
                    String email =scanner.next();
                    System.out.print("Enter borrowed count :");
                    int borrowedCount =scanner.nextInt();

                    Member member =new Member(name,email,borrowedCount);
                    MemberDAO memberDAO =new MemberDAO();

                    try{
                        memberDAO.addMember(member);
                        System.out.print("Member added successfully");
                    }catch(SQLException e){
                        System.out.print("failed:" +e.getMessage());
                    }
                    break;
                    }
                case 3:{
                    try {
                        scanner.nextLine();
                        System.out.print("Enter book name");
                        String bookName = scanner.nextLine();
                        System.out.print("Enter the your name");
                        String username = scanner.nextLine();

                        BookDAO bookDAO1 = new BookDAO();
                        MemberDAO memberDAO1 = new MemberDAO();
                        Book book = bookDAO1.getBookByTitle(bookName);
                        Member member = memberDAO1.getMemberByName(username);

                        if (book != null && member != null) {
                            Library library1 = new Library();
                            library1.issueBook(book, member);
                            System.out.print("Book issued successfully");
                        } else {
                            System.out.print("book or member not found");
                        }
                    }catch(Exception e){
                            System.out.println("Failed: " + e.getMessage());
                        }
                    break;
                }
                case 4:{
                    try {
                        scanner.nextLine();
                        System.out.print("Enter the book name");
                        String returnBook =scanner.nextLine();
                        System.out.print("Enter your name");
                        String returnUser =scanner.nextLine();

                        BookDAO bookDAO =new BookDAO();
                        MemberDAO memberDAO = new MemberDAO();
                        Book book= bookDAO.getBookByTitle(returnBook);
                        Member member =memberDAO.getMemberByName(returnUser);

                        if(book!=null && member!=null){
                            Library library = new Library();
                            library.returnBook(book, member);
                            System.out.print("Book returned successfully");

                            TransactionDAO transactionDAO=new TransactionDAO();
                            String issueDate=transactionDAO.getIssueDate(book.getBookId());
                            if(issueDate!=null){
                                Library lib = new Library();
                                double fine = lib.calculateFine(issueDate, LocalDate.now().toString());
                                if (fine > 0.0) {
                                    System.out.println("Fine: ₹" + fine);
                                } else {
                                    System.out.println("No fine — returned on time.");
                                }
                            }
                        }
                        else{
                            System.out.print("book and member not found");
                        }
                    }catch(Exception e){
                        System.out.print("failed"+e.getMessage());
                    }
                    break;
                }
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);
        }
}