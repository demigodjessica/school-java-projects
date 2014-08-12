
public class minHeapTest {
	
	public static String calculateHMS(double hoursDecimal) 
	{ 
		int hours = (int)hoursDecimal; 
		int minutes = (int)((hoursDecimal - hours)*60); 
		
//		Seconds with 1 decimal place. 
		double seconds1 = (((hoursDecimal - hours) * 60) - minutes) * 600;
		double seconds2 = (Math.round(seconds1)); 
		double seconds =  seconds2 / 10; 
		
		if (hours > 1)
		{
			return hours + " hours " + minutes + " mins " + seconds + " secs ";
		}
		else if (minutes > 1)
		{ 
			return minutes + " mins " + seconds + " secs ";
		}
		else 
		{ 
			return seconds + " secs ";
		}
	}
	
	public static void main(String[] args) 
	{
//		minHeap mht = new minHeap(6); 
//		
//		Vertex v1 = new Vertex("a", 0); 
//		Vertex v2 = new Vertex("b", 1); 
//		Vertex v3 = new Vertex("c", 2); 
//		Vertex v4 = new Vertex("d", 3);
//		
//		mht.enqueue(v1, 1); 
//		mht.enqueue(v2, 2); 
//		mht.enqueue(v3, 3);
//		mht.enqueue(v4, 4); 
//		
//		Vertex dv;
//		
//		dv = mht.dequeueMinimum(); 
////		System.out.println(" dequed " + dv);
//		
//		dv = mht.dequeueMinimum(); 
////		System.out.println(" dequed " + dv);
//		
//		dv = mht.dequeueMinimum(); 
//		System.out.println(" dequed " + dv);
//		
//	//	dv = mht.dequeueMinimum(); 
//		
//
//		
//		for (int i = 1; i < mht.ssize() + 1; i++)
//		{ 
//			double temp = mht.rElem(i);
//			System.out.println(temp);
//		}
		
		System.out.println(calculateHMS(0.00902777777777777777));
		
		System.out.println(calculateHMS(0.04105555555555555555));
		
		System.out.println(calculateHMS(0.21666666666666666666));
		
		System.out.println(calculateHMS(3.22008333333333333333));
		
		System.out.println(calculateHMS(6));

		
		
	}
}
