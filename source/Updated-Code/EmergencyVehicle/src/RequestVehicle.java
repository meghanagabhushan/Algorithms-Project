public class RequestVehicle {
    int vehicleType;
    int zipCode;
    int number;

    public RequestVehicle(int vehicleType, int zipCode, int number) {

        this.vehicleType = vehicleType;
        this.zipCode = zipCode;
        this.number = number;
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
    @Override
    public String toString() {
        return "Vehicle Info: [vehicleType=" + vehicleType + ", zipcode=" + zipCode +" ]";
    }
}
