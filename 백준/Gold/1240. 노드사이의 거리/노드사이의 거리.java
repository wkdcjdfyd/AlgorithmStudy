import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
@author         Ryong
@since          2023-11-2
@see            https://www.acmicpc.net/problem/1240
@performance
@category       #BFS
@note
*/

public class Main {

    static int N, M;
    static ArrayList<ArrayList<int[]>> graph;

    public static int getDist(int start, int end){
        Deque<int[]> q = new ArrayDeque<>();
        int[] visited = new int[N+1];
        Arrays.fill(visited, -1);
        visited[start] = 0;
        q.offer(new int[] {start, 0});

        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int[] nxt: graph.get(now[0])){
                if(visited[nxt[0]] != -1) continue;

                int dist = now[1] + nxt[1];
                if(end == nxt[0]){
                    return dist;
                }

                visited[nxt[0]] = dist;
                q.offer(new int[]{nxt[0], dist});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i = 0; i < N+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph.get(s).add(new int[]{e, dist});
            graph.get(e).add(new int[]{s, dist});
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(getDist(s, e)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}