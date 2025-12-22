public class Car extends Vehicle {
    public Car(String model, int year, double basePrice) {
        super(model, year, basePrice);
    }
    private int numberOfDoors;
    @Override
    public double calculateInsuranceFee() {
        return super.calculateInsuranceFee();
    }
}
