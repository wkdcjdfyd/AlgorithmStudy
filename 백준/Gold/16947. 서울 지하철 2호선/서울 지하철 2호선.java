import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] isCycle;

    public static void check(int now, List<Integer> visited){
        if(visited.contains(now)){
            boolean start = false;
            for(Integer n: visited){
                if(n == now) start = true;
                if(start) isCycle[n] = true;
            }
            return;
        }

        int prev = 0;
        if(!visited.isEmpty()){
            prev = visited.get(visited.size() - 1);
        }
        visited.add(now);

        for(Integer nxt : graph.get(now)){
            if(nxt == prev) continue;
            check(nxt, new ArrayList<>(visited));
        }
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

        return 10000;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isCycle = new boolean[N+1];

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

        check(1, new ArrayList<>());

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N+1; i++){
            sb.append(bfs(i)).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}