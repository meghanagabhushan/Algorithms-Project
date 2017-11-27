
import java.io.*;
import java.util.*;
public class Request {
    private int value;
    private String source;
    private String path_source;
    private String destination;
    private int weight;
    public final List<Path> graph;
    public List<Path> shortestPath;
    public Map<String, Pair> distance;

    public static void main(String[] args) throws IOException{

        /*new Thread(() -> startRequest()).start();
        new Thread(() -> completeRequest()).start();*/
        List<EmergencyVehicle> vehicleObjList = new ArrayList<>();
        File distanceFile = new File("data/EmergencyVehicle.txt");
        BufferedReader br = new BufferedReader(new FileReader(distanceFile));
        String line="";
        while ((line = br.readLine()) != null) {
            String[] attributes = line.split(",");
            EmergencyVehicle emergencyVehicles = createEmergencyVehicle(attributes);
            vehicleObjList.add(emergencyVehicles);
        }
        br.close();

        List<RequestVehicle> requestObjList = new ArrayList<>();
        File reqFile = new File("data/RequestTable.csv");
        BufferedReader breader = new BufferedReader(new FileReader(reqFile));
        String line1="";
        while ((line1 = breader.readLine()) != null) {
            String[] lines = line1.split(",");
            RequestVehicle requestVehicles = createRequestVehicle(lines);
            requestObjList.add(requestVehicles);
        }
        breader.close();

        for(RequestVehicle r : requestObjList){
            System.out.println("Requested VehicleType: " + r.vehicleType);
            System.out.println("Requested ZipCode: " + r.zipCode);
            System.out.println("Requested Vehicles: " + r.number);
            requestVehicle(r.vehicleType,r.zipCode, r.number,vehicleObjList);
        }



    }
    public static void requestVehicle(int requestType, int zipcode, int number,List<EmergencyVehicle> vehicleObj) throws IOException {
        String newContent="";
        for(EmergencyVehicle e : vehicleObj){
            if(e.vehicleType == requestType && e.zipCode==zipcode){
                if( e.availability==1){
                    System.out.println("The vehicle dispatched : "+e.vehicleID+"\n\n");
                    /*e.availability=0;*/

                    System.out.println(e);
                    break;

                }
            }
            newContent = e.vehicleID+","+e.zipCode+","+e.vehicleType+","+e.availability+"\n"+newContent;
            //dijkstra's algorithm implementation
            /*else{
                List<Path> list = new ArrayList<Path>();
                FileReader in = new FileReader("data/Distance.txt");
                BufferedReader br = new BufferedReader(in);
                FileWriter fw=null;
                BufferedWriter bw =null;
                fw = new FileWriter("data/Output.txt");
                bw = new BufferedWriter(fw);
                String line;
                int count=0,j=0;
                String[] values = {};
                ArrayList<String> a= new ArrayList<String>();
                while ((line = br.readLine()) != null) {
                    values = line.split(",");
                    for (String s : values)
                        a.add(s);
                    count++;
                }
                in.close();

                for(int i=0; i<count; i++){
                    list.add(new Path((a.get(j)), (a.get(j+1)), Integer.parseInt(a.get(j+2))));
                    j+=3;
                }

                Request object = new Request(list);
                ArrayList<String> uniqueNodes = new ArrayList<String>();
                for(int i=0;i<a.size();i++){
                    if(uniqueNodes.contains((a.get(i))) || ((i+1)%3)==0)
                        continue;

                    else
                        uniqueNodes.add(a.get(i));

                }

                for(int i=0; i<uniqueNodes.size();i++){
                    if( uniqueNodes.get(i)!= uniqueNodes.get(0)){

                        list = object.compute(uniqueNodes.get(0), uniqueNodes.get(i));
                        int sum =0;
                        for (Path path : list)
                        {
                            sum = sum+path.getWeight();
                            bw.write(path.getSource() + " -> " + path.getDestination()+" ");
                        }
                        bw.write(" distance : "+sum + "\r\n");

                        list = null;
                    }
                }
                bw.flush();
                bw.close();
            }*/

        }
        File myFoo = new File("data/EmergencyVehicle.txt");
        FileWriter fooWriter = new FileWriter(myFoo, false); // true to append
        // false to overwrite.
        fooWriter.write(newContent);
        fooWriter.close();

    }



    public Request(List<Path> graph)
    {
        this.graph = graph;
    }
    private static EmergencyVehicle createEmergencyVehicle(String[] attributes) {
        String vehicleID = attributes[0];
        int zipCode = Integer.parseInt(attributes[1]);
        int vehicleType = Integer.parseInt(attributes[2]);
        int availability = Integer.parseInt(attributes[3]);

        return new EmergencyVehicle(vehicleID,zipCode,vehicleType,availability);
    }

    private static RequestVehicle createRequestVehicle(String[] lines) {
        int vehicleType = Integer.parseInt(lines[0]);
        int zipCode = Integer.parseInt(lines[1]);
        int number  = Integer.parseInt(lines[2]);

        return new RequestVehicle(vehicleType, zipCode, number);
    }
    public static void startRequest(){

    }
    public static void completeRequest(){

    }


    public List<Path> compute(String start, String end)
    {
        List<Path> list = new ArrayList<Path>();
        list.addAll(graph);
        shortestPath = new ArrayList<Path>();
        distance = new HashMap<String, Pair>();
        distance.put(start, new Pair(0, "\0"));
        compute(start, end, list);
        generateShortestPath(start, end);

        return shortestPath;
    }

    private void compute(String source, String destination, List<Path> graph)
    {
        Path bestPath = null;

        for (Path path : graph)
        {
            if (path.getSource() == source)
            {
                int pathVal = path.getWeight() + ((Pair) distance.get(source)).getValue();

                if (!distance.containsKey(path.getDestination()))
                {
                    distance.put(path.getDestination(), new Pair(pathVal, path.getSource()));
                }
                else
                {
                    if (((Pair) distance.get(path.getDestination())).getValue() > pathVal)
                    {
                        distance.remove(path.getDestination());
                        distance.put(path.getDestination(), new Pair(pathVal, path.getSource()));
                    }
                }

                if (bestPath == null)
                {
                    bestPath = path;
                }
                else
                {
                    if (((Pair) distance.get(bestPath.getDestination())).getValue() > ((Pair) distance.get(path.getDestination())).getValue())
                    {
                        bestPath = path;
                    }
                }
            }
        }

        List<Path> remove = new ArrayList();

        for (Path path : graph)
        {
            if (path.getSource() == source || path.getDestination() == source)
            {
                remove.add(path);
            }
        }

        graph.removeAll(remove);

        if (bestPath != null)
        {
            if (bestPath.getDestination() != destination)
            {
                boolean check = false;

                for (Path path : graph)
                {
                    if (path.getSource() == bestPath.getDestination())
                    {
                        check = true;
                    }
                }

                if (check)
                {
                    compute(bestPath.getDestination(), destination, graph);
                }
                else if (!graph.isEmpty())
                {
                    compute(graph.get(0).getSource(), destination, graph);
                }
            }
        }
    }

    private void generateShortestPath(String start, String end)
    {
        Pair pair = (Pair) distance.get(end);

        shortestPath.add(new Path(pair.getSource(), end, pair.getValue()));

        while (pair.getSource() != start)
        {
            String c = pair.getSource();
            pair = (Pair) distance.get(pair.getSource());
            if (pair.getSource() != null)
            {
                pair = new Pair(0,start);
            }
            shortestPath.add(new Path(pair.getSource(), c, pair.getValue()));
        }

        Collections.reverse(shortestPath);
    }

}

