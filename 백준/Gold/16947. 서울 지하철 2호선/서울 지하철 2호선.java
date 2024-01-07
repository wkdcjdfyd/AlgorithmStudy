import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
@author         Ryong
@since          2024-01-07
@see            https://www.acmicpc.net/problem/16947
@performance
@category       #그래프탐색
@note
*/

public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] isCycle;

    public static boolean check(int prev, int now, int start){
        isCycle[now] = true;

        for(int nxt : graph.get(now)){
            if(!isCycle[nxt]){
                if(check(now, nxt, start)) return true;
            }
            if(nxt != prev && nxt == start) return true;
        }
        isCycle[now] = false;
        return false;
    }

    public static int bfs(int start){
        if(isCycle[start]) return 0;

        Deque<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        q.offer(new int[] {start, 0});

        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int nxt : graph.get(now[0])){
                if(visited[nxt]) continue;
                if(isCycle[nxt]) return now[1] + 1;
                
                visited[nxt] = true;
                q.offer(new int[]{nxt, now[1] + 1});
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        isCycle = new boolean[N+1];
        for(int i = 1; i < N+1; i++){
            if(check(i, i, i)) break;
            Arrays.fill(isCycle, false);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N+1; i++){
            sb.append(bfs(i)).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}