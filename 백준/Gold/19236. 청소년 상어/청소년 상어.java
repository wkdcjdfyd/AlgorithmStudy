import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
@author         Ryong
@since          2023-10-13
@see
@performance    
@category       #구현
@note
*/

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int ans;

    static class Fish implements Comparator<Fish> {
        int x, y, num, dir;

        public Fish(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }

        public Fish(Fish f){
            this.x = f.x;
            this.y = f.y;
            this.num = f.num;
            this.dir = f.dir;
        }

        public void rotateAndMove(Map<Integer, Fish> fishMap, int[][] graph){
            int cnt = 0;
            while(cnt < 8){
                int nx = this.x + dx[dir];
                int ny = this.y + dy[dir];

                if(0 <= nx && nx < 4 && 0 <= ny && ny < 4){
                    if(graph[nx][ny] > 0){
                        swapLocation(graph, this, fishMap.get(graph[nx][ny]));
                        return;
                    }
                    else if(graph[nx][ny] == 0) {
                        graph[this.x][this.y] = 0;
                        graph[nx][ny] = this.num;
                        this.x = nx;
                        this.y = ny;
                        return;
                    }
                }
                dir = (dir + 1) % 8;
                cnt++;
            }
        }

        @Override
        public int compare(Fish o1, Fish o2) {
            return Integer.compare(o1.num, o2.num);
        }
    }

    static class Shark extends Fish{
        int sum;

        public Shark(int x, int y, int num, int dir) {
            super(x, y, num, dir);
            sum = 0;
        }

        public Shark(Shark s){
            super(s);
            this.sum = s.sum;
        }

        public void eat(Map<Integer, Fish> fishMap, int[][] graph, Fish f){
            this.sum += f.num;
            this.dir = f.dir;
            swapLocation(graph, this, f);
            graph[f.x][f.y] = 0;
            fishMap.remove(f.num);
        }
    }

    public static void swapLocation(int[][] graph, Fish f1, Fish f2) {
        int temp = graph[f1.x][f1.y];
        graph[f1.x][f1.y] = graph[f2.x][f2.y];
        graph[f2.x][f2.y] = temp;

        int tempX = f1.x;
        f1.x = f2.x;
        f2.x = tempX;

        int tempY = f1.y;
        f1.y = f2.y;
        f2.y = tempY;
    }

    public static void moveFishes(Map<Integer, Fish> fishMap, int[][] graph){
        for(Fish f: fishMap.values()){
            f.rotateAndMove(fishMap, graph);
        }
    }

    public static int[][] copyGraph(int[][] org){
        int[][] nxt = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                nxt[i][j] = org[i][j];
            }
        }
        return nxt;
    }

    public static void dfs(Shark shark, Map<Integer, Fish> fishMap, int[][] graph){
        // 물고기 이동
        moveFishes(fishMap, graph);

        // 상어 이동
        boolean isMove = false;
        for(int scale = 1; scale < 4; scale++){
            int nx = shark.x + dx[shark.dir] * scale;
            int ny = shark.y + dy[shark.dir] * scale;

            if(nx < 0 || ny < 0|| nx >= 4 || ny >= 4) break;
            if(graph[nx][ny] == 0) continue;

            isMove = true;
            Shark nxtShark = new Shark(shark);
            Map<Integer, Fish> nxtFishMap = new TreeMap<>();
            for(Fish f : fishMap.values()){
                nxtFishMap.put(f.num, new Fish(f));
            }
            int[][] nxtGraph = copyGraph(graph);

            nxtShark.eat(nxtFishMap, nxtGraph, nxtFishMap.get(graph[nx][ny]));
            dfs(nxtShark, nxtFishMap, nxtGraph);
        }

        if(!isMove){
            ans = Math.max(ans, shark.sum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] graph = new int[4][4];
        Map<Integer, Fish> map = new TreeMap<>();

        for(int i = 0; i < 4; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j ++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                graph[i][j] = num;
                map.put(num, new Fish(i, j, num, dir));
            }
        }

        // 초기 위치에 상어 넣어주기
        Fish fish = map.get(graph[0][0]);

        Shark shark = new Shark(0, 0, -1, fish.dir);
        shark.sum = fish.num;
        ans = shark.sum;
        map.remove(graph[0][0]);
        graph[0][0] = -1;

        // 최댓값 탐색
        dfs(shark, map, graph);

        System.out.println(ans);
        br.close();
    }
}