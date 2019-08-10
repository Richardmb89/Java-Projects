import java.util.ArrayList;
import java.util.Arrays;

public class practingList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] list = {1,4,3,6};
		int Max = list[0];
		int Min = list.length;
		int minIndex=0;
		int maxIndex=0;
		for(int i = 0; i < list.length;i++)
		{
			if(list[i]>Max) {
				Max = list[i];}
				maxIndex = i;
			if(list[i]<Min) {
				Min = list[i];
				minIndex = i;
				}
		}
		
		System.out.println(Max);
		System.out.println(Min);
		System.out.println(maxIndex);
		System.out.println(minIndex);
		//ArrayList<Integer> listss = new ArrayList<Integer>(Arrays.asList(list));
		//ArrayList<Interger>arr = new Array.list;
		//final int index = Ints.IndexOf(lists, Max);
		if(minIndex<maxIndex)
		{
			System.out.print(Max-Min);
		}
		else {
			System.out.print(0);}
		
		
			
	}

}
