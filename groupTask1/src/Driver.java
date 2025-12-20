public class Driver {

    private String name;
    private int rating;
    public String getName() {return name;}
    public Driver(String name) {this.name = name;}
    public void acceptTrip(Trip trip) {
        trip.setAccepted(true);
        System.out.println("Driver " + name + " accepted the trip.");
    }
    public void setRating(int rating) {this.rating = rating;}

}