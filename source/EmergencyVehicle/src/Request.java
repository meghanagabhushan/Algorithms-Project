import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
     * Created by Megha Nagabhushan on 11/5/2017.
     */
    public class Request {
        public static void main(String[] args) throws IOException{
            List<EmergencyVehicle> vehicleObj = new ArrayList<>();
            File distanceFile = new File("./data/EmergencyVehicle.csv");
            BufferedReader br = new BufferedReader(new FileReader(distanceFile));
            String line="";
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                EmergencyVehicle emergencyVehicles = createEmergencyVehicle(attributes);
                vehicleObj.add(emergencyVehicles);
            }
            br.close();

            List<RequestVehicle> requestObj = new ArrayList<>();
            File reqFile = new File("./data/TableRequest.csv");
            BufferedReader breader = new BufferedReader(new FileReader(reqFile));
            String line1="";
            while ((line1 = breader.readLine()) != null) {
                String[] lines = line1.split(",");
                RequestVehicle requestVehicles = createRequestVehicle(lines);
                requestObj.add(requestVehicles);
            }
            breader.close();

           for(RequestVehicle r : requestObj){
                System.out.println("VehicleType: " + r.vehicleType);
                System.out.println("ZipCode: " + r.zipCode);
                requestVehicle(r.vehicleType,r.zipCode, vehicleObj);
            }



        }
        public static void requestVehicle(int requestType, int zipcode, List<EmergencyVehicle> vehicleObj) throws IOException {

            for(EmergencyVehicle e : vehicleObj){
                //System.out.println(e.vehicleID);
                if(e.vehicleType == requestType && e.zipCode==zipcode && e.availability>0){
                    e.decreaseAvailability();
                    System.out.println(e);
                    e.scheduleRelease();
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

        }

        private static EmergencyVehicle createEmergencyVehicle(String[] attributes) {
            int vehicleID = Integer.parseInt(attributes[0]);
            int zipCode = Integer.parseInt(attributes[1]);
            int vehicleType = Integer.parseInt(attributes[2]);
            int availability = Integer.parseInt(attributes[3]);

            return new EmergencyVehicle(vehicleID,zipCode,vehicleType,availability);
        }

         private static RequestVehicle createRequestVehicle(String[] lines) {
             //String s = String.lines[0];
             int vehicleType = Integer.parseInt(lines[0]);
             int zipCode = Integer.parseInt(lines[1]);

             return new RequestVehicle(vehicleType, zipCode);
        }

    }