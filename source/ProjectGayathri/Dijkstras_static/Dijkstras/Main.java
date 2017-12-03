package djkshtatra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        List<Path> list = new ArrayList();
        List<Path> list1 = new ArrayList();
        FileReader in = new FileReader("C:/Users/Manisha/Documents/NetBeansProjects/djkshtatra/src/djkshtatra/data.txt");
    	BufferedReader br = new BufferedReader(in);
        FileWriter fw;
        BufferedWriter bw;
        fw = new FileWriter("Output.txt");
        bw = new BufferedWriter(fw);
    	String line;
        int count=0,j=0;
    	String[] values;
    	ArrayList<String> a= new ArrayList<String>(); 
	while ((line = br.readLine()) != null) {
            values = line.split(",");  
            for (String s : values)
    		a.add(s);	
            count++;
	}				
    	in.close();

        for(int i=0; i<count; i++){
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
                System.out.println( input_var +"-"+uniqueNodes.get(i));
                object = new Djkshtatra(list);
//                if(object.compute( input_var , uniqueNodes.get(i))== null){
//                    bw.write(input_var+" -> "+ uniqueNodes.get(i)+" : path does not exists");
//                    bw.newLine();
//                }
//                else{
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
//                    }
//                    else{
//                        bw.write(input_var+" -> "+ uniqueNodes.get(i)+" : path does not exists");
//                        bw.newLine();
//                    }                    
                //}                               
                list1 = null;
                object = null;
                
            }
        }     
        bw.flush();
        bw.close();        
    }
}
