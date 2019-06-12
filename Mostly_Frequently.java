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
	//Create Hash Map that I named hm
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
	//The Map commmonKey only takes the first value
	Map.Entry<Integer, Integer> commmonKey = hm.entrySet().iterator().next();
	//Use commmonKey as a template or bar to compare other values in the hashmap
	//Use commmonKey to create a had a large key and value to test
	int largestValue = commmonKey.getValue();
	int largestKey = commmonKey.getKey();
	//Create another Map that will hold the key and value from the first hashmap
	for(Map.Entry<Integer, Integer>map : hm.entrySet())
	{
		int val = map.getValue();
		//pull the value out of the map and then compare it to commmonKey value
		//if val is largest then largest value. val becomes the new largestValue
		if(val>largestValue)
		{
			largestValue = val;
			//once you have the largest value cope the key to largestKey
			largestKey = map.getKey();
		}
		
	}
	//Send back the largest key to show that this key repeats the most in the array
	return largestKey;
}
}
