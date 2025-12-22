public class Bus extends Vehicle{
    public Bus(String model, int year, double basePrice) {
        super(model, year, basePrice);
    }
    private int passengerCapacity;
    @Override
    public double calculateInsuranceFee(){
        return (super.getBasePrice()-0.67*(super.getAge(2025)));
    }
}
