package CSCI-102.Recitation 1;

public class App {

    // Create a vehicle class from which an emergency vehicle, car and truck class will inherit from.

    public class Vehicle{
        private String type;
        private int numWheels;
        private String numberPlate;
        private int amountOwed;

        public Vehicle(String type, int numWheels, String numberPlate, int amountOwed){
            this.type = type;
            this.numWheels = numWheels;
            this.numberPlate = numberPlate;
            this.amountOwed = amountOwed;
        }

        public void CrossedToll(){
            if(this.type.equals("Car")){
                this.amountOwed += 5;
            } else if(this.type.equals("Truck")){
                int numAxles = this.numWheels / 2;
                this.amountOwed += (numAxles * 3);
            } else if(this.type.equals("Emergency Vehicle")){
                this.amountOwed += 0;
            }
        }

    }

    public class Car extends Vehicle{
        public Car(String type, int numWheels, String numberPlate, int amountOwed){
            super(type, numWheels, numberPlate, amountOwed);
        }
    }

    public class Truck extends Vehicle{
        public Truck(String type, int numWheels, String numberPlate, int amountOwed){
            super(type, numWheels, numberPlate, amountOwed);
        }
    }

    public class EmergencyVehicle extends Vehicle{
        public EmergencyVehicle(String type, int numWheels, String numberPlate, int amountOwed){
            super(type, numWheels, numberPlate, amountOwed);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

}
