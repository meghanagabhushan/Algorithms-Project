import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dijkstras.Djkshtatra;
import Dijkstras.Edge;
import Dijkstras.Pair;

public class MainRequestHandler {
	
    private List<Edge> shortestEdge;
    
    private Map distance;
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
        BufferedReader in = new BufferedReader(new FileReader("C:/Users/User/Desktop/djkshtatra/src/djkshtatra/Updated/EmergencyVehicle.txt"));
        String line = "";
        while ((line = in.readLine()) != null) {
            String parts[] = line.split(",");
            vehiclesMap.put(parts[0], parts[1] + "," + parts[2] + "," + parts[3]);
        }
        in.close();

        //Saving requesttable File into a list
        ArrayList<String> requestList = new ArrayList<>();
        File reqFile = new File("C:/Users/User/Desktop/djkshtatra/src/djkshtatra/Updated/RequestTable");
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
                            //count=number;
                        //write the algorithm codeb
                    	 List<Edge> list = new ArrayList();
                         List<Edge> list1 = new ArrayList();
                         FileReader in1 = new FileReader("C:/Users/User/Desktop/New folder/djkshtatra/src/djkshtatra/data.txt");
                     	BufferedReader br = new BufferedReader(in1);
                         FileWriter fw;
                         BufferedWriter bw;
                         fw = new FileWriter("C:/Users/User/Desktop/New folder/djkshtatra/src/djkshtatra/Output.txt");
                         bw = new BufferedWriter(fw);
                     	String l1;
                         int count1=0,j=0;
                     	String[] values;
                     	ArrayList<String> a= new ArrayList<String>(); 
                 	while ((l1 = br.readLine()) != null) {
                             values = l1.split(",");  
                             for (String s : values)
                     		a.add(s);	
                             count1++;
                 	}				
                     	in.close();

                         for(int i=0; i<count1; i++){
                             //System.out.println((a.get(j)).charAt(0)+"-"+(a.get(j+1)).charAt(0)+"-"+Integer.parseInt(a.get(j+2)));
                             list.add(new Edge(a.get(j), a.get(j+1), Integer.parseInt(a.get(j+2))));
                             j+=3;
                         }
                         
                         ArrayList<String> uniqueNodes = new ArrayList<String>();
                         for(int i=0;i<a.size();i++){
                             if(uniqueNodes.contains(a.get(i)) || ((i+1)%3)==0)
                                 continue;
                             
                             else
                                 uniqueNodes.add(a.get(i));
                             
                         } 
                         Djkshtatra object;
                         int sum;
                         String input_var = "64113"; // insert the zipcode here
                         for(int i=0; i<uniqueNodes.size();i++){      
                             if( !(input_var.equals(uniqueNodes.get(i)))){
                                 System.out.println( input_var +"-"+uniqueNodes.get(i));
                                 object = new Djkshtatra(list);
                                 if(object.compute( input_var , uniqueNodes.get(i))== null){
                                     bw.write(input_var+" -> "+ uniqueNodes.get(i)+" : path does not exists");
                                     bw.newLine();
                                 }
                                 else{
                                     list1 = object.compute( input_var , uniqueNodes.get(i)); 
                                     sum =0;
                                     if((list1.get(0).getSource()).equals(input_var)){
                                         for (Edge path : list1)
                                         {   
                                             sum = sum + path.getWeight();    
                                             bw.write(path.getSource() + " -> " + path.getDestination()+" ");
                                         } 

                                         bw.write(" distance : "+sum );
                                         bw.newLine();
                                     }
                                     else{
                                         bw.write(input_var+" -> "+ uniqueNodes.get(i)+" : path does not exists");
                                         bw.newLine();
                                     }                    
                                 }                               
                                 list1 = null;
                                 object = null;
                             }
                         }     
                         bw.flush();
                         bw.close();        
                     }
                
                }
            }

        }

        File myFoo = new File("C:/Users/User/Desktop/djkshtatra/src/djkshtatra/Updated/EmergencyVehicle.txt");
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
        File reqFile = new File("C:/Users/User/Desktop/djkshtatra/src/djkshtatra/Updated/RequestComplete");
        BufferedReader breader = new BufferedReader(new FileReader(reqFile));
        String line1 = "";
        while ((line1 = breader.readLine()) != null) {
            completeList.add(line1);
        }
        breader.close();

        //Saving the contents on Emergency Vehicle in a Hash Map with vehicle ID as its Key and zipcode,type and availability as its value
        Map<String, String> vehiclesMap = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new FileReader("C:/Users/User/Desktop/djkshtatra/src/djkshtatra/Updated/EmergencyVehicle.txt"));
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
        File myFoo = new File("C:/Users/User/Desktop/djkshtatra/src/djkshtatra/Updated/EmergencyVehicle.txt");
       FileWriter fooWriter = new FileWriter(myFoo, false); // true to append
        for (Map.Entry<String, String> entry : vehiclesMap.entrySet()) {
            //System.out.println(entry.getKey() + "," + entry.getValue() + "\n");
            fooWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
       fooWriter.close();
    }
    
 
   
}