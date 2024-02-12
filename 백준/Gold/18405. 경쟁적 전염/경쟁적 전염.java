import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
@author         Ryong
@since          2024-02-13
@see            https://www.acmicpc.net/problem/18405
@performance
@category       #구현
@note
*/

public class Main {

    static int N, K;
    static int[][] graph;
    static Deque<Virus> q;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Virus implements Comparable<Virus> {
        int x;
        int y;
        int num;

        public Virus(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Virus o) {
            return Integer.compare(this.num, o.num);
        }
    }

    public static void spread() {
        int n = q.size();

        for (int i = 0; i < n; i++) {
            Virus now = q.poll();

            for (int d = 0; d < dx.length; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (graph[nx][ny] != 0) continue;
                graph[nx][ny] = now.num;
                q.offer(new Virus(nx, ny, graph[nx][ny]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        q = new ArrayDeque<>();
        List<Virus> viruses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] != 0) {
                    viruses.add(new Virus(i, j, graph[i][j]));
                }
            }
        }
        Collections.sort(viruses);
        q.addAll(viruses);

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        for (int i = 0; i < s; i++) {
            spread();
        }

        System.out.println(graph[x - 1][y - 1]);
        br.close();
    }
}