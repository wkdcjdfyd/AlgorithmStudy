import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author         Ryong
@since          2024-02-12
@see            https://www.acmicpc.net/problem/9207
@performance
@category       #구현
@note
*/

public class Main {

    static final int H = 5;
    static final int W = 9;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] graph;
    static int pinNum;
    static int moveNum;

    public static void dfs(int x, int y, int move, int pin) {
        if(pin < pinNum){
            pinNum = pin;
            moveNum = move;
        }
        for(int d = 0; d < dx.length; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isValid(nx, ny) && graph[nx][ny] == 'o'){
                int nnx = nx + dx[d];
                int nny = ny + dy[d];
                if(isValid(nnx, nny) && graph[nnx][nny] == '.'){
                    graph[x][y] = '.';
                    graph[nx][ny] = '.';
                    graph[nnx][nny] = 'o';

                    for(int i = 0; i < H; i++){
                        for(int j = 0; j < W; j++){
                            if(graph[i][j] == 'o') {
                                dfs(i, j, move+1, pin-1);
                            }
                        }
                    }
                    graph[x][y] = 'o';
                    graph[nx][ny] = 'o';
                    graph[nnx][nny] = '.';
                }
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            graph = new char[H][W];
            int originalPinNum = 0;

            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for(int j = 0; j < W; j++){
                    graph[i][j] = s.charAt(j);
                    if(graph[i][j] == 'o') originalPinNum++;
                }
            }
            pinNum = originalPinNum;
            moveNum = 0;
            if(t != T-1) br.readLine();

            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(graph[i][j] == 'o'){
                        dfs(i, j, 0, originalPinNum);
                    }
                }
            }

            sb.append(pinNum).append(" ").append(moveNum).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}