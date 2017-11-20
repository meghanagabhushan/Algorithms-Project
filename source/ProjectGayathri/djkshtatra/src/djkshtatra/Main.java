import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        List<Path> list = new ArrayList();
        FileReader in = new FileReader("data.txt");
    	BufferedReader br = new BufferedReader(in);
        FileWriter fw=null;
        BufferedWriter bw =null;
        fw = new FileWriter("Output.txt");
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
            list.add(new Path((a.get(j)).charAt(0), (a.get(j+1)).charAt(0), Integer.parseInt(a.get(j+2))));
            j+=3;
        }
        
        Djkshtatra object = new Djkshtatra(list);
        ArrayList<Character> uniqueNodes = new ArrayList<Character>();
        for(int i=0;i<a.size();i++){
            if(uniqueNodes.contains((a.get(i)).charAt(0)) || ((i+1)%3)==0)
                continue;
            
            else
                uniqueNodes.add((a.get(i)).charAt(0));
            
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
                 bw.write(" distance : "+sum + "\n");
                 
                 list = null;
            }
        }     
        bw.flush();
        bw.close();        
    }
}
