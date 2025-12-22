public class Car extends Vehicle {
    private int numberOfDoors;
    public Car(String model, int year, double basePrice, int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
        super(model, year, basePrice);
    }
    @Override
    public double calculateInsuranceFee() {
        return (super.getBasePrice() * (1 - 0.067*(super.getAge(2025))));
    }
    @Override
    public void performService() {
        System.out.println("Car service finished successfully.");
    }
    @Override
    public int getServiceIntervalKm() {
        return 1337*super.getAge(2025);
    }
}