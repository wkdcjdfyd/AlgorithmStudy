import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-11-2
@see            https://www.acmicpc.net/problem/1002
@performance
@category       #수학
@note
*/

public class Main {

    public static double dist(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            if(x1 == x2 && y1 == y2 && r1 == r2){
                sb.append(-1).append("\n");
                continue;
            }

            double target = dist(x1, y1, x2, y2);
            if(target == r1 + r2 || target == Math.abs(r1-r2)){
                sb.append(1).append("\n");
            }
            else if(target > r1 + r2 || target < Math.abs(r1-r2)){
                sb.append(0).append("\n");
            }
            else {
                sb.append(2).append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}