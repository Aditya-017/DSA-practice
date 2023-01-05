package Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Pair{
    int node;
    int distance;
    public Pair(int distance,int node){
        this.node = node;
        this.distance = distance;
    }
}

class Solution
{
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a,b)->a.distance-b.distance);

        int[] dist = new int[V];

        // Initialising distTo list with a large number to
        // indicate the nodes are unvisited initially.
        // This list contains distance from source to the nodes.
        for (int i = 0; i < V; i++) dist[i] = (int) (1e9);

        // Source initialised with dist=0.
        // dist[S] = 0;
        pq.add(new Pair(0, S));

        // Now, pop the minimum distance node first from the min-heap
        // and traverse for all its adjacent nodes.
        while (pq.size() != 0) {
            int dis = pq.peek().distance;
            int node = pq.peek().node;
            pq.remove();
            if(dist[node]<1e9)continue;
            dist[node]=dis;

            // Check for all adjacent nodes of the popped out
            // element whether the prev dist is larger than current or not.
            for (int i = 0; i < adj.get(node).size(); i++) {
                int edgeWeight = adj.get(node).get(i).get(1);
                int adjNode = adj.get(node).get(i).get(0);

                // If current distance is smaller,
                // push it into the queue.


                pq.add(new Pair(dis+edgeWeight, adjNode));

            }
        }
        // Return the list containing shortest distances
        // from source to all the nodes.
        return dist;
    }
}

