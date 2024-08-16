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
     * Complete the 'checkDivisibility' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY arr as parameter.
     */

    public static List<String> checkDivisibility(List<String> arr) {
    List<String> ans = new ArrayList<>();
        for(String num:arr){
            int len = num.length();
            int[] map = new int[10];
            for(int i=0;i<len;++i){
                int n =num.charAt(i)-'0';
                ++map[n];
            }
            ans.add(check(0,map,Math.min(len,3)));
        }
        return ans;
    }
    public static String check(int num,int[] map, int len){
        // System.out.println(len);
                // System.out.println("num="+num);
                // System.out.println(Arrays.toString(map));


        if(len==0){
            return num%8==0?"YES":"NO";
        }
        for(int i=0;i<=9;++i){
            
            int temp = num;
            
            if(map[i]>0){
                int n = map[i];
                map[i] = n-1;
                num = (num*10) + i;
                String ch = check(num,map,len-1);
                if(ch=="YES") return ch;
                map[i] = n;
            }
            num = temp;
        }
        return "NO";
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> arr = IntStream.range(0, arrCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.checkDivisibility(arr);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
