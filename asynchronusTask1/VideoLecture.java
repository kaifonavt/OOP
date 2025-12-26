public class VideoLecture extends ContentItem{
    private String quality;
    @Override
    public double getLicenseCost(int currentYear){
        return 0.03*super.getDurationMinutes()+((super.getAge<=2)?5:2);
    }

}