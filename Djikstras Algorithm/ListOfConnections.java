import java.util.ArrayList;

/*
  ListOfConnections.java
   /\              o                /\         
  |  |  _   ,   ,      __   __,    |  |        
  |  | |/  / \_/ \_|  /    /  |    |  | |   |  
   \_|/|__/ \/  \/ |_/\___/\_/|_/   \_|/ \_/|_/
    /|                               /|        
    \|                               \|  
				Jessica Ju, ID#46483660 UCnetID:juj1
*/


// A List of connections is an arraylist of type Edge. 
public class ListOfConnections 
{
	ArrayList<Edge> ListOfConnections; 
	
//	Constructor
	public ListOfConnections()
	{ 
		ListOfConnections  = new ArrayList<Edge>(); 
	}
	
//	Add a new connection to a list of connections! 
//	For example for test.0 AdjacencyList[0] would contain a ListOfConnections 
//	Which would contain 1, 3, 9, 12 
//	Inside 1, 3, 9, 12 would be Edge characteristics. 
	public void addAConnection(Edge newConnection)
	{ 
		ListOfConnections.add(newConnection); 
	}
	
//	The number of adjacent vertexes for a particular vertex. 
	public int numberOfAV() 
	{ 
		return ListOfConnections.size(); 
	}
	
//	Returns a vertex from the List of connections. 
	public Edge oneAdjacentVertex(int index) 
	{ 
		return ListOfConnections.get(index);
	}
	
//	Should return the cost (double) from the node that contains this list of connections 
//  to the end vertex. 
	public double getCost(boolean costInDistance, int endVertex) 
	{ 
//		This returns the cost to get to a particular end vertex. 
		for (int i = 0; i < ListOfConnections.size(); i++)
		{ 
			if (ListOfConnections.get(i).getEndVertexNode() == endVertex)
			{
//				return breaks out of loop. 
				return ListOfConnections.get(i).startToEndCost(costInDistance);
			}
		}
//		Something went wrong. The endVertex(int) provided is not adjacent to the startVertex. 
		System.out.println("MAJOR ERROR END VERTEX NOT FOUND");
		return Integer.MAX_VALUE; 
	}
	
//	Returns an edge speed. 
	public double getSpeed(int endVertex) 
	{ 
		for (int i = 0; i < ListOfConnections.size(); i++)
		{ 
			if (ListOfConnections.get(i).getEndVertexNode() == endVertex)
			{
				return ListOfConnections.get(i).speed;
			}
		}
//		Something went wrong. The endVertex(int) provided is not adjacent to the startVertex. 
		System.out.println("MAJOR ERROR END VERTEX NOT FOUND");
		return Integer.MAX_VALUE; 
	}
	
//	Returns an edge distance 
	public double getDistance(int endVertex) 
	{ 
		for (int i = 0; i < ListOfConnections.size(); i++)
		{ 
			if (ListOfConnections.get(i).getEndVertexNode() == endVertex)
			{
				return ListOfConnections.get(i).distanceCost;
			}
		}
//		Something went wrong. The endVertex(int) provided is not adjacent to the startVertex. 
		System.out.println("MAJOR ERROR END VERTEX NOT FOUND");
		return Integer.MAX_VALUE; 
	}
	
}






