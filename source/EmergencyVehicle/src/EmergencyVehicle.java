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
    public void scheduleRelease(){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        increaseAvailability();
                        //System.out.println("Availability: "+availability);
                    }
                },
                1000
        );
    }
    public void release() {
        try {
            Thread.sleep(1000);
            increaseAvailability();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public void increaseAvailability(){
        availability++;
    }
    public void decreaseAvailability(){
        availability--;
    }
    public String toString() {
        return "Vehicle Info: [vehicleType=" + vehicleType + ", zipcode=" + zipCode + ", " +
                "vehicleType=" + vehicleType + ", Availability =" +availability +"]";
    }

}
