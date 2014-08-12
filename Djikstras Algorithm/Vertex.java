/*
  Vertex.java 
   /\              o                /\         
  |  |  _   ,   ,      __   __,    |  |        
  |  | |/  / \_/ \_|  /    /  |    |  | |   |  
   \_|/|__/ \/  \/ |_/\___/\_/|_/   \_|/ \_/|_/
    /|                               /|        
    \|                               \|  
					Jessica Ju, ID#46483660 UCnetID:juj1
*/

public class Vertex {
	
	boolean K_v; 
	int P_v; 
	double D_v; 
	String name;
	int index; 
	
	String FileName = "Vertex.java"; 
	
//	A vertex object composes of a String name, an index number, K_v (visited or not), 
//  P_v (previous vertex), and D_v(shortest path cost from start(given in trip file) to this vertex. 
	public Vertex(String name, int index) 
	{ 
		this.name = name; 
		this.index = index; 
	}
	
//	set k p v values
	public void setKPV(boolean k, int p, double d)
	{ 
		K_v = k; 
		P_v = p; 
		D_v = d; 
	}
}
