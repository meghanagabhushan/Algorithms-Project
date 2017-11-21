package Integrated;
public class EmergencyVehicle {
        int vehicleID;
        int vehicleType;
        int zipCode;
        int availability;

    public EmergencyVehicle(int vehicleID, int zipCode, int vehicleType, int availability) {

        this.vehicleID = vehicleID;
        this.zipCode = zipCode;
        this.vehicleType = vehicleType;
        this.availability = availability;

    }
    public void scheduleRelease(int number){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        increaseAvailability(number);
                        System.out.println("New Availability: "+availability);
                    }
                },
                10
        );
    }


    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
    public void increaseAvailability(int number){
        availability= availability+number;
    }
    public void decreaseAvailability(int number){
        availability = availability-number;
    }
    public String toString() {
        return "Vehicle Info: [vehicleType=" + vehicleType + ", zipcode=" + zipCode + ", " +
                ", Availability =" +availability +"]";
    }

}
