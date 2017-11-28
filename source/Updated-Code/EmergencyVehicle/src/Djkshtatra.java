import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Djkshtatra
{
    private final List<Edge> graph;
    private List<Edge> shortestEdge;
    private Map distance;
    
    public Djkshtatra(List<Edge> graph)
    {
        this.graph = graph;
    }
    
    public List<Edge> compute(String start, String end)
    {        
        List list = new ArrayList();
        list.addAll(graph);
        shortestEdge = new ArrayList();
        distance = new HashMap();
        distance.put(start, new Pair(0, ""));
        compute(start, end, list);
        generateShortestEdge(start, end);        
        return shortestEdge;
    }
    
    private void compute(String source, String destination, List<Edge> graph)
    {
        Edge bestEdge = null;
        
        for (Edge Edge : graph)
        {
            if ((Edge.getSource()).equals(source))
            {
                int EdgeVal = Edge.getWeight() ;
                
                if (!distance.containsKey(Edge.getDestination()))
                {
                    distance.put(Edge.getDestination(), new Pair(EdgeVal, Edge.getSource()));
                }
                else
                {
                    if (((Pair) distance.get(Edge.getDestination())).getValue() > EdgeVal)
                    {
                        distance.remove(Edge.getDestination());
                        distance.put(Edge.getDestination(), new Pair(EdgeVal, Edge.getSource()));
                    }
                }
                
                if (bestEdge == null)
                {
                    bestEdge = Edge;
                }
                else
                {
                    if (((Pair) distance.get(bestEdge.getDestination())).getValue() > ((Pair) distance.get(Edge.getDestination())).getValue())
                    {
                        bestEdge = Edge;
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
        
        if (bestEdge != null)
        {
            if((bestEdge.getDestination()).equals(destination))
                ;
            else
            {
                boolean check = false;
                
                for (Edge Edge : graph)
                {
                    if ((Edge.getSource()).equals(bestEdge.getDestination()))
                    {
                        check = true;
                    }
                }
                
                if (check)
                {
                    compute(bestEdge.getDestination(), destination, graph);
                }
                else if (!graph.isEmpty())
                {
                    compute(graph.get(0).getSource(), destination, graph);
                }
            }
        }
    }
    
    private void generateShortestEdge(String start, String end)
    {
        Pair pair = (Pair) distance.get(end);    
        shortestEdge.add(new Edge(pair.getSource(), end, pair.getValue()));       
        while (!((pair.getSource()).equals(start)))
        {
            String c = pair.getSource();
            pair = (Pair) distance.get(pair.getSource());
            shortestEdge.add(new Edge(pair.getSource(), c, pair.getValue()));
        }       
        Collections.reverse(shortestEdge);
    }
}