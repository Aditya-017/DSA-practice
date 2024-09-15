import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getMaxArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> getMaxArray(List<Integer> arr) {
        Map<Integer,Queue<Integer>> map=  new HashMap<>();
        
        for(int i=0;i<arr.size();++i){
            add(map,i,arr.get(i));
        }
        // System.out.println(map);
        // remove(map,3);
        // System.out.println(map);
        // remove(map,2);
        // System.out.println(map);
        List<Integer> ans = new ArrayList<>();
        int windowStart=0;
        while(windowStart<arr.size()){
            int ele = smallestMissingInt(map);
            ans.add(ele);
            int index = findIndex(windowStart,arr,ele);
            while(windowStart<=index){
                remove(map,arr.get(windowStart));
                ++windowStart;    
            }
            
        }
        
        

        return ans;
    }
    
    private static void add(Map<Integer,Queue<Integer>> map, int index, int no){
        Queue q = map.getOrDefault(no,new ArrayDeque());
        q.offer(index);
        map.put(no,q);
    }
    
    private static void remove(Map<Integer,Queue<Integer>> map, int no){
        Queue q = map.get(no);
        q.poll();
        if(q.isEmpty()) map.remove(no);
    }
    private static int smallestMissingInt(Map<Integer,Queue<Integer>> map){
        int i=0, max =(int)1e5+7;
        while(i<=max){
            if(!map.containsKey(i)) return i;
            ++i;
        }
        return max;
    }
    
    private static int findIndex(int start, List<Integer> arr,  int ele){
        Set<Integer> set = new HashSet<>();
        int curAns=0;
        for(int i=start;i<arr.size();++i){
            set.add(arr.get(i));
            while(set.contains(curAns)){
                ++curAns;
            }
            if(curAns==ele) return i;
        }
        return (int)1e9+7;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.getMaxArray(arr);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
