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
     * Complete the 'getMinMoves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int getMinMoves(String s) {
        int[][][] dp = new int[2][s.length()][26];
        for(int i=0;i<s.length();++i) Arrays.fill(dp[0][i],-1);
        for(int i=0;i<s.length();++i) Arrays.fill(dp[1][i],-1);
        return solve(0,'a',1,s,dp);     
       
    }
    
    private static int solve(int i, int pre,int isChoice, String s, int[][][] dp){
        if(i==s.length()-1) return Math.abs(s.charAt(i)-pre);
         if(dp[isChoice][i][pre-'a']!=-1) return dp[isChoice][i][pre-'a'];
        int choice = 1000000000;
        int noChoice = 1000000000;
        if(isChoice==1){
           for(int ch='a';ch<='z';++ch){
               int cost = Math.abs(s.charAt(i)-ch);
               choice = Math.min(choice, cost+solve(i+1,ch,0,s,dp));
           }
        }
        if(i!=0)
        noChoice = Math.abs(s.charAt(i)-pre)+solve(i+1,pre,1,s,dp);

        return dp[isChoice][i][pre-'a'] = Math.min(choice,noChoice);
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int result = Result.getMinMoves(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
