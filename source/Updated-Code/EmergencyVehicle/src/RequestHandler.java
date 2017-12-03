
import java.io.*;
import java.util.*;


/**
 * Created by Megha Nagabhushan on 11/26/2017.
 *
 */
public class RequestHandler {


    private List<Edge> shortestEdge;

    private Map distance;

    public RequestHandler() throws IOException {
    }

    public static void main(String[] args) throws IOException {

        RequestHandler requestHandler = new RequestHandler();
        //requestHandler.startRequest();
        //requestHandler.completeRequest();
        new Thread() {
            public void run() {
                try {
                    requestHandler.startRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            public void run() {
                try {
                    requestHandler.completeRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    public synchronized void startRequest() throws IOException {

        ShortestPath shortestPath=new ShortestPath();

        System.out.printf("%1s  %-7s   %-7s   %-6s   %-6s   %-6s%n","Request ID","Request Type","Request ZipCode","Nearest Zipcode","Dispatched Vehicle ID","Distance from Source");
        //Saving the contents of Emergency Vehicle  File into a Hash Map with vehicle ID as its Key and zipcode,type and availability as its value
        Map<String, String> vehiclesMap = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new FileReader("data/EmergencyVehicle.txt"));
        String line = "";
        while ((line = in.readLine()) != null) {
            String parts[] = line.split(",");
            vehiclesMap.put(parts[0], parts[1] + "," + parts[2] + "," + parts[3]);
        }
        in.close();

        //Saving requestTable File into a Hash Map with Request ID as the Key and Type, zipcode and assigned Vehicle as its value
        Map<String, String> requestMap = new HashMap<String, String>();
        File reqFile = new File("data/RequestTable");
        BufferedReader breader = new BufferedReader(new FileReader(reqFile));
        String line1 = "";
        while ((line1 = breader.readLine()) != null) {
            String parts[] = line1.split(",");
            requestMap.put(parts[0], parts[1] + "," + parts[2] + "," + parts[3]);
        }
        breader.close();

        for (Map.Entry<String, String> request : requestMap.entrySet()) {
            String requestID = request.getKey();
            String requestValue = request.getValue();
            String requests[] = requestValue.split(",");
            String requestType = requests[0];
            String requestZipcode = requests[1];
            String dispatchedVehicle = "";
            String nearestZipCode = "";
            int distance = 0;
<<<<<<< HEAD
            int flag = 0;

            for (Map.Entry<String, String> entry : vehiclesMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String linesplits[] = value.split(",");
                String vehicleType = linesplits[1];
                String vehicleZipCode = linesplits[0];
                String vehicleAvailability = linesplits[2];
                if (requestType.equals(vehicleType) && requestZipcode.equals(vehicleZipCode) && vehicleAvailability.equals("1")) {
                    //System.out.println("Dispatched Vehicle : " + key +" for Request ID : "+requestID);
                    dispatchedVehicle = key;
                    nearestZipCode = requestZipcode;
                    distance = 0;
                    //changing the availability to 0 once the request is processed
                    entry.setValue(vehicleZipCode + "," + vehicleType + ",0");
                    //flag = 1;
                    break;
                }
                /*else {
                    //algorithmImplementation(requestType, requestZipcode,vehiclesMap);
                    dispatchedVehicle = "Not Found";
                    nearestZipCode = "Not Found";
                    distance = -1;
                }*/
=======
            boolean flag= false;

            checkEmergency(vehiclesMap,requestZipcode,requestType,flag);

            while(!flag){
                String newZipCode = shortestPath.algorithmImplementation(requestZipcode);
                String splits[]=newZipCode.split(",");
>>>>>>> 8152c15d2dc6d2e17f817b00434a35d30198717d

                System.out.println("Nearest Neighbor: "+splits[0]+"\tDistance:"+splits[1]);
                //checkEmergency(vehiclesMap,splits[0],requestType,flag);
                dispatchedVehicle = "Not Found";
                nearestZipCode = "Not Found";
                distance = -1;

               flag=true;
            }
<<<<<<< HEAD
            /*while(flag == 0){

            }*/
            //setting the id of the vehicle that was assigned once the request is processed
            request.setValue(requestType+","+requestZipcode+","+dispatchedVehicle);
            printResult(requestID,requestType,requestZipcode,nearestZipCode,dispatchedVehicle,distance);
=======
>>>>>>> 8152c15d2dc6d2e17f817b00434a35d30198717d

            //setting the id of the vehicle that was assigned once the request is processed
            request.setValue(requestType + "," + requestZipcode + "," + dispatchedVehicle);
            printResult(requestID, requestType, requestZipcode, nearestZipCode, dispatchedVehicle, distance);
        }


        //updating the Emergency Vehicle File with the new availability [writing the Hash map into the file]
        File myFoo = new File("data/EmergencyVehicle.txt");
        FileWriter fooWriter = new FileWriter(myFoo, false); // true to append*/
        for(Map.Entry<String, String> vehicleEntry : vehiclesMap.entrySet()) {
            //System.out.println(entry.getKey() + "," + entry.getValue() + "\n");
            fooWriter.write(vehicleEntry.getKey() + "," + vehicleEntry.getValue() + "\n");
        }
        fooWriter.close();

        //updating the Request Table file with the assigned vehicles [writing the hash map into the file]
        File myFoo2 = new File("data/RequestTable");
        FileWriter fooWriter2 = new FileWriter(myFoo2, false); // true to append*/
        for(Map.Entry<String, String> requestEntry : requestMap.entrySet()) {
            //System.out.println(entry.getKey() + "," + entry.getValue() + "\n");
            fooWriter2.write(requestEntry.getKey() + "," + requestEntry.getValue() + "\n");
        }
        fooWriter2.close();
    }

    public synchronized void completeRequest() throws IOException {

        //writing down ids of all vehicles that completed the request into an array list
        ArrayList<String> completeList = new ArrayList<>();
        File reqFile = new File("data/RequestComplete");
        BufferedReader breader = new BufferedReader(new FileReader(reqFile));
        String line1 = "";
        while ((line1 = breader.readLine()) != null) {
            completeList.add(line1);
        }
        breader.close();

        //Saving the contents on Emergency Vehicle in a Hash Map with vehicle ID as its Key and zipcode,type and availability as its value
        Map<String, String> vehiclesMap = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new FileReader("data/EmergencyVehicle.txt"));
        String line = "";
        while ((line = in.readLine()) != null) {
            String parts[] = line.split(",");
            vehiclesMap.put(parts[0], parts[1] + "," + parts[2] + "," + parts[3]);
        }
        in.close();

        //Setting back the availability to 1 once the vehicle returns.
        for (String str : completeList) {
            for (Map.Entry<String, String> entry : vehiclesMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String linesplits[] = value.split(",");
                String vehicleType = linesplits[1];
                String vehicleZipCode = linesplits[0];
                String vehicleAvailability = linesplits[2];
                if (key.equals(str)) {
                    entry.setValue(vehicleZipCode + "," + vehicleType + ",1");
                }
            }
        }

        //Updating the Emergency Vehicle file with the new availability
        System.out.println("****Complete Request update****");
        File myFoo = new File("data/EmergencyVehicle.txt");
        FileWriter fooWriter = new FileWriter(myFoo, false); // true to append
        for (Map.Entry<String, String> entry : vehiclesMap.entrySet()) {
            //System.out.println(entry.getKey() + "," + entry.getValue() + "\n");
            fooWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
        fooWriter.close();
    }

    //Printing the final table of results
    public void printResult(String requestID,String requestType,String requestZipcode,String nearestZipCode,String dispatchedVehicle,int distance){
        //System.out.println("Request ID : "+requestID+"\t"+"Request Type : "+requestType+"\t"+"Request Zipcode : "+requestZipcode+"\t"+"Nearest Zipcode : "+nearestZipCode+"\t"+"Dispatched Vehicle ID : "+dispatchedVehicle+"\t"+"Distance from the source : "+distance+"\n");
        System.out.printf("%1s  %15s   %15s   %15s   %15s   %15s%n", requestID, requestType, requestZipcode, nearestZipCode, dispatchedVehicle,distance);
    }

    public void checkEmergency(Map<String,String> vehiclesMap, String requestZipcode,String requestType,boolean flag){
        for (Map.Entry<String, String> entry : vehiclesMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String linesplits[] = value.split(",");
            String vehicleType = linesplits[1];
            String vehicleZipCode = linesplits[0];
            String vehicleAvailability = linesplits[2];
            String dispatchedVehicle = "";
            String nearestZipCode = "";
            int distance = 0;
            if (requestType.equals(vehicleType) && requestZipcode.equals(vehicleZipCode) && vehicleAvailability.equals("1")) {
                //System.out.println("Dispatched Vehicle : " + key +" for Request ID : "+requestID);
                dispatchedVehicle = key;
                nearestZipCode = requestZipcode;
                distance = 0;
                //changing the availability to 0 once the request is processed
                entry.setValue(vehicleZipCode + "," + vehicleType + ",0");
                flag=true;
                break;
            }
        }
    }
}
