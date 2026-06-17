public class skeleton {
    public class Book {

        private String isbn;
        private String title;
        private String author;
        private boolean available;

        // Constructor 1: without author (author defaults to "Unknown")
        public Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
            this.author = "Unknown";
            this.available = true;
        }

        // Constructor 2 (overload): with author
        public Book(String isbn, String title, String author) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.available = true;
        }

        // Getters
        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return available;
        }

        // Setter for availability
        public void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "isbn='" + isbn + '\'' +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", " + (available ? "Available" : "On Loan") +
                    '}';
        }
    }
}
