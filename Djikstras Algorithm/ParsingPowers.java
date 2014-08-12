import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
	ParsingPowers.java 
 	/\              o                /\         
   |  |  _   ,   ,      __   __,    |  |        
   |  | |/  / \_/ \_|  /    /  |    |  | |   |  
 	\_|/|__/ \/  \/ |_/\___/\_/|_/   \_|/ \_/|_/
  	 /|                               /|        
  	 \|                               \|  
			Jessica Ju, ID#46483660 UCnetID:juj1
 */

public class ParsingPowers {

	//	My Global variables. 
	AdjacencyList AL; 
	ArrayList<TripObj> TripList; 
	int infinity = Integer.MAX_VALUE; 
	ArrayList<String> shortestPath; 

	//  Constructor. 
	public ParsingPowers(String myFile)
	{
		try {
			File file = new File(myFile);
			Scanner scanner = new Scanner(file);
			// This will contain the input file with the comments taken out. 
			ArrayList<String> CleanFile = new ArrayList<String>(); 

			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine(); 
				if ((!(line.length() == 0)) && (!line.startsWith("# ")))
				{
					CleanFile.add(line); 
				}
			}

			//	Saves the list of locations 
			int numberOfLocations = Integer.parseInt(CleanFile.get(0));  
			AL = new AdjacencyList(numberOfLocations); 

			for (int l = 0; l < numberOfLocations; l++)
			{ 
				Vertex tempNewVertex = new Vertex(CleanFile.get(l + 1), l);
				AL.addVertex(tempNewVertex); 
			}	

			//	Create edge objects and add them to ArrayList
			int startVertexT; 
			int endVertexT;
			double distanceT; 
			double timeT; 
			double speedT; 
			int numberOfEdges = Integer.parseInt(CleanFile.get(1 + numberOfLocations)); 
			for (int e = 0; e < numberOfEdges; e++)
			{
				Scanner sl = new Scanner(CleanFile.get(numberOfLocations + e + 2)); // 2 accounts for 2 lines 
				startVertexT = sl.nextInt(); 
				endVertexT = sl.nextInt(); 
				distanceT = sl.nextDouble(); 
				speedT = sl.nextDouble();
				timeT = distanceT / speedT; 
				Edge thisEdge = new Edge(startVertexT, endVertexT, distanceT, speedT, timeT); 
				AL.addProper(startVertexT, thisEdge); 
			}

			//	Creates tripObj and adds them to an ArrayList.
			TripList = new ArrayList<TripObj>(); 
			int numberOfTrips = Integer.parseInt(CleanFile.get(2 + numberOfLocations + numberOfEdges)); 
			int startingLocationT; 
			int endingLocationT; 
			boolean isDistance = false; 
			for (int t = 0; t < numberOfTrips; t++) 
			{ 
				Scanner ts = new Scanner(CleanFile.get(t + numberOfLocations + numberOfEdges + 3)); 
				startingLocationT = ts.nextInt(); 
				endingLocationT = ts.nextInt();
				String letterDORT; 
				letterDORT = ts.next(); 
				if (letterDORT.equals("D"))
					isDistance = true;
				else 
					isDistance = false; 

				TripObj tripStorage = new TripObj(startingLocationT, endingLocationT, isDistance); 
				TripList.add(tripStorage);
			}

			//	MAJOR PART OF CODE! 
			//	Where I actually run Dijkstra's Algorithm! ti stands for trip index. 
			//
			for (int ti = 0; ti < TripList.size(); ti++)
			{ 
				int startLocation = TripList.get(ti).tripGetStart(); 
				int endLocation = TripList.get(ti).tripGetEnd(); 
				boolean goByDistance = TripList.get(ti).shortestDistance(); 
				DijkstraAlgorithm(goByDistance, startLocation, endLocation);
				debugShortestPath(startLocation, endLocation, TripList.get(ti).shortestDistance());
			}	
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found exception has been thrown. ");
			e.printStackTrace();
		}
	}

	//				
	//	 		************************Dijkstra's Algorithm************************
	//	NOTES: Should get shortest part from start vertex to ALL other vertexes 
	//	Contains kv, dv, pv. Manipulates the global variable AL (AdjacencyList) in this file. 
	//	This is the conversion of the pseudocode from: http://www.ics.uci.edu/~jacobson/ics23/LabManual/03-RockAndRoll.html
	//			
	private void DijkstraAlgorithm(boolean findCostInDistance, int startIndex, int endIndex) 
	{ 
		int locations = AL.getVertexCount(); 
		for (int v = 0; v < locations; v++)
		{ 
			if (v == startIndex)
			{
				// -2 means there never will be a predecessor while -1 just means it's unknown. 
				AL.setKPV(v, false, -2, 0);
			}
			else
			{
				//	Set K_v to false, P_v to unknown (-1), D_v to infinity
				AL.setKPV(v, false, -1, infinity);
			}
		}
		//	Initializes a minimum heap of size (number of locations). 
		minHeap pq = new minHeap(locations); 
		pq.enqueue(AL.getVertex(startIndex), 0);
		while(pq.ssize() > 0) 
		{
			Vertex v = pq.dequeueMinimum(); 
			if (v.K_v == false)
			{ 
				//	Visited before. 
				v.K_v = true; 
				ListOfConnections tempListOfAV; 
				tempListOfAV = AL.getAdjacentVertexes(v.index); 

				//	Look through adjacent vertexes 
				for (int i= 0; i < tempListOfAV.numberOfAV(); i++) 
				{ 
					int d_w_index = tempListOfAV.oneAdjacentVertex(i).getEndVertexNode(); 

					//	Shortest path from source to this vertex. They are at first all infinity (abs. largest). 
					double d_w = AL.getVertex(d_w_index).D_v; 

					if (d_w > v.D_v + AL.Cost(findCostInDistance, v.index, d_w_index))
					{ 
						//	Smaller path found. 
						d_w = v.D_v + AL.Cost(findCostInDistance, v.index, d_w_index); 

						AL.getVertex(d_w_index).P_v = v.index; 
						pq.enqueue(AL.getVertex(d_w_index), d_w);
					}
				}	
			}
		}
	}

	//	Debugs the shortest path! 
	private void debugShortestPath(int startLoc, int endLoc, boolean findShortestDistance) 
	{ 
		shortestPath = new ArrayList<String>();
		double totalQuantity = 0; 
		//	Debug differently for search type. 
		if (findShortestDistance == true)
		{
			System.out.println("Shortest distance from " + AL.getVertex(startLoc).name 
					+ " to " + AL.getVertex(endLoc).name);
			System.out.println(" Begin at " + AL.getVertex(startLoc).name ); 
			totalQuantity = AL.getVertex(endLoc).D_v; 

			int temp = AL.getVertex(endLoc).P_v; 
			shortestPath.add(" Continue to " + AL.getVertex(endLoc).name + " ("
					+ AL.Cost(findShortestDistance, AL.getVertex(endLoc).P_v, AL.getVertex(endLoc).index) + " miles)"); 
			while (temp != startLoc)
			{ 
				shortestPath.add(" Continue to " + AL.getVertex(temp).name + " ("
						+ AL.Cost(findShortestDistance, AL.getVertex(temp).P_v, AL.getVertex(temp).index) + " miles)"); 

				temp = AL.getVertex(temp).P_v; 
			}
			//Reverse the shortest path. 
			for (int i= shortestPath.size() - 1; i > -1; i--)
			{
				System.out.println(shortestPath.get(i));
			}
			//Write out total. 
			System.out.println("Total distance: " + totalQuantity + " miles");
			System.out.println(" \n");
		}
//		This is for when it's asking for shortest time. 
		else 
		{
			System.out.println("Shortest time from " + AL.getVertex(startLoc).name 
					+ " to " + AL.getVertex(endLoc).name);
			System.out.println(" Begin at " + AL.getVertex(startLoc).name ); 
			totalQuantity = AL.getVertex(endLoc).D_v; 

			int temp = AL.getVertex(endLoc).P_v; 
			shortestPath.add(" Continue to " + AL.getVertex(endLoc).name + " ("
					+ AL.correctDistance(AL.getVertex(endLoc).P_v, AL.getVertex(endLoc).index) + " miles @ " 
					+ AL.correctSpeed(AL.getVertex(endLoc).P_v, AL.getVertex(endLoc).index) 
					+ " mph = " + calculateHMS(AL.Cost(findShortestDistance, AL.getVertex(endLoc).P_v, AL.getVertex(endLoc).index)) + ")");
			
			while (temp != startLoc)
			{ 
				shortestPath.add(" Continue to " + AL.getVertex(temp).name + " ("
						+ AL.correctDistance(AL.getVertex(temp).P_v, AL.getVertex(temp).index) + " @ "
						+ AL.correctSpeed(AL.getVertex(temp).P_v, AL.getVertex(temp).index)
						+  " mph = " + calculateHMS(AL.Cost(findShortestDistance, AL.getVertex(temp).P_v, AL.getVertex(temp).index)) + ")"); 

				temp = AL.getVertex(temp).P_v; 
			}
			//		Reverse the shortest path. 
			for (int i= shortestPath.size() - 1; i > -1; i--)
			{
				System.out.println(shortestPath.get(i));
			}
			//		Write out total. 
			System.out.println("Total time: " + calculateHMS(totalQuantity));
			System.out.println(" \n");
		}
	}

//	Returns string in hours, minutes, and seconds 
	public static String calculateHMS(double hoursDecimal) 
	{ 
		int hours = (int)hoursDecimal; 
		int minutes = (int)((hoursDecimal - hours)*60); 

		//		Seconds with 1 decimal place. 
		double seconds1 = (((hoursDecimal - hours) * 60) - minutes) * 600;
		double seconds2 = (Math.round(seconds1)); 
		double seconds =  seconds2 / 10; 

		//		Three different printing cases! 
		if (hours >= 1)
			return hours + " hours " + minutes + " mins " + seconds + " secs";
		else if (minutes >= 1)
			return minutes + " mins " + seconds + " secs";
		else 
			return seconds + " secs";
	}

}