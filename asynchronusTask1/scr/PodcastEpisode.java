public class PodcastEpisode extends ContentItem{
    private String hostName;
    public PodcastEpisode(String title, int year, int durationMinutes) {
        super(title, year, durationMinutes);
    }
    @Override
    public double getLicenseCost(int currentYear) {
        return 0.03 * super.getDurationMinutes() + super.getAge(2025)<=2?3:1;
    }
    @Override
    public void download() {
        System.out.println("Downloading podcast hosted by" + hostName + "...");
    }
    @Override
    public int getMaxDownloadsPerDay() {
        return 10;
    }
    @Override
    public String toString() {
        return "ContentItem{id=" + super.getId() +
                ", title='" + super.getTitle() +
                ", duration='" + super.getDurationMinutes() +
                ", year=" + super.getYear() +
                ", host='" + hostName +'}';
    }
}