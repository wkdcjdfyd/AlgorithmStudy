import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-02-14
@see            https://www.acmicpc.net/problem/2987
@performance
@category       #기하학
@note
*/

public class Main {

    static Point[] points = new Point[3];

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Line{
        Point p1;
        Point p2;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public static double getArea(Point p1, Point p2, Point p3){
        return 1.0 * Math.abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)) / 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        Line[] lines = new Line[3];
        lines[0] = new Line(points[0], points[1]);
        lines[1] = new Line(points[1], points[2]);
        lines[2] = new Line(points[2], points[0]);

        int ans = 0;
        int N = Integer.parseInt(br.readLine());
        double area = getArea(points[0], points[1], points[2]);

        loop:
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point p = new Point(x, y);

            double sum = 0;
            for (Line line : lines) {
                sum += getArea(line.p1, line.p2, p);
            }
            if(sum == area) ans++;
        }

        sb.append(String.format("%.1f", area)).append("\n");
        sb.append(ans);

        System.out.println(sb);
        br.close();
    }
}