public class Passenger {
    private String name;
    public Passenger(String name) {
        this.name = name;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Trip requestTrip(Driver driver, Car car) {
        System.out.println(name + " requested a trip.");
        return new Trip(this, driver, car);
    }
    public void rateDriver(Driver driver, int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        driver.setRating(rating);
        System.out.println(name + " rated the driver: " + rating);
    }
}
