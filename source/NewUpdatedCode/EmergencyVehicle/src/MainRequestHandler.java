/*
import java.io.*;
import java.util.*;


//import Dijkstras.Djkshtatra;
//import Dijkstras.Edge;
//import Dijkstras.Pair;

*/
/**
 * Created by Megha Nagabhushan on 11/26/2017.
 *//*

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
        */
/*new Thread(() -> {
            try {
                mainRequestHandler.completeRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*//*


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
        File reqFile = new File("data/requestOld");
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
            for (Map.Entry<String, String> entry : vehiclesMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String linesplits[] = value.split(",");
                String vehicleType = linesplits[1];
                String vehicleZipCode = linesplits[0];
                String vehicleAvailability = linesplits[2];
                if (requestType.equals(vehicleType) && requestZipcode.equals(vehicleZipCode) && vehicleAvailability.equals("1") && number>count) {
                    System.out.println("Dispatched Vehicle : " + key);
                    entry.setValue(vehicleZipCode + "," + vehicleType + ",0");
                    count++;
                    //break;
                }
                else if (count >= number) {
                    break;
                }
            }
            while(number>count) {

                //count = number;
                //ealgorithmImplementation(requestType, requestZipcode, number,count,vehiclesMap);
                //write the algorithm codeb
            	 List<Path> list = new ArrayList();
                 List<Path> list1 = new ArrayList();
                 FileReader in1 = new FileReader("data/Distance.txt");
             	BufferedReader br = new BufferedReader(in1);
                 FileWriter fw;
                 BufferedWriter bw;
                 fw = new FileWriter("data/Output.txt");
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
                     list.add(new Path(a.get(j), a.get(j+1), Integer.parseInt(a.get(j+2))));
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
                 String input_var = "64117"; // insert the zipcode here
                 for(int i=0; i<uniqueNodes.size();i++){      
                     if( !(input_var.equals(uniqueNodes.get(i)))){
                         //System.out.println( input_var +"-"+uniqueNodes.get(i));
                         object = new Djkshtatra(list);
//                         if(object.compute( input_var , uniqueNodes.get(i))== null){
//                             bw.write(input_var+" -> "+ uniqueNodes.get(i)+" : path does not exists");
//                             bw.newLine();
//                         }
//                         else{
                             list1 = object.compute( input_var , uniqueNodes.get(i)); 
                             sum =0;
                             //if((list1.get(0).getSource()).equals(input_var)){
                                 for (Path path : list1)
                                 {   
                                     sum++;
                                     //sum = sum + path.getWeight();    
                                     bw.write(path.getSource() + " -> " + path.getDestination()+" ");
                                 } 

                                 bw.write(" distance : "+list1.get(sum-1).getWeight() );
                                 bw.newLine();
//                             }
//                             else{
//                                 bw.write(input_var+" -> "+ uniqueNodes.get(i)+" : path does not exists");
//                                 bw.newLine();
//                             }                    
                         //}                               
                         list1 = null;
                         object = null;
                         
                     }
                 }     
                 bw.flush();
                 bw.close();
            	
            }
                    
                
        }

        File myFoo = new File("data/EmergencyVehicle.txt");
        FileWriter fooWriter = new FileWriter(myFoo, false); // true to append*//*

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
    
*/
/* public void algorithmImplementation(String requestType, String requestZipcode, int number, int count, Map<String, String> vehiclesMap) throws IOException {
     List<Edge> list = new ArrayList();
     List<Edge> list1 = new ArrayList();
     FileReader inp = new FileReader("data/NewDistance.txt");
     BufferedReader br = new BufferedReader(inp);
     FileWriter fw;
     BufferedWriter bw;
     fw = new FileWriter("data/Output.txt");
     bw = new BufferedWriter(fw);
     String line2;
     int count1=0,j=0;
     String[] values;
     ArrayList<String> a= new ArrayList<String>();
     while ((line2 = br.readLine()) != null) {
         values = line2.split(",");
         for (String s : values)
             a.add(s);
         count1++;
     }
     inp.close();

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
     HashMap<String,Integer> nearestNeighbour = new HashMap<String, Integer>();
     for(int i=0; i<uniqueNodes.size();i++){
         if( uniqueNodes.get(i)!= requestZipcode){
             object = new Djkshtatra(list);
             list1 = object.compute(requestZipcode, uniqueNodes.get(i));
             sum =0;
             for (Edge path : list1)
             {
                 sum = sum + path.getWeight();
                 bw.write(path.getSource() + " -> " + path.getDestination()+" ");
             }
             bw.write(" distance : "+sum + "\n");
             nearestNeighbour.put(uniqueNodes.get(i),sum);
             list1 = null;
             object = null;
         }
     }
     bw.flush();
     bw.close();
     int min = Collections.min(nearestNeighbour.values());
     System.out.println("Prinitng min"+min);
     for(Map.Entry<String, Integer> entry : nearestNeighbour.entrySet()){
         if(entry.getValue().equals(min)){
             System.out.println("the zipcode is "+entry.getKey());
             for (Map.Entry<String, String> entry2 : vehiclesMap.entrySet()) {
                 String key = entry2.getKey();
                 String value = entry2.getValue();
                 String linesplits[] = value.split(",");
                 String vehicleType = linesplits[1];
                 String vehicleZipCode = linesplits[0];
                 String vehicleAvailability = linesplits[2];
                 if (requestType.equals(vehicleType) && requestZipcode.equals(entry.getKey()) && vehicleAvailability.equals("1") && number>count) {
                     System.out.println("Dispatched Vehicle : " + key);
                     entry2.setValue(entry.getKey() + "," + vehicleType + ",0");
                     count++;
                     //break;
                 }
                 else if (count >= number) {
                     break;
                 }
             }

         }
     }
     //System.out.println(nearestNeighbour);;

 }*//*

   
}
*/
