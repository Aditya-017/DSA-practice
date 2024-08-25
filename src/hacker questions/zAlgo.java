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
     * Complete the 'commonPrefix' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY inputs as parameter.
     */

    public static List<Integer> commonPrefix(List<String> inputs) {
    List<Integer> ans =  new ArrayList<>();
        for(int i=0;i<inputs.size();++i){
            ans.add(solve(inputs.get(i)));
        }
        return ans;
    }
    public static int solve(String st){
        int ans =0;
        int[] z =  new int[st.length()];
        z[0] = st.length();
        int l=0, r=0;
        for(int i=1;i<st.length();++i){
            if(i<=r){
                z[i] = Math.min(r-i+1,z[i-l]);
            }
            while(i+z[i]<st.length() && st.charAt(z[i]) == st.charAt(i+z[i])){
                
                ++z[i];
                // System.out.println("inside z[i]="+z[i]);
            }
            if(i+z[i]-1>r){
                l=i;
                r= i+z[i]-1;
            }
            
        ans+= z[i];
        }
        return ans+z[0];
    }

}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int inputsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> inputs = IntStream.range(0, inputsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<Integer> result = Result.commonPrefix(inputs);

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
