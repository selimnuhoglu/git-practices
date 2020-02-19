import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader("/Users/selim/Desktop/b_small.in");
        List<List<Integer>> lines = inputReader.getLines();

        Integer requiredNumberOfSlices = lines.get(0).get(0);
        Integer typesOfPizzas = lines.get(0).get(1);
        List<Integer> listOfSlices = lines.get(1);

        List<List<Integer>> combinations = new ArrayList<>();
        for ( int i = typesOfPizzas; i > 0; i--) {
            combination(listOfSlices, i, new ArrayList<Integer>(), combinations);
        }

        Map<Integer, List<Integer>> resultMap = calculateSums(combinations);
        resultMap.forEach((key, value) -> {
            System.out.print(key + " ");
            value.forEach(element -> System.out.print(element + " "));
            System.out.print("\n");
        });

        List<Integer> filteredResults =
                resultMap.keySet().stream()
                        .filter(key -> requiredNumberOfSlices.compareTo(key) > 0)
                        .collect(Collectors.toList());
        filteredResults.forEach(x -> System.out.println(x));
    }

    private static Map<Integer, List<Integer>> calculateSums(List<List<Integer>> combinations) {

        TreeMap<Integer, List<Integer>> resultMap = new TreeMap<Integer, List<Integer>>();
        for (List<Integer> combination: combinations) {
            int totalSliceOfCombination = 0;
            for (Integer number: combination) {
                totalSliceOfCombination += number.intValue();
            }
            resultMap.put(totalSliceOfCombination, combination);
        }
        return resultMap;
    }

    public static void combination(List<Integer> e, int k, List<Integer> accumulated, List<List<Integer>> outputList){

        // 1. stop
        if(e.size() < k)
            return;

        // 2. add each element in e to accumulated
        if(k == 1)
            for(Integer i:e) {
                ArrayList<Integer> subList = new ArrayList<Integer>();
                subList.addAll(accumulated);
                subList.add(i);
                outputList.add(subList);
            }

            // 3. add all elements in e to accumulated
        else if(e.size() == k){
            ArrayList<Integer> subList = new ArrayList<Integer>();
            subList.addAll(accumulated);
            subList.addAll(e);
            outputList.add(subList);
        }

        // 4. for each element, call combination
        else if(e.size() > k)
            for(int i = 0 ; i < e.size() ; i++) {
                accumulated.add(e.get(i));
                combination(e.subList(i+1, e.size()), k-1, accumulated, outputList);
                accumulated.remove(e.get(i));
            }

    }
}