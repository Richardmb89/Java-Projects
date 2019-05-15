
// This class will perform the operations on the dataset like calculate prior probabilities etc.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class DataSet {
    private String[][] data = null;
    private Feature classFeature = null;
    private HashMap<String, Double> priorProbs = new HashMap<String, Double>();

    public DataSet(String[][] data) {
        this.data = data;
    }

    //calculate prior probability based on the last column (labels)
    private DataSet calcPriorProbs() {
        classFeature = new Feature(data, data[0].length - 1);
        classFeature.getFeatureValue().stream().forEach(featureValue ->
                priorProbs.put(featureValue.getName(), (double) featureValue.getOccurences() / (data.length - 1)));
        return this;
    }

    //Calculate the conditional probability for a single column
    public HashMap<String, Double> calcCondProb(HashMap<String, String> instance) {
        calcPriorProbs();
        HashMap<String, Double> condProbs = new HashMap<String, Double>();
        classFeature.getFeatureValue().forEach(featureValue -> {
            HashMap<String, String> logMap = new HashMap<String, String>();
            logMap.put(featureValue.getName(), featureValue.getOccurences() + "/" + (data.length - 1));
            DataSet newDataSet = createDataSet(classFeature.getFeatureValue(featureValue.getName()));
            double condProb = calcCondProb(newDataSet, featureValue.getName(), instance, logMap);
            condProbs.put(featureValue.getName(), condProb);
            System.out.println(getResultStr(newDataSet, instance, logMap, condProb, featureValue.getName()));
        });
        return condProbs;
    }

    //Stores conditional probability for all the feature
    private double calcCondProb(DataSet newDataSet, String classFeatureValue, HashMap<String, String> instanceMap, HashMap<String, String> logMap) {
        ArrayList<Feature> features = new ArrayList<Feature>();
        instanceMap.keySet().stream().forEach(featureName ->
                features.add(new Feature(newDataSet.data, getColNumb(featureName)).calcProb(instanceMap.get(featureName), logMap))
        );
        double condProb = priorProbs.get(classFeatureValue);
        for (int i = 0; i < features.size(); i++) condProb *= features.get(i).getProbability();
        return condProb;
    }

    private static String getResultStr(DataSet dataSet, HashMap<String, String> instanceMap, HashMap<String, String> logMap, double prob, String featureValue) {
        StringBuffer resultSB = new StringBuffer(dataSet + "\n");
        String instanceStr = getInstanceStr(dataSet, instanceMap);
        resultSB.append("P(" + featureValue + "|" + instanceStr + ") = (P(" + featureValue + ")");
        IntStream.range(0, dataSet.data[0].length - 1).forEach(i -> resultSB.append("*P(" + instanceMap.get(dataSet.data[0][i]) + "|" + featureValue + ")"));
        resultSB.append(") / " + "P(" + instanceStr + ")\n");
        resultSB.append("P(" + featureValue + "|" + instanceStr + ") = " + "((" + logMap.get(featureValue) + ")");
        IntStream.range(0, dataSet.data[0].length - 1).forEach(i -> resultSB.append("'(" + logMap.get(dataSet.data[0][i]) + ")"));
        resultSB.append(") /" + "P(" + instanceStr + ")\n");
        resultSB.append("P(" + featureValue + "|" + instanceStr + ") = " + String.format("%.5f", prob) + " / " + instanceStr + ")\n");
        return resultSB.toString();
    }

    //Filters out datapoints relate to single label eg : "hypertention disease"
    private DataSet createDataSet(FeatureValue classFeatureValue) {
        String[][] returnData = new String[classFeatureValue.getOccurences() + 1][data[0].length];
        returnData[0] = data[0];
        int counter = 1;
        for (int row = 1; row < data.length; row++)
            if (data[row][data[0].length - 1].equals(classFeatureValue.getName()))
                returnData[counter++] = data[row];
        return new DataSet(returnData);

    }

    //Get the column number by giving the name
    private int getColNumb(String colName) {
        int returnValue = -1;
        for (int column = 0; column < data[0].length; column++)
            if (data[0][column] == colName) {
                returnValue = column;
                break;
            }
        return returnValue;
    }

    static String getInstanceStr(DataSet dataSet, HashMap<String, String> instanceMap) {
        StringBuffer instanceSB = new StringBuffer("<");
        IntStream.range(0, dataSet.data[0].length - 2).forEach(i -> instanceSB.append(instanceMap.get(dataSet.data[0][i]) + ", "));
        return (instanceSB.append(instanceMap.get(dataSet.data[0][dataSet.data[0].length - 2]) + ">")).toString();
    }

    public String[][] getData() {
        return data;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        IntStream.range(0, data.length).forEach(row -> {
            IntStream.range(0, data[row].length).forEach(column -> {
                stringBuffer.append(data[row][column]);
                IntStream.range(0, 24 - data[row][column].length()).forEach(i -> stringBuffer.append(" "));
            });
            stringBuffer.append("\n");
            if (row == 0) {
                IntStream.range(0, 108).forEach(i -> stringBuffer.append("-"));
                stringBuffer.toString();
            }
        });
        return stringBuffer.toString();
    }
}
