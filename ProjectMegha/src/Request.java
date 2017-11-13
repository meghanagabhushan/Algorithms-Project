import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Megha Nagabhushan on 11/5/2017.
 */
public class Request {
    public static void main(String[] args) throws IOException{
        String request = "1";
        String zipcode = "64110";
        requestVehicle(request,zipcode);


    }
    public static void requestVehicle(String request,String zipcode) throws IOException{
        String distanceFile = "data/EmergencyVehicle";
        BufferedReader distanceReader = new BufferedReader(new FileReader(distanceFile));
        //BufferedWriter writer = new BufferedWriter(new FileWriter(distanceFile));
        String line1;
        while ((line1 = distanceReader.readLine()) != null) {
            List<String> items = Arrays.asList(line1.split("\\s*,\\s*"));
            if(items.get(1).equals(zipcode) && items.get(2).equals(request) && items.get(3).equals("1")){
                System.out.println("Vehicle is available in your zipcode. Its ID is :"+items.get(0));
                //line1.replace(line1,items.get(0)+","+items.get(1)+","+items.get(2)+",0");
                //writer.write(line1.replaceAll("1","0"));
                break;
            }
            //writer.close();
        }
    }
}
