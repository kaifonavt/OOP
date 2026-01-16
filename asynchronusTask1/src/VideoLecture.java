public class VideoLecture extends ContentItem{
    private String quality;
    public VideoLecture(String title, int year, int durationMinutes) {
        super(title, year, durationMinutes);
    }
    @Override
    public double getLicenseCost(int currentYear){
        return 0.03*super.getDurationMinutes()+super.getAge(2025)<=2?5:2;
    }
    @Override
    public void download() {
        System.out.println("Downloading video in" + quality + "...");
    }
    @Override
    public int getMaxDownloadsPerDay() {
        return 3;
    }
    @Override
    public String toString() {
        return "ContentItem{id=" + super.getId() +
                ", title='" + super.getTitle() +
                ", duration='" + super.getDurationMinutes() +
                ", year=" + super.getYear() +
                ", quality='" + quality +'}';
    }
}