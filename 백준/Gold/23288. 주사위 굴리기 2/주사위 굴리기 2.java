import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-02-23
@see            https://www.acmicpc.net/problem/23288
@performance
@category       #구현
@note
*/

public class Main {

    static final int EAST = 0;
    static final int SOUTH = 1;
    static final int WEST = 2;
    static final int NORTH = 3;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M, K;
    static int[][] graph;

    static class Dice{
        int x, y;
        int up, down, east, west, south, north, d;

        public Dice() {
            this.x = 0;
            this.y = 0;
            this.up = 1;
            this.down = 6;
            this.east = 3;
            this.west = 4;
            this.south = 5;
            this.north = 2;
            this.d = EAST;
        }

        private void clockwise(){
            this.d = (this.d + 1) % 4;
        }

        private void counterClockwise(){
            this.d = (this.d + 3) % 4;
        }

        public void rotate(int val){
            if(this.down > val) clockwise();
            else if(this.down < val) counterClockwise();
        }

        public void move(){
            int nx = this.x + dx[this.d];
            int ny = this.y + dy[this.d];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                this.d = (this.d + 2) % 4;
                nx = this.x + dx[this.d];
                ny = this.y + dy[this.d];
            }

            this.x = nx;
            this.y = ny;

            switch (this.d){
                case EAST:
                    moveEast();
                    break;
                case SOUTH:
                    moveSouth();
                    break;
                case WEST:
                    moveWest();
                    break;
                case NORTH:
                    moveNorth();
                    break;
            }
        }

        private void moveEast(){
            int temp = this.east;
            this.east = this.up;
            this.up = this.west;
            this.west = this.down;
            this.down = temp;
        }

        private void moveWest(){
            int temp = this.west;
            this.west = this.up;
            this.up = this.east;
            this.east = this.down;
            this.down = temp;
        }

        private void moveSouth(){
            int temp = this.south;
            this.south = this.up;
            this.up = this.north;
            this.north = this.down;
            this.down = temp;
        }

        private void moveNorth(){
            int temp = this.north;
            this.north = this.up;
            this.up = this.south;
            this.south = this.down;
            this.down = temp;
        }
    }

    public static int getScore(int x, int y){
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        q.offer(new int[]{x, y});
        int cnt = 1;

        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int d = 0; d < dx.length; d++){
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[nx][ny] || graph[nx][ny] != graph[x][y]) continue;
                cnt++;
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }

        return cnt * graph[x][y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        Dice dice = new Dice();
        int score = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0; k < K; k++){
            dice.move();
            dice.rotate(graph[dice.x][dice.y]);
            score += getScore(dice.x, dice.y);
        }

        System.out.println(score);
        br.close();
    }
}