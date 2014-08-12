import java.util.ArrayList;

/*
  AdjacencyList.java 
   /\              o                /\         
  |  |  _   ,   ,      __   __,    |  |        
  |  | |/  / \_/ \_|  /    /  |    |  | |   |  
   \_|/|__/ \/  \/ |_/\___/\_/|_/   \_|/ \_/|_/
    /|                               /|        
    \|                               \|  
					Jessica Ju, ID#46483660 UCnetID:juj1
*/

public class AdjacencyList {
	
//	An Adjacency list includes an ArrayList of ArrayList of Edges 
//	and an array list of single Vertexes. 
	
	ArrayList<ListOfConnections> Edges; 
	ArrayList<Vertex> Vertexes;
	int numberOfVertex; 
 
//	AdjacencyList constructor 
//	Intializes Edges (since it is an arraylist of arraylist). 
	public AdjacencyList(int Locations) 
	{ 
		Edges = new ArrayList<ListOfConnections>(Locations); 
		for (int i=0; i<Locations; i++)
		{ 
			Edges.add(new ListOfConnections()); 
		}
		
		Vertexes = new ArrayList<Vertex>(Locations); 
		numberOfVertex = Locations; 
	}
	
//	Returns the number of vertexes. (size of Adjacency list). 
	public int getVertexCount()
	{ 
		return numberOfVertex; 
	}
	
//	Set the KPV of a particular Vertex node. 
	public void setKPV(int vertexIndex, boolean k, int p, double d)
	{ 
		Vertexes.get(vertexIndex).setKPV(k, p, d); 
	}
	
//	Adds a new Edge a certain vertex connects to. 
	public void addProper(int vertexIndex, Edge newConnection)
	{ 
		Edges.get(vertexIndex).addAConnection(newConnection);
	}
	
//	Add a vertex to Vertexes (an ArrayList). 
	public void addVertex(Vertex newVertex)
	{ 
		Vertexes.add(newVertex);
	}
	
//	Access a vertex given an index. 
	public Vertex getVertex(int indexNumber)
	{ 
		return Vertexes.get(indexNumber); 
	}
	
//	Returns Vertexes
	public ArrayList<Vertex> getVertexes()
	{ 
		return Vertexes; 
	}
	
//	Access a list of adjacent vertexes given an index. 
	public ListOfConnections getAdjacentVertexes(int indexNumber)
	{ 
		return Edges.get(indexNumber);
	}
	
//	This assumes end vertex is adjacent to start vertex. 
	public double Cost(boolean findDistanceCost, int startVertexIndex, int endVertexAL)
	{ 
		double temp = Edges.get(startVertexIndex).getCost(findDistanceCost, endVertexAL); 
		return temp; 
	}
	
//	Returns speed of an Edge 
	public double correctSpeed(int startVertexIndex, int endVertexAL)
	{ 
		double temp = Edges.get(startVertexIndex).getSpeed(endVertexAL); 
		return temp; 
	}
	
//	Returns distance of an Edge 
	public double correctDistance(int startVertexIndex, int endVertexAL)
	{ 
		double temp = Edges.get(startVertexIndex).getDistance(endVertexAL); 
		return temp; 
	}
	
}
