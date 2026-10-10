public class Transaction {
    private String transactionId;
    private Book book;
    private Member member;
    private String issueDate;
    private String returnDate;

    public Transaction(String transactionId,Book book,Member member,String issueDate, String returnDate) {
        this.transactionId = transactionId;
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
        public String getTransactionId(){return transactionId;}
        public Book getBook(){
            return book;
        }
        public Member getMember (){return member;}
        public String getIssueDate(){return issueDate;}
        public String getReturnDate(){
            return returnDate;
        }
    }
