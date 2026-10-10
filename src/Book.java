public class Book {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book (String title,String author,String isbn,boolean isAvailable){
        this.title=title;
        this.author=author;
        this.isbn=isbn;
        this.isAvailable=isAvailable;
    }

    public void show(){
        System.out.println(title);
        System.out.println(author);
        System.out.println(isbn);
        System.out.println(isAvailable);
    }

    public int getBookId(){
        return bookId;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setBookId(int bookId){
        if(bookId>0){
            this.bookId=bookId;
        }
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }


}
