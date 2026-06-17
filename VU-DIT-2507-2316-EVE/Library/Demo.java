import java.lang.reflect.Member;
import java.time.LocalDate;

import loan.java.Loan;

public class Demo {
    public class LibraryDemo {
        public static void main(String[] args) {
            // Create the library
            Library library = new Library();

            // Create books
            Book book1 = new Book("978-0-14-028333-4", "1984", "George Orwell");
            Book book2 = new Book("978-0-06-112008-4", "To Kill a Mockingbird", "Harper Lee");
            Book book3 = new Book("978-0-7432-7356-5", "The Great Gatsby", "F. Scott Fitzgerald");

            // Add books to library
            library.addBook(book1);
            library.addBook(book2);
            library.addBook(book3);

            // Create members
            Member member1 = new Member("M001", "Alice Johnson");
            Member member2 = new Member("M002", "Bob Smith");

            // Register members
            library.registerMember(member1);
            library.registerMember(member2);

            // --- Print initial state ---
            System.out.println("========== LIBRARY STATE - BEFORE ANY TRANSACTIONS ==========");
            printLibraryState(book1, book2, book3, member1, member2);

            // --- Transaction 1: Lend "1984" to Alice ---
            System.out.println("\n========== TRANSACTION 1: Lend '1984' to Alice ==========");
            library.lendBook(book1, member1, LocalDate.of(2026, 6, 1));

            // --- Transaction 2: Lend "To Kill a Mockingbird" to Bob ---
            System.out.println("\n========== TRANSACTION 2: Lend 'To Kill a Mockingbird' to Bob ==========");
            library.lendBook(book2, member2, LocalDate.of(2026, 6, 3));

            // --- Transaction 3: Attempt to lend "1984" to Bob (should be rejected) ---
            System.out.println("\n========== TRANSACTION 3: Attempt to lend '1984' to Bob (should FAIL) ==========");
            try {
                library.lendBook(book1, member2, LocalDate.of(2026, 6, 5));
            } catch (IllegalStateException e) {
                System.out.println("REJECTED: " + e.getMessage());
            }

            // --- Print state after lending ---
            System.out.println("\n========== LIBRARY STATE - AFTER LENDING, BEFORE RETURNS ==========");
            printLibraryState(book1, book2, book3, member1, member2);

            // --- Transaction 4: Bob returns "To Kill a Mockingbird" ---
            System.out.println("\n========== TRANSACTION 4: Bob returns 'To Kill a Mockingbird' ==========");
            library.returnBook(book2);

            // --- Transaction 5: Now lend "1984" to Bob (should succeed because Alice
            // hasn't returned it yet? No!) ---
            // Actually "1984" is still with Alice, so this should fail. Let's lend "The
            // Great Gatsby" to Bob instead.
            System.out.println("\n========== TRANSACTION 5: Lend 'The Great Gatsby' to Bob ==========");
            library.lendBook(book3, member2, LocalDate.of(2026, 6, 10));

            // --- Transaction 6: Alice returns "1984" ---
            System.out.println("\n========== TRANSACTION 6: Alice returns '1984' ==========");
            library.returnBook(book1);

            // --- Transaction 7: Now lend "1984" to Bob (should succeed) ---
            System.out.println("\n========== TRANSACTION 7: Lend '1984' to Bob (should succeed now) ==========");
            library.lendBook(book1, member2, LocalDate.of(2026, 6, 12));

            // --- Transaction 8: Attempt to return "1984" again (should fail - already with
            // Bob) ---
            // Actually it's on loan to Bob now, so returning it is valid. Instead, try
            // returning "1984"
            // twice — first real return, then duplicate.
            System.out.println("\n========== TRANSACTION 8: Return '1984' from Bob ==========");
            library.returnBook(book1);

            System.out.println("\n========== TRANSACTION 9: Attempt to return '1984' again (should FAIL) ==========");
            try {
                library.returnBook(book1);
            } catch (IllegalStateException e) {
                System.out.println("REJECTED: " + e.getMessage());
            }

            // --- Final state ---
            System.out.println("\n========== LIBRARY STATE - FINAL ==========");
            printLibraryState(book1, book2, book3, member1, member2);
        }

        /**
         * Helper method to print the state of books and members.
         */
        private static void printLibraryState(Book book1, Book book2, Book book3,
                Member member1, Member member2) {
            System.out.println("--- Books ---");
            printBook(book1);
            printBook(book2);
            printBook(book3);

            System.out.println("--- Members ---");
            printMember(member1);
            printMember(member2);
        }

        private static void printBook(Book book) {
            String status = book.isAvailable() ? "Available" : "ON LOAN";
            System.out.printf("  [%s] %s by %s  (ISBN: %s) - %s%n",
                    status, book.getTitle(), book.getAuthor(), book.getIsbn(), status);
        }

        private static void printMember(Member member) {
            System.out.println("  " + member.getName() + " (ID: " + member.getMemberId() + ")");
            if (member.getLoans().isEmpty()) {
                System.out.println("    No active loans");
            } else {
                System.out.println("    Active loans:");
                for (Loan loan : member.getLoans()) {
                    System.out.printf("      - '%s' (borrowed: %s, due: %s)%n",
                            loan.getBook().getTitle(),
                            loan.getBorrowDate(),
                            loan.getDueDate());
                }
            }
        }
    }
}
