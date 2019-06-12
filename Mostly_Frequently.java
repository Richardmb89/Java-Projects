import java.util.HashMap;
import java.util.Map;
//Assignment 1
//given an array of integer values and find the most 
//used in the array
public class Mostly_Frequently {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]array1 = {1,3,1,3,2,1};

		 // mostFrequent(array2) should return 3.
        int[] array2 = {3,3, 3, 3, 2, 1};
        // mostFrequent(array3) should return null.
        int[] array3 = {};
        // mostFrequent(array4) should return 0.
        int[] array4 = {0};
        // mostFrequent(array5) should return -1.
        int[] array5 = {0, -1, 10, 10, -1, 10, -1, -1, -1, 1};
		System.out.print(mostFreqent(array2));
	}
public static Integer mostFreqent(int[]givenArray)

{
	//Create
	HashMap<Integer, Integer>hm = new HashMap<Integer,Integer>();
	int value = 1;
	int counter=1;
	int length = givenArray.length;
	for(int i = 0;i<length;i++)
	//Creating a for loop to fun the list
	{
		
		if( hm.containsKey(givenArray[i]))//each value is checked 
			//in the list to see if it in the hashmap
			//if the value is in the hashmap as a key, the 
			//counter gets a + 1
		{
			hm.put(givenArray[i], counter++);
		}
		else//if the value is not in the hashmap it added and the value is 1
			hm.put(givenArray[i],value);
		
	}
	Map.Entry<Integer, Integer> commmonKey = hm.entrySet().iterator().next();
	int largestValue = commmonKey.getValue();
	int largestKey = commmonKey.getKey();
	for(Map.Entry<Integer, Integer>map : hm.entrySet())
	{
		int val = map.getValue();
		if(val>largestValue)
		{
			largestValue = val;
			largestKey = map.getKey();
		}
		
	}
	//System.out.print(hm);
	//System.out.println(commmonKey);
	//System.out.print(givenArray+" , /n");
	return largestKey;
}
}
