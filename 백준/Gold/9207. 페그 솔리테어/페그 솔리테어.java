import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
    static int pinNum;
    static int moveNum;

    public static void dfs(char[][] now, int pin, int move) {
        List<int[]> pos = new ArrayList<>();
        for (int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(now[i][j] == 'o') {
                    for(int d = 0; d < dx.length; d++){
                        if (check(i, j, d, now)){
                            pos.add(new int[]{i, j, d});
                        }
                    }
                }
            }
        }

        if(pin < pinNum){
            moveNum = move;
            pinNum = pin;
        }

        for(int[] n: pos){
            dfs(move(n[0], n[1], n[2], now), pin-1, move+1);
        }
    }

    public static char[][] move(int x, int y, int d, char[][] old){
        char[][] now = new char[H][W];
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                now[i][j] = old[i][j];
            }
        }
        now[x][y] = '.';

        int nx = x + dx[d];
        int ny = y + dy[d];
        now[nx][ny] = '.';

        int nnx = nx + dx[d];
        int nny = ny + dy[d];
        now[nnx][nny] = 'o';

        return now;
    }

    public static boolean check(int x, int y, int d, char[][] graph){
        int nx = x + dx[d];
        int ny = y + dy[d];

        if(!isValid(nx, ny)) return false;
        if(graph[nx][ny] != 'o') return false;

        int nnx = nx + dx[d];
        int nny = ny + dy[d];
        if(!isValid(nnx, nny)) return false;
        return graph[nnx][nny] == '.';
    }

    public static boolean isValid(int x, int y){
        return 0 <= x && x < H && 0 <= y && y < W;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            char[][] graph = new char[H][W];
            pinNum = 0;
            moveNum = 0;

            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for(int j = 0; j < W; j++){
                    graph[i][j] = s.charAt(j);
                    if(graph[i][j] == 'o') pinNum++;
                }
            }

            if(t != T-1) br.readLine();

            dfs(graph, pinNum, 0);

            sb.append(pinNum).append(" ").append(moveNum).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}