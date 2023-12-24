import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-12-24
@see            https://www.acmicpc.net/problem/2458
@performance    
@category       #BFS
@note          
*/

public class Main {

    static int N;
    static int M;
    static ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> reverseEdge = new ArrayList<>();

    public static boolean check(int num){
        boolean[] visited = new boolean[N+1];
        boolean[] visited2 = new boolean[N+1];
        Deque<Integer> q = new ArrayDeque<>();
        visited[num] = true;
        visited2[num] = true;
        q.offer(num);

        while(!q.isEmpty()){
            int now = q.poll();

            for(int nxt: edge.get(now)){
                if(!visited[nxt]){
                    visited[nxt] = true;
                    q.offer(nxt);
                }
            }
        }

        q.offer(num);
        while(!q.isEmpty()){
            int now = q.poll();

            for(int nxt: reverseEdge.get(now)){
                if(!visited2[nxt]){
                    visited2[nxt] = true;
                    q.offer(nxt);
                }
            }
        }

        for(int i = 1; i < N+1; i++){
            if(!visited[i] && !visited2[i]){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N+1; i++){
            edge.add(new ArrayList<>());
            reverseEdge.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edge.get(from).add(to);
            reverseEdge.get(to).add(from);
        }

        int ans = 0;
        for(int i = 1; i < N+1; i++){
            if(check(i)){
                ans++;
            }
        }

        System.out.println(ans);
        br.close();
    }
}