public class Loan {
    private Member member;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    private static final int LOAN_PERIOD_DAYS = 14;

    // --- Constructors ---

    /**
     * Constructor 1: Creates a loan with the current date as borrow date
     * and a due date 14 days later.
     */
    public Loan(Member member, Book book) {
        this(member, book, LocalDate.now(), LocalDate.now().plusDays(LOAN_PERIOD_DAYS));
    }

    /**
     * Constructor 2 (overloaded): Creates a loan with explicit dates.
     */
    public Loan(Member member, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    // --- Getters and Setters ---

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // --- Object overrides ---

    @Override
    public String toString() {
        return "Loan{member=" + member.getName() + " (" + member.getMemberId() +
                "), book='" + book.getTitle() + "' (" + book.getIsbn() +
                "), borrow=" + borrowDate + ", due=" + dueDate + "}";
    }
}
