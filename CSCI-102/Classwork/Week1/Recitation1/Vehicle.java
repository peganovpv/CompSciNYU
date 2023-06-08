public abstract class Vehicle{

    private int numAxles; // number of axles
    private String owner; // name of owner
    private String numberPlate; // number plate
    private int amountOwed; // amount owed
    private double accountNumber; // account number
    private int charge; // charge for crossing toll

    public Vehicle(String owner, String numberPlate, int numAxles, int charge) {
        this.owner = owner;
        this.numberPlate = numberPlate;
        this.numAxles = numAxles;
        this.amountOwed = 0;
        this.accountNumber = Math.random() * (double)100000000;
        this.charge = charge;
    }

    public void crossedToll() {
        System.out.println("Vehicle " + this.numberPlate + " crossed toll");
        this.amountOwed += this.charge;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public int getnumAxles(){
        return this.numAxles;
    }

    public String getOwner(){
        return this.owner;
    }

    public String getNumberPlate(){
        return this.numberPlate;
    }

    public int getAmountOwed(){
        return this.amountOwed;
    }

    public double getAccountNumber(){
        return this.accountNumber;
    }

}
