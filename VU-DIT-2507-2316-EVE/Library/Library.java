public class Library {
     private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    /**
     * Lends a book to a member, enforcing the "at most one active loan per book" rule.
     *
     * @param book      the book to lend
     * @param member    the member borrowing the book
     * @param borrowDate the date the book is borrowed
     * @throws IllegalStateException if the book is already on loan
     */
    public void lendBook(Book book, Member member, LocalDate borrowDate) {
        if (book == null || member == null || borrowDate == null) {
            throw new IllegalArgumentException("Book, member and borrow date must not be null");
        }

        if (!books.contains(book)) {
            throw new IllegalArgumentException("Book is not part of the library collection");
        }

        if (!members.contains(member)) {
            throw new IllegalArgumentException("Member is not registered with the library");
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException(
                "Book '" + book.getTitle() + "' (ISBN: " + book.getIsbn() + ") is already on loan and cannot be borrowed until returned."
            );
        }

        // Calculate due date: 14 days from borrow date (standard loan period)
        LocalDate dueDate = borrowDate.plusDays(14);

        // Create the loan and link it to both book and member
        Loan loan = new Loan(book, member, borrowDate, dueDate);

        // Update book status
        book.setAvailable(false);

        // Add loan to member's list of active loans
        member.addLoan(loan);

        System.out.println("Book '" + book.getTitle() + "' successfully lent to member " + member.getName()
            + ". Due date: " + dueDate);
    }

    /**
     * Returns a book that was on loan, making it available again.
     *
     * @param book the book being returned
     * @throws IllegalStateException if the book is not currently on loan
     */
    public void returnBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book must not be null");
        }

        if (!books.contains(book)) {
            throw new IllegalArgumentException("Book is not part of the library collection");
        }

        if (book.isAvailable()) {
            throw new IllegalStateException(
                "Book '" + book.getTitle() + "' (ISBN: " + book.getIsbn() + ") is not currently on loan."
            );
        }

        // Find the active loan for this book across all members
        Loan activeLoan = null;
        Member loanHolder = null;

        for (Member member : members) {
            for (Loan loan : member.getLoans()) {
                if (loan.getBook().equals(book)) {
                    activeLoan = loan;
                    loanHolder = member;
                    break;
                }
            }
            if (activeLoan != null) break;
        }

        if (activeLoan == null) {
            throw new IllegalStateException(
                "No active loan record found for book '" + book.getTitle() + "'."
            );
        }

        // Remove the loan from the member's active loans
        loanHolder.removeLoan(activeLoan);

        // Mark the book as available
        book.setAvailable(true);

        System.out.println("Book '" + book.getTitle() + "' returned by member " + loanHolder.getName()
            + " and is now available for lending.");
    }

    /**
     * Searches for books whose title contains the given keyword (case-insensitive).
     */
    public List<Book> searchByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return new ArrayList<>();
        }

        return books.stream()
            .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
    }
}

}
