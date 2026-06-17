public class publish {

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;}

/**
 * Represents the library that holds all books and members,
 * and provides operations for lending, returning, and searching.
 */
public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Loan> activeLoans;

    /**
     * Creates an empty library.
     */
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.activeLoans = new ArrayList<>();
    }

    // --- Book management ---

    /**
     * Adds a new book to the library's collection.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Registers a new member with the library.
     */
    public void registerMember(Member member) {
        members.add(member);
    }

    // --- Lending ---

    /**
     * Lends a book to a member.
     * Enforces: a book can have at most one active loan at a time.
     *
     * @param memberId the ID of the member borrowing the book
     * @param isbn     the ISBN of the book to borrow
     * @return the created Loan if successful, or null if rejected
     */
    public Loan lendBook(String memberId, String isbn) {
        // Find the member
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("ERROR: Member with ID '" + memberId + "' not found.");
            return null;
        }

        // Find the book
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("ERROR: Book with ISBN '" + isbn + "' not found.");
            return null;
        }

        // Enforce business rule: book must be available
        if (!book.isAvailable()) {
            System.out.println("REJECTED: Book '" + book.getTitle() +
                    "' is already on loan and cannot be borrowed.");
            return null;
        }

        // Create the loan
        Loan loan = new Loan(member, book);

        // Update state
        book.setAvailable(false);
        member.addLoan(loan);
        activeLoans.add(loan);

        System.out.println("SUCCESS: '" + book.getTitle() + "' lent to " +
                member.getName() + " (due: " + loan.getDueDate() + ").");
        return loan;
    }

    // --- Returning ---

    /**
     * Returns a book that was on loan.
     *
     * @param isbn the ISBN of the book being returned
     * @return true if the return was successful, false otherwise
     */
    public boolean returnBook(String isbn) {
        // Find the book
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("ERROR: Book with ISBN '" + isbn + "' not found.");
            return false;
        }

        // Check that it is actually on loan
        if (book.isAvailable()) {
            System.out.println("ERROR: Book '" + book.getTitle() +
                    "' is not currently on loan.");
            return false;
        }

        // Find the active loan for this book
        Loan loan = findActiveLoanByIsbn(isbn);
        if (loan == null) {
            System.out.println("ERROR: No active loan found for ISBN '" + isbn + "'.");
            return false;
        }

        // Update state
        book.setAvailable(true);
        loan.getMember().removeLoan(loan);
        activeLoans.remove(loan);

        System.out.println("SUCCESS: '" + book.getTitle() + "' returned by " +
                loan.getMember().getName() + ".");
        return true;
    }

    // --- Searching ---

    /**
     * Searches for books by title (case-insensitive partial match).
     *
     * @param title the title or title fragment to search for
     * @return a list of books whose titles contain the search string
     */
    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    // --- Helper methods ---

    private Member findMemberById(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                return m;
            }
        }
        return null;
    }

    private Book findBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }

    private Loan findActiveLoanByIsbn(String isbn) {
        for (Loan l : activeLoans) {
            if (l.getBook().getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    // --- Getters for display ---

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    public List<Loan> getActiveLoans() {
        return new ArrayList<>(activeLoans);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== LIBRARY STATE =====\n");

        sb.append("\n--- Books (").append(books.size()).append(") ---\n");
        for (Book b : books) {
            sb.append("  ").append(b).append("\n");
        }

        sb.append("\n--- Members (").append(members.size()).append(") ---\n");
        for (Member m : members) {
            sb.append("  ").append(m).append("\n");
        }

        sb.append("\n--- Active Loans (").append(activeLoans.size()).append(") ---\n");
        if (activeLoans.isEmpty()) {
            sb.append("  (none)\n");
        } else {
            for (Loan l : activeLoans) {
                sb.append("  ").append(l).append("\n");
            }
        }

        sb.append("=========================");
        return sb.toString();
    }
}

}
