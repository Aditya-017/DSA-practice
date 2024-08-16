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
     * Complete the 'maxShared' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts WEIGHTED_INTEGER_GRAPH friends as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */

    public static int maxShared(int friendsNodes, List<Integer> friendsFrom, List<Integer> friendsTo, List<Integer> friendsWeight) {
        ArrayList<int[]>[] graph = new ArrayList[friendsNodes];
        Set<Integer> set = new HashSet<>();
        for(int i =0;i<friendsNodes;++i){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<friendsFrom.size(); ++i){
            int v1 = friendsFrom.get(i)-1;
            int v2 = friendsTo.get(i)-1;
            int wt = friendsWeight.get(i);
            set.add(wt);
            graph[v1].add(new int[]{v2,wt});
            graph[v2].add(new int[]{v1,wt});

        }
        int[][] grid = new int[friendsNodes][friendsNodes];
        // for(int i =0;i<friendsNodes;++i){
        //      List<int[]>arr = graph[i];
        //      System.out.println();
        //      for(int j=0;j<arr.size();++j){
        //          System.out.print(Arrays.toString(arr.get(j)));
        //      }
        // }
        for(int wt: set){
            for(int st=0;st<friendsNodes;++st){
                Set<Integer> reach = bfs(st,wt,graph);
                for(int r: reach){
                    ++grid[st][r];
                }
            }
        }
        // System.out.println(Arrays.deepToString(grid));
        int maxReach=0;
        for(int i=0;i<friendsNodes;++i){
            for(int j=0;j<friendsNodes;++j){
                maxReach = Math.max(maxReach,grid[i][j]);
            }
        }
        int ans=0;
        for(int i=0;i<friendsNodes;++i){
            for(int j=0;j<friendsNodes;++j){
                if(grid[i][j]==maxReach){
                    ans = Math.max(ans,(i+1)*(j+1));
                }
            }
        }
        return ans;

    }
    
    private static Set<Integer> bfs(int st, int wt, ArrayList<int[]>[] graph ){
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        boolean[] vis = new boolean[graph.length];
        q.add(st);
        while(!q.isEmpty()){
            int cur = q.poll();
            if(vis[cur]) continue;
            vis[cur] = true;
            if(st!=cur)
                set.add(cur);
            for(int[] ele:graph[cur]){
                if(ele[1]==wt){
                    q.offer(ele[0]);
                }
            }
            
        }
        return set;
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] friendsNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int friendsNodes = Integer.parseInt(friendsNodesEdges[0]);
        int friendsEdges = Integer.parseInt(friendsNodesEdges[1]);

        List<Integer> friendsFrom = new ArrayList<>();
        List<Integer> friendsTo = new ArrayList<>();
        List<Integer> friendsWeight = new ArrayList<>();

        IntStream.range(0, friendsEdges).forEach(i -> {
            try {
                String[] friendsFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                friendsFrom.add(Integer.parseInt(friendsFromToWeight[0]));
                friendsTo.add(Integer.parseInt(friendsFromToWeight[1]));
                friendsWeight.add(Integer.parseInt(friendsFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.maxShared(friendsNodes, friendsFrom, friendsTo, friendsWeight);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

