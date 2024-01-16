import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
@author         Ryong
@since          2024-01-17
@see            https://www.acmicpc.net/problem/5972
@performance
@category       #Dijkstra
@note
*/

public class Main {

    static int N, M;
    static ArrayList<ArrayList<int[]>> graph;

    public static int dijkstra(){
        int[] d = new int[N+1];
        Arrays.fill(d, (int)1e9);
        d[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1[1], o2[1])));
        pq.offer(new int[]{1, 0});

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            if(d[now[0]] < now[1]) continue;

            for(int[] nxt : graph.get(now[0])){
                int cost = d[now[0]] + nxt[1];
                if(cost < d[nxt[0]]){
                    d[nxt[0]] = cost;
                    pq.offer(new int[]{nxt[0], cost});
                }
            }
        }
        return d[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for(int i = 0; i < N+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }

        System.out.println(dijkstra());
        br.close();
    }
}