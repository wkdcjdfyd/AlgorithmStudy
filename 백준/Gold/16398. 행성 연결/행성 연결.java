import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-03
@see            https://www.acmicpc.net/problem/16398
@performance
@category       #MST
@note
*/

public class Main {

    static int N;
    static int[] parents;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y){
        int px = find(x);
        int py = find(y);

        if(px == py) return false;
        parents[px] = py;
        return true;
    }

    static class Edge implements Comparable<Edge>{
        int to;
        int from;
        int cost;

        public Edge(int to, int from, int cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];

        for(int i = 1; i < N+1; i++){
            parents[i] = i;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++){
                int c = Integer.parseInt(st.nextToken());
                if(i < j){
                    edges.add(new Edge(i, j, c));
                }
            }
        }

        Collections.sort(edges);

        int num = 0;
        long total = 0L;

        for(Edge e : edges){
            if(union(e.from, e.to)){
                total += e.cost;
                if(++num == N) break;
            }
        }

        System.out.println(total);
        br.close();
    }
}