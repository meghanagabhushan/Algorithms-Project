package djkshtatra;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Manisha
 */
public class Path
{
    private String source;
    private String destination;
    private int weight;
    
    public Path(String source, String destination, int weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    public String getSource()
    {
        return source;
    }
    
    public String getDestination()
    {
        return destination;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public void setSource(String source)
    {
        this.source = source;
    }
    
    public void setDestination(String destination)
    {
        this.destination = destination;
    }
    
    public void setWeight(int weight)
    {
        this.weight = weight;
    }
}
