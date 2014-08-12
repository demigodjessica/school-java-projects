
/*
   Edge.java 
    /\              o                /\         
   |  |  _   ,   ,      __   __,    |  |        
   |  | |/  / \_/ \_|  /    /  |    |  | |   |  
    \_|/|__/ \/  \/ |_/\___/\_/|_/   \_|/ \_/|_/
     /|                               /|        
     \|                               \|  
		     Jessica Ju, ID#46483660 UCnetID:juj1
*/

public class Edge {
	
	int startVertex; 
	int endVertex; 
	double distanceCost; 
	double timeCost; 
	double speed; 
	
//	An Edge object includes a start vertex, end vertex, a distance cost, and a time cost. 
	public Edge(int startVertex, int endVertex, double distance, double speed, double time)
	{
		distanceCost = distance; 
		timeCost = time; 
		this.speed = speed; 
		this.startVertex = startVertex; 
		this.endVertex = endVertex; 
	}
	
//	Returns end vertex. 
	public int getEndVertexNode() 
	{ 
		return endVertex; 
	}
	
//	Distance and time cost are variables of type double. 
	public double startToEndCost(boolean costInDistance)
	{ 
		if (costInDistance == true)
		{
			return distanceCost; 
		}
		else
		{
			return timeCost; 
		}
	}
	
}
