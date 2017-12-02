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
public class Pair
{
    private int value;
    private String source;
    
    public Pair(int value, String source)
    {
        this.value = value;
        this.source = source;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public String getSource()
    {
        return source;
    }
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public void setSource(String source)
    {
        this.source = source;
    }
}
