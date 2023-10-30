import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-10-31
@see            https://www.acmicpc.net/problem/1004
@performance
@category       #구현
@note
*/

public class Main {

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Planet extends Point{
        int r;

        public Planet(int x, int y, int r) {
            super(x, y);
            this.r = r;
        }

        public boolean isIn(Point p){
            double dist = Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
            return dist <= r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);

            int n = Integer.parseInt(br.readLine());

            int cnt = 0;
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                Planet p = new Planet(x, y, r);

                if((p.isIn(start) && !p.isIn(end)) || (!p.isIn(start) && p.isIn(end))){
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}