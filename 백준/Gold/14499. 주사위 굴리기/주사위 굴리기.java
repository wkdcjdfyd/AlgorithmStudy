import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-15
@see            https://www.acmicpc.net/problem/14499
@performance
@category       #구현
@note
*/

public class Main {

    static final int EAST = 1;
    static final int WEST = 2;
    static final int NORTH = 3;
    static final int SOUTH = 4;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static Dice dice = new Dice();

    static class Dice {
        int[] num;

        public Dice() {
            this.num = new int[6];
        }

        public void move(int d) {
            int top = 0;
            switch (d) {
                case EAST:
                    top = num[4];
                    num[4] = num[2];
                    num[2] = num[5];
                    num[5] = num[0];
                    num[0] = top;
                    break;
                case WEST:
                    top = num[5];
                    num[5] = num[2];
                    num[2] = num[4];
                    num[4] = num[0];
                    num[0] = top;
                    break;
                case SOUTH:
                    top = num[3];
                    num[3] = num[2];
                    num[2] = num[1];
                    num[1] = num[0];
                    num[0] = top;
                    break;
                case NORTH:
                    top = num[1];
                    num[1] = num[2];
                    num[2] = num[3];
                    num[3] = num[0];
                    num[0] = top;
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int d = Integer.parseInt(st.nextToken());
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            dice.move(d);
            if (graph[nx][ny] == 0) {
                graph[nx][ny] = dice.num[2];
            } else {
                dice.num[2] = graph[nx][ny];
                graph[nx][ny] = 0;
            }
            x = nx;
            y = ny;
            sb.append(dice.num[0]).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}