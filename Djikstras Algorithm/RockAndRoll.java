
/*
   RockAndRoll.java
    /\              o                /\         
   |  |  _   ,   ,      __   __,    |  |        
   |  | |/  / \_/ \_|  /    /  |    |  | |   |  
    \_|/|__/ \/  \/ |_/\___/\_/|_/   \_|/ \_/|_/
     /|                               /|        
     \|                               \|  
		    Jessica Ju, ID#46483660 UCnetID:juj1
*/

//  This contains the main method, not much else. 
public class RockAndRoll {
	
	String FileName = "RockAndRoll.java"; 
	public static void main(String[] args) 
	{
		try
		{ 
			ParsingPowers pp = new ParsingPowers("test.6");
		} 
		catch (ArrayIndexOutOfBoundsException ae)
		{ 
			System.out.println(" NO PATH FOUND! (*test6 doesn't work). ");
		}
	}
	
}
