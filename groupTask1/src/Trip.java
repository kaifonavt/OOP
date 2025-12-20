public class Trip {
    private Passenger passenger;
    private Driver driver;
    private Car car;
    private boolean accepted;
    private boolean started;
    private boolean finished;
    public Trip(Passenger passenger, Driver driver, Car car) {
        this.passenger = passenger;
        this.driver = driver;
        this.car = car;
    }
    public Passenger getPassenger() {return passenger;}
    public void setPassenger(Passenger passenger) {this.passenger = passenger;}
    public Driver getDriver() {return driver;}
    public void setDriver(Driver driver) {this.driver = driver;}
    public Car getCar() {return car;}
    public void setCar(Car car) {this.car = car;}
    public boolean isAccepted() {return accepted;}
    public void setAccepted(boolean accepted) {this.accepted = accepted;}
    public void startTrip() {
        if (!accepted) {
            throw new IllegalStateException("Trip cannot start: not accepted by driver");
        }
        started = true;
        System.out.println("Trip started using car: " + car.getModel());
    }

    public void finishTrip() {
        if (!started) {
            throw new IllegalStateException("Trip cannot finish: not started");
        }
        finished = true;
        System.out.println("Trip finished successfully.");
    }
}