import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
@author         Ryong
@since          2024-01-12
@see            https://www.acmicpc.net/problem/2665
@performance
@category       #BFS
@note
*/

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n;
    static boolean[][] graph;
    static int[][] dist;

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(){
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0));
        dist[0][0] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int d = 0; d < dx.length; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                int nxtDist = graph[nx][ny]?dist[now.x][now.y]:dist[now.x][now.y]+1;

                if(nxtDist < dist[nx][ny]){
                    dist[nx][ny] = nxtDist;
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new boolean[n][n];
        dist = new int[n][n];

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < n; j++){
                if(s.charAt(j) == '0'){
                    graph[i][j] = false;
                }
                else {
                    graph[i][j] = true;
                }
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();
        System.out.println(dist[n-1][n-1]);
        br.close();
    }
}