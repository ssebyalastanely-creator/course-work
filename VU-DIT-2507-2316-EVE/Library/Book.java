public class Book {

    }
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    // --- Constructors ---

    /**
     * Constructor 1: Creates a book with just ISBN and title.
     * Author defaults to "Unknown" and the book is initially available.
     */
    public Book(String isbn, String title) {
        this(isbn, title, "Unknown");
    }

    /**
     * Constructor 2 (overloaded): Creates a book with ISBN, title, and author.
     * The book is initially available.
     */
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true; // New books are always available
    }

    // --- Getters and Setters ---

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // --- Object overrides ---

    @Override
    public String toString() {
        return "Book{isbn='" + isbn + "', title='" + title +
                "', author='" + author + "', " +
                (available ? "available" : "on loan") + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);

    }
}
