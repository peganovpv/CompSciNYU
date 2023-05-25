public class App {

    public static void main(String[] args) {
        
        Car car = new Car("Peter", "WWW11Q", 2);
        Truck truck = new Truck("John", "WWW22Q", 4);
        EmergencyVehicle ev = new EmergencyVehicle("Government", "WWW33Q", 2);

        System.out.println("Car owner: " + car.getOwner());
        System.out.println("Truck owner: " + truck.getOwner());
        System.out.println("Emergency vehicle owner: " + ev.getOwner());

        System.out.println("Car number of axles: " + car.getnumAxles());
        System.out.println("Truck number of axles: " + truck.getnumAxles());
        System.out.println("Emergency vehicle number of axles: " + ev.getnumAxles());

        car.crossedToll();
        truck.crossedToll();
        ev.crossedToll();

        System.out.println("Car amount owed: " + car.getAmountOwed());
        System.out.println("Truck amount owed: " + truck.getAmountOwed());
        System.out.println("Emergency vehicle amount owed: " + ev.getAmountOwed());

    }

}
