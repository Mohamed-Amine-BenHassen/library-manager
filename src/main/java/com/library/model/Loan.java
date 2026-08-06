package com.library.model;

public class Loan {
    private final int id;
    private final String bookTitle;
    private final String memberName;
    private final String borrowedOn;
    private final String dueDate;

    public Loan(int id, String bookTitle, String memberName, String borrowedOn, String dueDate) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.memberName = memberName;
        this.borrowedOn = borrowedOn;
        this.dueDate = dueDate;
    }

    public int getId()            { return id; }
    public String getBookTitle()  { return bookTitle; }
    public String getMemberName() { return memberName; }
    public String getBorrowedOn() { return borrowedOn; }
    public String getDueDate()    { return dueDate; }

    @Override
    public String toString() {
        return String.format("[%d] \"%s\" — %s | borrowed %s, due %s",
                id, bookTitle, memberName, borrowedOn, dueDate);
    }
}