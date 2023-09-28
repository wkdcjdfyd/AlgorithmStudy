import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-09-28
 * @see  			https://www.acmicpc.net/problem/17244
 * @performance 	
 * @category 		#BFS
 * @note 			
 */

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int WALL = -1;
    static final int END = -2;
    static int N, M;
    static int[][] graph;
    static boolean[][][] visited;
    static int objCnt = 0;

    static class Node {
        int x, y, obj, move;

        public Node(int x, int y, int obj, int move){
            this.x = x;
            this.y = y;
            this.obj = obj;
            this.move = move;
        }
    }

    public static int bfs(int x, int y){
        Deque<Node> q = new ArrayDeque<>();
        visited[0][x][y] = true;
        q.offer(new Node(x, y, 0, 0));

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int d = 0; d < dx.length; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(graph[nx][ny] == WALL) continue;
                if(visited[now.obj][nx][ny]) continue;

                if(graph[nx][ny] == END && now.obj == (1<<objCnt)-1){
                    return now.move+1;
                }

                if(graph[nx][ny] > 0){
                    int nxtObj = (now.obj | (1<<(graph[nx][ny]-1)));
                    if(!visited[nxtObj][nx][ny]){
                        visited[nxtObj][nx][ny] = true;
                        q.offer(new Node(nx, ny, nxtObj, now.move+1));
                    }
                }
                else if(graph[nx][ny] == 0) {
                    visited[now.obj][nx][ny] = true;
                    q.offer(new Node(nx, ny, now.obj, now.move+1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[M][N];
        int x = -1;
        int y = -1;

        for(int i = 0; i < M; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                char c = s.charAt(j);

                if(c == '#') graph[i][j] = WALL;
                else if(c == 'X') graph[i][j] = ++objCnt;
                else if(c == '.') graph[i][j] = 0;
                else if(c == 'E') graph[i][j] = END;
                else if(c == 'S'){
                    graph[i][j] = 0;
                    x = i;
                    y = j;
                }
            }
        }
        visited = new boolean[1<<objCnt][M][N];

        int ans = bfs(x, y);
        System.out.println(ans);
        br.close();
    }
}