import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

// Main function to maintain the flow
public class Driver {
	static String dataKey = Data.datas.keySet().iterator().next();
	
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		DataSet dataSet = new DataSet(Data.datas.get(dataKey));
		System.out.println("["+dataKey+" DATASET]\n"+dataSet);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		while(flag)
		{
			System.out.println("> what do you want to do (calc probs, change dataset, exits) ?");
			String command = bufferedReader.readLine();
			switch(command){
			case "calc probs":
				//Reading the inputs to calculate probability
				System.out.println("> Please enter values for: ");
				for(int i = 0; i<dataSet.getData()[0].length-2;i++) System.out.println(dataSet.getData()[0][i]+",");
				System.out.println(dataSet.getData()[0][dataSet.getData()[0].length-2]+" (seperated by commas)");
				String[]values =  (bufferedReader.readLine()).split(",");
				HashMap<String, String>instMap = new HashMap<String, String>();
				for(int i = 0; i<dataSet.getData()[0].length-1; i++) instMap.put(dataSet.getData()[0][i], values[i].trim());


				//Calculate conditional probability for each label
				//condProbs include conditional probability for each label
				HashMap<String, Double>condProbs = dataSet.calcCondProb(instMap);
				double allProbs = 0;
				Iterator<Double>probsIterator = condProbs.values().iterator();
				while(probsIterator.hasNext()) allProbs += probsIterator.next();
				Iterator<String>keyIterator = condProbs.keySet().iterator();
				while(keyIterator.hasNext())
				{
					String next = keyIterator.next();
					System.out.println("P("+next+"|"+DataSet.getInstanceStr(dataSet, instMap)+") = "+
					String.format("%.5f",condProbs.get(next))+"/"+String.format("%.5f", allProbs)+
					" = "+String.format("%.5f", condProbs.get(next)/allProbs));
					
				}
				
				System.out.println();
				System.out.println("Prediction:");

				//print out prediction with the label with highest conditional probability
				Iterator<String>predictIterator = condProbs.keySet().iterator();
				double max = -1;
				String maxKey = "";

				while(predictIterator.hasNext())
				{
					String key = predictIterator.next();
					if(max < condProbs.get(key)){
						max = condProbs.get(key);
						maxKey = key;
					}
				}
				System.out.println(maxKey);

				break;
			case "change dataset":
				System.out.println("> Choose dataset ("+Data.datas.keySet()+" ?");
				String value = bufferedReader.readLine();
				if(Data.datas.keySet().contains(value))
				{
					dataKey = value;
					dataSet = new DataSet(Data.datas.get(dataKey));
					System.out.println(dataSet);
					
				}
				else
					System.out.println("Please enter valid dataset name");
				break;
			case "exit":
				flag=false;
				break;
			}
		}
		System.exit(0);

	}

}
