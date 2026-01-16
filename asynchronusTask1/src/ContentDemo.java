import java.util.ArrayList;

public class ContentDemo  {
    public static void main(String[] args) {
        ArrayList<ContentItem> items = new ArrayList<>();
        items.add(new VideoLecture("Introduction",2006,109));
        items.add(new VideoLecture("Basic Syntax",2006,91));
        items.add(new PodcastEpisode("Honestly",2014,153));
        items.add(new PodcastEpisode("No Soul",2022,180));
        for (ContentItem item: items){
            System.out.println(item.toString()+ " | licenseCost=" + item.getLicenseCost(2025));
        }
    }
}