import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-12-15
@see            https://www.acmicpc.net/problem/14284
@performance
@category       #다익스트라
@note
*/

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    static class Edge implements Comparable<Edge>{
        int to;
        int weight;

        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static int dijkstra(int start, int end){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] visited = new int[N+1];
        Arrays.fill(visited, (int) 1e9);

        visited[start] = 0;
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge now = pq.poll();

            if(visited[now.to] < now.weight) continue;

            for(Edge nxt : graph.get(now.to)){
                int cost = now.weight + nxt.weight;
                if(cost < visited[nxt.to]){
                    visited[nxt.to] = cost;
                    pq.add(new Edge(nxt.to, cost));
                }
            }
        }

        return visited[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.print(dijkstra(start, end));
        br.close();
    }
}