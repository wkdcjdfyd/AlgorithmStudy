import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-10-3
 * @see  			https://www.acmicpc.net/problem/16929
 * @performance 	
 * @category 		#구현
 * @note 			
 */

public class Main {
    static int N, M;
    static char[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean result;

    public static void dfs(int nth, int x, int y, int[] start){
        for(int d = 0; d < dx.length; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if(nth >= 4 && nx == start[0] && ny == start[1]){
                result = true;
                return;
            }

            if(visited[nx][ny] || graph[nx][ny] != graph[start[0]][start[1]]) continue;

            visited[nx][ny] = true;
            dfs(nth+1, nx, ny, start);
            if(result) return;
            visited[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                graph[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                visited[i][j] = true;
                dfs(1, i, j, new int[] {i, j});
                if(result) break;
                visited[i][j] = false;
            }
        }

        if(result) System.out.println("Yes");
        else System.out.println("No");
        br.close();
    }
}