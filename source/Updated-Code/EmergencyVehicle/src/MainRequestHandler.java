import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Megha Nagabhushan on 11/26/2017.
 */
public class MainRequestHandler {
    public static void main(String[] args) {

        MainRequestHandler mainRequestHandler = new MainRequestHandler();


        //Thread to start the request
        new Thread(() -> {
            try {
                mainRequestHandler.startRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        //Thread to update the completed request
        new Thread(() -> {
            try {
                mainRequestHandler.completeRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    public void startRequest() throws IOException {

        //Saving the contents on Emergency Vehicle in a Hash Map with vehicle ID as its Key and zipcode,type and availability as its value

        Map<String, String> vehiclesMap = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new FileReader("data/EmergencyVehicle.txt"));
        String line = "";
        while ((line = in.readLine()) != null) {
            String parts[] = line.split(",");
            vehiclesMap.put(parts[0], parts[1] + "," + parts[2] + "," + parts[3]);
        }
        in.close();

        //Saving requesttable File into a list
        ArrayList<String> requestList = new ArrayList<>();
        File reqFile = new File("data/RequestTable");
        BufferedReader breader = new BufferedReader(new FileReader(reqFile));
        String line1 = "";
        while ((line1 = breader.readLine()) != null) {
            requestList.add(line1);
        }
        breader.close();

        for (String request : requestList) {
            String requests[] = request.split(",");
            String requestType = requests[0];
            String requestZipcode = requests[1];
            int number = Integer.parseInt(requests[2]);
            int count =0;
            while(number>count){
                for (Map.Entry<String, String> entry : vehiclesMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    String linesplits[] = value.split(",");
                    String vehicleType = linesplits[1];
                    String vehicleZipCode = linesplits[0];
                    String vehicleAvailability = linesplits[2];
                    if (requestType.equals(vehicleType) && requestZipcode.equals(vehicleZipCode) && vehicleAvailability.equals("1")) {
                        System.out.println("Dispatched Vehicle : " + key);
                        entry.setValue(vehicleZipCode + "," + vehicleType + ",0");
                        count++;
                        break;
                    }
                    else{
                            count=number;
                        //write the algorithm codeb
                    }
                }
            }

        }

        File myFoo = new File("data/EmergencyVehicle.txt");
        FileWriter fooWriter = new FileWriter(myFoo, false); // true to append*/
        for (Map.Entry<String, String> entry : vehiclesMap.entrySet()) {
            //System.out.println(entry.getKey() + "," + entry.getValue() + "\n");
            fooWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
        fooWriter.close();

    }

    public void completeRequest() throws IOException {

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

        System.out.println("****Complete Request update****");
        File myFoo = new File("data/EmergencyVehicle.txt");
       FileWriter fooWriter = new FileWriter(myFoo, false); // true to append
        for (Map.Entry<String, String> entry : vehiclesMap.entrySet()) {
            //System.out.println(entry.getKey() + "," + entry.getValue() + "\n");
            fooWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
       fooWriter.close();
    }
}
