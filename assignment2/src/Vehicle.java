public abstract class Vehicle implements Serviceable {
    private int id;
    private String model;
    private int year;
    private static int idGen;
    private double basePrice;
    public Vehicle(String model, int year, double basePrice) {
        id = idGen++;
        this.model = model;
        this.year = year;
        this.basePrice = basePrice;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getModel() {return model;}
    public void setModel(String model) {
        if ((model == null)||(model.trim().isEmpty()))
        {
            throw new IllegalArgumentException("Model name cannot be empty");
        }
        else {
            this.model = model;
        }
    }
    public int getYear() {return year;}
    public void setYear(int year) {
        if ((1885>year)||(year>2025))
        {
            throw new IllegalArgumentException("Year out of range");
        }
        else {
            this.year = year;
        }
    }
    public double getBasePrice() {return basePrice;}
    public void setBasePrice(double basePrice) {
        if (basePrice < 0){
            throw new IllegalArgumentException("Base price cannot be less than 0");
        }
        else {
            this.basePrice = basePrice;
        }
    }
    public int getAge(int currentYear){
        return(currentYear-this.year);
    }
    public abstract double calculateInsuranceFee();
    @Override
    public String toString() {
        return "Vehicle{id=" + id +
                ", model='" + model + '\'' +
                ", price='" + basePrice + '\'' +
                ", year=" + year + '}';
    }
}