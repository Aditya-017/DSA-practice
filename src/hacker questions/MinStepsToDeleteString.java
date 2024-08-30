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
     * Complete the 'getMinOperations' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING series as parameter.
     */

    public static int getMinOperations(String s) {
        int n =s.length();
        int[][] dp = new int[n][n];
        for(int i=0;i<n;++i) Arrays.fill(dp[i],-1);
        return solve(0,s.length()-1,s,dp);

    }
    
    private static int solve(int i, int j, String s, int[][] dp){
        if(j<i) return 0;
        if(i==j) return 1;
        if(dp[i][j]!=-1) return dp[i][j];
        int res = 1 +solve(i+1,j,s,dp);
        for(int it = i+1; it<=j; ++it){
            if(s.charAt(i)==s.charAt(it)){
                res = Math.min(res, solve(i+1,it-1,s,dp)+solve(it,j,s,dp)); 
            }
        }
        return dp[i][j]=res;
    }


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String series = bufferedReader.readLine();

        int result = Result.getMinOperations(series);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
