import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Djkshtatra
{
    private final List<Edge> graph;
    private List<Edge> shortestPath;
    private Map distance;
    private String source_main;
    private String dest_main;
    
    public Djkshtatra(List<Edge> graph)
    {
        this.graph = graph;
    }
    
    public List<Edge> compute(String start, String end)
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
        if(shortestPath.isEmpty()){
             return null;
        }
           
        else
            return shortestPath;
    }
    
    private void compute(String source, String destination, List<Edge> graph)
    {
        Edge bestPath = null;
        
        for (Edge Edge : graph)
        {
            if((Edge.getSource()).equals(source_main) && (Edge.getDestination()).equals(dest_main)){
                bestPath = Edge;
                distance.put(Edge.getDestination(), new Pair(Edge.getWeight(), Edge.getSource()));
                break;
            }
            else if ((Edge.getSource()).equals(source))
            {
                int pathVal = Edge.getWeight() ;
                
                if (!distance.containsKey(Edge.getDestination()))
                {
                    distance.put(Edge.getDestination(), new Pair(pathVal, Edge.getSource()));
                }
                else  if (((Pair) distance.get(Edge.getDestination())).getValue() > pathVal)
                {
                        distance.remove(Edge.getDestination());
                        distance.put(Edge.getDestination(), new Pair(pathVal, Edge.getSource()));
                }
                
                if (bestPath == null)
                {
                    bestPath = Edge;
                }
                else
                {
                    if (((Pair) distance.get(bestPath.getDestination())).getValue() > ((Pair) distance.get(Edge.getDestination())).getValue())
                    {
                        bestPath = Edge;
                    }
                }
            }
        }
        
        List<Edge> remove = new ArrayList();
        
        for (Edge Edge : graph)
        {
            if ((Edge.getSource()).equals(source) || (Edge.getDestination()).equals(source))
            {
                remove.add(Edge);
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
                
                for (Edge Edge : graph)
                {
                    if ((Edge.getSource()).equals(bestPath.getDestination()))
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
        if(pair == null){
        
        }
        else{
            shortestPath.add(new Edge(pair.getSource(), end, pair.getValue())); 
            while (!((pair.getSource()).equals(start)))
            {
                String c = pair.getSource();
                pair = (Pair) distance.get(pair.getSource());
                    if(pair == null)
                        break;
                    else
                        shortestPath.add(new Edge(pair.getSource(), c, pair.getValue()));
            }       
            Collections.reverse(shortestPath);
        }
    }
}