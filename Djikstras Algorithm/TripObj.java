/*
   TripObj.java 
    /\              o                /\         
   |  |  _   ,   ,      __   __,    |  |        
   |  | |/  / \_/ \_|  /    /  |    |  | |   |  
    \_|/|__/ \/  \/ |_/\___/\_/|_/   \_|/ \_/|_/
     /|                               /|        
     \|                               \|  
		    Jessica Ju, ID#46483660 UCnetID:juj1
*/

public class TripObj {
	
	int startingLocation; 
	int endingLocation; 
	boolean findShortestDistance; 

//	A trip object contains a starting vertex, ending vertex, and a boolean of whether to calculate
//	by shortest distance or shortest time. 
	public TripObj(int startingLocation, int endingLocation, boolean goByDistance)
	{ 
		this.startingLocation = startingLocation; 
		this.endingLocation = endingLocation; 
		findShortestDistance = goByDistance; // True of false. 
	}
	
//	Return starting location
	public int tripGetStart()
	{ 
		return startingLocation; 
	}
	
//	Return ending location
	public int tripGetEnd() 
	{ 
		return endingLocation; 
	}
	
//	Return calculate by shortest Distance or Time. 
	public boolean shortestDistance()
	{ 
		return findShortestDistance; 
	}

	
}
