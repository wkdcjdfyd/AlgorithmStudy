import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
@author         Ryong
@since          2024-02-05
@see            https://www.acmicpc.net/problem/31230
@performance    
@category       #Dijkstra
@note          
*/

public class Main {

    static int N, M, A, B;
    static ArrayList<ArrayList<int[]>> graph;
    static final long INF = Long.MAX_VALUE;

    public static class Node implements Comparable<Node> {
        int num;
        long cost;

        public Node(int num, long cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    private static long[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.num] < now.cost) continue;

            for (int[] nxt : graph.get(now.num)) {
                long cost = now.cost + nxt[1];
                if (cost < dist[nxt[0]]) {
                    dist[nxt[0]] = cost;
                    pq.offer(new Node(nxt[0], cost));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }

        long[] distA = dijkstra(A);
        long[] distB = dijkstra(B);
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (distA[i] + distB[i] == distA[B]) {
                ans.add(i);
            }
        }

        sb.append(ans.size()).append("\n");
        for (int num : ans) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}