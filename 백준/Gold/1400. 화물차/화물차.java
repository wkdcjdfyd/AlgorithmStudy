import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-09-30
 * @see  			https://www.acmicpc.net/problem/1400
 * @performance
 * @category 		#구현
 * @note
 */

public class Main {
    static int M, N;
    static char[][] graph;
    static ArrayList<Cross> c;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Cross{
        int eastTime, northTime;
        boolean isEastFirst;

        public Cross(int eastTime, int northTime, boolean isEastFirst) {
            this.eastTime = eastTime;
            this.northTime = northTime;
            this.isEastFirst = isEastFirst;
        }

        public boolean canGo(int dir, int time){
            int left = time % (eastTime + northTime);
            if(left == 0) left = eastTime + northTime;

            if(isEastFirst){
                if(1 <= left && left <= eastTime){
                    return dir == 1 || dir == 3;
                }
                else{
                    return dir == 0 || dir == 2;
                }
            }
            else{
                if(1 <= left && left <= northTime){
                    return dir == 0 || dir == 2;
                }
                else{
                    return dir == 1 || dir == 3;
                }
            }
        }
    }

    public static int bfs(int x, int y){
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[M][N];
        visited[x][y] = true;
        q.offer(new int[] {x, y});

        int time = 0;
        while(!q.isEmpty()){
            time++;
            int size = q.size();

            while(--size >= 0){
                int[] now = q.poll();

                for(int d = 0; d < dx.length; d++){
                    int nx = now[0] + dx[d];
                    int ny = now[1] + dy[d];

                    if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                    if(graph[nx][ny] == '.' || visited[nx][ny]) continue;
                    if(graph[nx][ny] == 'B') return time;
                    if(graph[nx][ny] == '#'){
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                    }
                    if('0' <= graph[nx][ny] && graph[nx][ny] <= '9'){
                        int idx = graph[nx][ny] - '0';
                        if(c.get(idx).canGo(d, time)){
                            visited[nx][ny] = true;
                            q.offer(new int[] {nx, ny});
                        }
                        else{
                            q.offer(now);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if(M == 0 && N == 0) break;
            graph = new char[M][N];
            c = new ArrayList<>();
            int x = -1;
            int y = -1;
            int crossCnt = -1;

            for(int i = 0; i < M; i++){
                String s = br.readLine();
                for(int j = 0; j < N; j++){
                    graph[i][j] = s.charAt(j);
                    if(graph[i][j] == 'A'){
                        x = i;
                        y = j;
                    }
                    if('0' <= graph[i][j] && graph[i][j] <= '9'){
                        crossCnt = Math.max(graph[i][j] - '0', crossCnt);
                    }
                }
            }
            if(crossCnt != -1){
                for(int i = 0; i <= crossCnt; i++){
                    st = new StringTokenizer(br.readLine());
                    st.nextToken();
                    char isEastFirst = st.nextToken().charAt(0);
                    int eastTime = Integer.parseInt(st.nextToken());
                    int northTime = Integer.parseInt(st.nextToken());
                    c.add(new Cross(eastTime, northTime, '-' == isEastFirst));
                }
            }

            if(x == -1 && y == -1){
                sb.append("impossible\n");
                br.readLine();
                continue;
            }

            int result = bfs(x, y);
            if(result == -1){
                sb.append("impossible\n");
            }
            else{
                sb.append(result).append("\n");
            }

            br.readLine();
        }

        System.out.print(sb);
        br.close();
    }
}