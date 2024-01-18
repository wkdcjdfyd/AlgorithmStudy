import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-19
@see            https://www.acmicpc.net/problem/18808
@performance
@category       #구현
@note
*/

public class Main {

    static int N, M, K;
    static boolean[][] graph;
    static int ans;

    public static boolean push(boolean[][] sticker){
        int r = sticker.length;
        int c = sticker[0].length;

        // 붙이는 자리 찾기
        for(int i = 0; i <= N-r; i++){
            for(int j = 0; j <= M-c; j++){
                // 붙일 수 있는지 확인
                boolean checkFlag = true;

                if(i+r <= N && j+c <= M) {
                    checkLoop:
                    for (int x = i; x < i + r; x++) {
                        for (int y = j; y < j + c; y++) {
                            if (!sticker[x-i][y-j]) continue;
                            if (graph[x][y]) {
                                checkFlag = false;
                                break checkLoop;
                            }
                        }
                    }

                    if (checkFlag) {
                        for (int x = i; x < i + r; x++) {
                            for (int y = j; y < j + c; y++) {
                                if (sticker[x-i][y-j]) {
                                    graph[x][y] = true;
                                    ans++;
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean[][] rotate(boolean[][] sticker){
        int r = sticker.length;
        int c = sticker[0].length;
        boolean[][] rotated = new boolean[c][r];

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                rotated[j][r-1-i] = sticker[i][j];
            }
        }

        return rotated;
    }

    public static void checkSticker(boolean[][] sticker){
        if(push(sticker)) return;
        for(int i = 0; i < 3; i++){
            sticker = rotate(sticker);
            if(push(sticker)) return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new boolean[N][M];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            boolean[][] sticker = new boolean[r][c];

            for(int x = 0; x < r; x++){
                st = new StringTokenizer(br.readLine());
                for(int y = 0; y < c; y++){
                    sticker[x][y] = Integer.parseInt(st.nextToken()) == 1;
                }
            }
            checkSticker(sticker);
        }

        System.out.println(ans);
        br.close();
    }
}