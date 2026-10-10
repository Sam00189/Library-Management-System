public class Member {
    private int id;
    private String name;
    private String email;
    private int borrowedBookCount;

    public Member (String name,String email,int borrowedBookCount){
        this.name=name;
        this.email=email;
        this.borrowedBookCount=borrowedBookCount;

    }

    public void displayInfo(){
        System.out.println(id);
        System.out.println(name);
        System.out.println(email);
        System.out.println(borrowedBookCount);
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public int getBorrowedBookCount(){
        return borrowedBookCount;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        if(id>0){
            this.id=id;
        }
    }
}
