import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
@author         Ryong
@since          2023-10-17
@see            https://www.acmicpc.net/problem/1007
@performance
@category       #수학
@note
*/

public class Main {
    static int N;
    static Point[] points;
    static double ans;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void makeComb(int nth, int startIdx, int[] choosed){
        if(nth == choosed.length){
            makeVectorSum(choosed);
            return;
        }
        for(int i = startIdx; i < N; i++){
            choosed[nth] = i;
            makeComb(nth+1, i+1, choosed);
        }
    }

    public static void makeVectorSum(int[] pointIdx){
        int x = 0;
        int y = 0;
        int idx = 0;

        for(int i = 0; i < N; i++){
            if(idx < N/2 && pointIdx[idx] == i){
                x += points[i].x;
                y += points[i].y;
                idx++;
            }
            else{
                x -= points[i].x;
                y -= points[i].y;
            }
        }
        double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        ans = Math.min(ans, dist);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());
            points = new Point[N];

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }

            ans = Double.MAX_VALUE;
            makeComb(0, 0, new int[N/2]);

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}