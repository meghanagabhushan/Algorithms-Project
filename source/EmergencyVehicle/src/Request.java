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

            new Thread(() -> startRequest()).start();
            new Thread(() -> completeRequest()).start();
            List<EmergencyVehicle> vehicleObjList = new ArrayList<>();
            File distanceFile = new File("data/EmergencyVehicle.csv");
            BufferedReader br = new BufferedReader(new FileReader(distanceFile));
            String line="";
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                EmergencyVehicle emergencyVehicles = createEmergencyVehicle(attributes);
                vehicleObjList.add(emergencyVehicles);
            }
            br.close();

            List<RequestVehicle> requestObjList = new ArrayList<>();
            File reqFile = new File("data/TableRequest.csv");
            BufferedReader breader = new BufferedReader(new FileReader(reqFile));
            String line1="";
            while ((line1 = breader.readLine()) != null) {
                String[] lines = line1.split(",");
                RequestVehicle requestVehicles = createRequestVehicle(lines);
                requestObjList.add(requestVehicles);
            }
            breader.close();

           for(RequestVehicle r : requestObjList){
                System.out.println("VehicleType: " + r.vehicleType);
                System.out.println("ZipCode: " + r.zipCode);
               System.out.println("Requested Vehicles: " + r.number);
                requestVehicle(r.vehicleType,r.zipCode, r.number,vehicleObjList);
            }



        }
        public static void requestVehicle(int requestType, int zipcode, int number,List<EmergencyVehicle> vehicleObj) throws IOException {

            for(EmergencyVehicle e : vehicleObj){
                //System.out.println(e.vehicleID);
                if(e.vehicleType == requestType && e.zipCode==zipcode){
                    if( e.availability>=number){
                        e.decreaseAvailability(number);
                        System.out.println(e);
                        e.scheduleRelease(number);
                        System.out.println(e);
                        break;
                    }

                    /*else if(e.availability>0){

                    }*/


                }
               /* else{
                    //algorithm part to be added
                }*/

            }

        }

    /*List<RequestComplete> requestComplete = new ArrayList<>();
    File completeFile = new File("data/RequestComplete");
    BufferedReader bufferedReader = new BufferedReader(new FileReader(completeFile));
    String line1="";
            while ((line1 = bufferedReader.readLine()) != null) {
        String[] lines = line1.split(",");
        RequestVehicle requestCompleted = completeRequest(lines);
        requestComplete.add(requestCompleted);
    }
            bufferedReader.close();*/

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
             int number  = Integer.parseInt(lines[2]);

             return new RequestVehicle(vehicleType, zipCode, number);
        }
        public static void startRequest(){

        }
        public static void completeRequest(){

        }
    }