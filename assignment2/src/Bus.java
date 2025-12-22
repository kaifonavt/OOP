public class Bus extends Vehicle{
    private int passengerCapacity;
    public Bus(String model, int year, double basePrice, int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
        super(model, year, basePrice);
    }
    @Override
    public double calculateInsuranceFee(){
        return (super.getBasePrice() * (1 - 0.067*(super.getAge(2025))));
    }
    @Override
    public void performService() {
        System.out.println("Bus service finished successfully.");
    }
    @Override
    public int getServiceIntervalKm() {
        return 420*super.getAge(2025);
    }
}
