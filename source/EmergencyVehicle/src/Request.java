import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
     * Created by Megha Nagabhushan on 11/5/2017.
     */
    public class Request {
        public static void main(String[] args) throws IOException{
            int requestType = 2;
            int zipcode = 64118;
            int number = 4;
            requestVehicle(requestType,zipcode,number);


        }
        public static void requestVehicle(int requestType,int zipcode,int number) throws IOException {
            List<EmergencyVehicle> vehicleObj = new ArrayList<>();
            File distanceFile = new File("./data/EmergencyVehicle.csv");
            BufferedReader br = new BufferedReader(new FileReader(distanceFile));
            String line="";
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                EmergencyVehicle emergencyVehicles = createEmergencyVehicle(attributes);
                vehicleObj.add(emergencyVehicles);
            }
            for(EmergencyVehicle e : vehicleObj){
                System.out.println(e.vehicleID);
                if(e.vehicleType == requestType && e.zipCode==zipcode && e.availability>=number){
                    e.decreaseAvailability();
                    System.out.println(e);
                    e.scheduleRelease();
                    //e.release();
                    //System.out.println("Next Line");
                    System.out.println(e);

                    break;
                }
                else if(e.vehicleType == requestType && e.zipCode==zipcode && e.availability == 0){
                    System.out.println("Calculate Nearest available vehicle distance.");
                    //int newZipCode = m.calculateDistance();

                    break;
                }
                else
                    continue;
            }
            br.close();
        }

        private static EmergencyVehicle createEmergencyVehicle(String[] attributes) {
            int vehicleID = Integer.parseInt(attributes[0]);
            int zipCode = Integer.parseInt(attributes[1]);
            int vehicleType = Integer.parseInt(attributes[2]);
            int availability = Integer.parseInt(attributes[3]);

            return new EmergencyVehicle(vehicleID,zipCode,vehicleType,availability);
        }

    }