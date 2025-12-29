public abstract class ContentItem implements Downloadable {
    private int id;
    private static int idGen;
    private String title;
    private int year;
    private int durationMinutes;
    public ContentItem(String title, int year, int durationMinutes)
    {
        setTitle(title);
        setYear(year);
        setDurationMinutes(durationMinutes);
        this.id = idGen++;
    }
    public String getTitle() {return title;}
    public void setTitle(String title) {
        if ((title == null)||(title.trim().isEmpty())) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        else {this.title = title;}
    }
    public int getId() {return id;}
    public int getYear() {return year;}
    public void setYear(int year) {
        if ((2025<year)||(year<1990)) {
            throw new IllegalArgumentException("Year out of range");
        }
        else {this.year = year;}
    }
    public int getDurationMinutes() {return durationMinutes;}
    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes<0) {
            throw new IllegalArgumentException("Duration time cannot be negative");
        }
        else {this.durationMinutes = durationMinutes;}
    }
    public int getAge(int currentYear){return currentYear - this.getYear();}
    public abstract double getLicenseCost(int currentYear);
    @Override
    public String toString(){
        return "ContentItem{id=" + id +
                ", title='" + title +
                ", duration='" + durationMinutes +
                ", year=" + year + '}';
    }
}