import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-12-28
@see            https://www.acmicpc.net/problem/1922
@performance
@category       #MST
@note
*/

public class Main {

    static int N, M;
    static int[] parents;

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static int find(int x){
        if(parents[x] == x){
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y){
        int px = find(x);
        int py = find(y);

        if(px == py) return false;
        parents[px] = py;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N+1];
        Edge[] edges = new Edge[M];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }
        Arrays.sort(edges);

        for(int i = 1; i < N+1; i++){
            parents[i] = i;
        }

        int ans = 0;
        int cnt = 0;
        for(Edge e: edges){
            if(union(e.from, e.to)){
                cnt++;
                ans += e.cost;
            }
            if(cnt == N) break;
        }

        System.out.println(ans);
        br.close();
    }
}