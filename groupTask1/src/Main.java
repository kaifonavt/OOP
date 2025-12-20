void main() {
    Passenger passenger = new Passenger("Artur");
    Driver driver = new Driver("Alex");
    Car car = new Car("Toyota Camry", "777AAA");
    Trip trip = passenger.requestTrip(driver, car);
    driver.acceptTrip(trip);
    trip.startTrip();
    trip.finishTrip();
    passenger.rateDriver(driver, 5);
}
