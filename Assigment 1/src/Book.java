public class Book {
    private int id;
    private static int idGen = 1;
    private String title;
    private String author;
    private int year;
    private boolean available;
    public Book() {
        this.id = idGen++;
        this.available = true;
    }
    public Book(String title, String author, int year) {
        this();
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }
    public int getId() {return id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        this.author = author;
    }
    public int getYear() {return year;}
    public void setYear(int year) {
        if (year < 1500 || year > 2025) {
            throw new IllegalArgumentException("Year must be in a reasonable range");
        }
        this.year = year;
    }
    public boolean isAvailable() {return available;}
    public void markAsBorrowed() {this.available = false;}
    public void markAsReturned() {this.available = true;}
    @Override
    public String toString() {
        return "Book{id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", available=" + available + '}';
    }
}