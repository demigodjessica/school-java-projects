/*
   minHeap.java 
    /\              o                /\         
   |  |  _   ,   ,      __   __,    |  |        
   |  | |/  / \_/ \_|  /    /  |    |  | |   |  
    \_|/|__/ \/  \/ |_/\___/\_/|_/   \_|/ \_/|_/
     /|                               /|        
     \|                               \|  
		     Jessica Ju, ID#46483660 UCnetID:juj1
*/

public class minHeap {

	Vertex[] myMinHeap;  
	int max; // Max size of heap = number of vertexes = number of locations. 
	int nextIndex; // Add to this spot. 
	int elements; // Number of elements in the heap so far. 

//	Constructor for my minimum heap. 
	public minHeap(int capacity)
	{ 
		max = capacity + 1; // I need to add 1 to max length since I start at index 1. 
		myMinHeap = new Vertex[max]; 
		nextIndex = 1; // Have it start at 1. 0 should be empty. 
		elements = 0; 
	}

//	Swap simple. 
	public void swapCells(int cell1, int cell2)
	{
		Vertex temp = myMinHeap[cell2]; 
		myMinHeap[cell2] = myMinHeap[cell1]; 
		myMinHeap[cell1] = temp; 

	}

//	Returns the root. 
	public Vertex getRoot()
	{ 
		return myMinHeap[1];
	}
	
// 	Think of the children! 
	public int myLeftChild(int currentPosition)
	{ 
		return 2*currentPosition; 
	}

	public int myRightChild(int currentPosition)
	{ 
		return 2*currentPosition + 1; 
	}
	
//  Parent index formula. 
	public int myParent(int currentPosition)
	{ 
		//	No need to floor. 
		return currentPosition / 2; 
	}

//	Insertion this is in log (n) 
	public void enqueue(Vertex startVertex, double d_v) 
	{ 
		startVertex.D_v = d_v; 
		myMinHeap[nextIndex] = startVertex; // Add to end. 
		elements++; 
		nextIndex++; 
		heapifyUp(elements);
	}

//	Deletion this is in log(n) the minimum is also returned as well as removed. 
	public Vertex dequeueMinimum()
	{ 		
		Vertex tempVertex = getRoot(); 
		swapCells(1, elements); 
		elements--; // Chop it off.
		nextIndex--; 
		heapifyDown(elements);	
		return tempVertex; 
	}

//  Does a right child exist given a total amount of elements in heap? 
	public boolean parentHasRightKid(int currentIndex, int elementsCount)
	{ 
//		If odd number there is right child. 
		if (currentIndex*2 + 1 <= elementsCount)
			return true; 
		else 
			return false; 
	}

//	Run until 1. both children are greater than parent or 2. there are no children.  3. or leftchild > parent
	public void heapifyDown(int currentPosition) 
	{
//		i is like the parent for every branches 
		int i = 1; 
		while (i < currentPosition &&  i*2 <= currentPosition)
		{
			int leftkid = myLeftChild(i);
			int rightkid;
			int sChild;

			if (parentHasRightKid(i, currentPosition) == true)
			{ 
				rightkid = myRightChild(i); 
//				if both children are greater than parent I am already done. 
				if ((myMinHeap[leftkid].D_v > myMinHeap[i].D_v && (myMinHeap[rightkid].D_v > myMinHeap[i].D_v)))
				{
					break; 
					
				}
				
//				Look for smaller child. 
				if (myMinHeap[leftkid].D_v > myMinHeap[rightkid].D_v)
				{
					sChild= rightkid; 
					swapCells(sChild, i); 
					i = i*2 + 1; 
				}
				else
				{ 
//					In the case of one node or the left kid is smaller pick the left kid 
					sChild = leftkid; 
					swapCells(sChild, i); 
					i = i*2; 
				}
			} 
			else 
			{ 				
				if (myMinHeap[leftkid].D_v > myMinHeap[i].D_v)
				{
					break; 
				}
				else 
					sChild = leftkid; 
					swapCells(sChild, i); 
					i = i*2; 
				}
			}	
		}
	
	

//	Used in Insertion. 
	public void heapifyUp(int currentPosition)
	{       
		int temp = currentPosition; 
		int theParent; 

		while (temp > 1)
		{
			theParent = myParent(temp); 
			if (myMinHeap[temp].D_v < myMinHeap[theParent].D_v)
				swapCells(temp, theParent);
			temp = temp / 2; 
		}
	}
	
//	Returns number elements in heap so far. 
	public int ssize()
	{ 
		return elements; 
	}
}

