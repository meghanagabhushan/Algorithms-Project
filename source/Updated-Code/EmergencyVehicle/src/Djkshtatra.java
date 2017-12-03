
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Djkshtatra
{
    private final List<Path> graph;
    private List<Path> shortestPath;
    private Map distance;
    private String source_main;
    private String dest_main;
    
    public Djkshtatra(List<Path> graph)
    {
        this.graph = graph;
    }
    
    public List<Path> compute(String start, String end)
    {        
        source_main = start;
        dest_main = end;
        List list = new ArrayList();
        list.addAll(graph);
        shortestPath = new ArrayList();
        distance = new HashMap();
        distance.put(start, new Pair(0, ""));
        compute(start, end, list);
        generateShortestPath(start, end);   
//        if(shortestPath.isEmpty()){
//             return null;
//        }
//           
//        else
//            return shortestPath;
        return shortestPath;
    }
    
    private void compute(String source, String destination, List<Path> graph)
    {
        Path bestPath = null;
        
        for (Path path : graph)
        {
            if((path.getSource()).equals(source_main) && (path.getDestination()).equals(dest_main)){
                bestPath = path;
                distance.put(path.getDestination(), new Pair(path.getWeight(), path.getSource()));
                break;
            }
            else if ((path.getSource()).equals(source))
            {
                int pathVal = path.getWeight() + ((Pair) distance.get(source)).getValue();
                
                if (!distance.containsKey(path.getDestination()))
                {
                    distance.put(path.getDestination(), new Pair(pathVal, path.getSource()));
                }
                else  if (((Pair) distance.get(path.getDestination())).getValue() > pathVal)
                {
                        distance.remove(path.getDestination());
                        distance.put(path.getDestination(), new Pair(pathVal, path.getSource()));
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
            if ((path.getSource()).equals(source) || (path.getDestination()).equals(source))
            {
                remove.add(path);
            }
        }
        
        graph.removeAll(remove);
        
        if (bestPath != null)
        {
            if((bestPath.getDestination()).equals(destination))
                ;
            else
            {
                boolean check = false;
                
                for (Path path : graph)
                {
                    if ((path.getSource()).equals(bestPath.getDestination()))
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
        
        while (!((pair.getSource()).equals(start)))
        {
            String c = pair.getSource();
            pair = (Pair) distance.get(pair.getSource());
            shortestPath.add(new Path(pair.getSource(), c, pair.getValue()));
        }
        
        Collections.reverse(shortestPath);
    }
    
    
//    private void generateShortestPath(String start, String end)
//    {
//        Pair pair = (Pair) distance.get(end);   
//        if(pair == null){
//        
//        }
//        else{
//            shortestPath.add(new Path(pair.getSource(), end, pair.getValue())); 
//            while (!((pair.getSource()).equals(start)))
//            {
//                String c = pair.getSource();
//                pair = (Pair) distance.get(pair.getSource());
//                    if(pair == null)
//                        break;
//                    else
//                        shortestPath.add(new Path(pair.getSource(), c, pair.getValue()));
//            }       
//            Collections.reverse(shortestPath);
//        }
//    }
}