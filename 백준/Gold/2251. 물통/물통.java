import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
@author         Ryong
@since          2024-01-10
@see            https://www.acmicpc.net/problem/2251
@performance
@category       #BFS
@note
*/

public class Main {

    static Set<Integer> set = new TreeSet<>();
    static int[] bottle = new int[3];

    public static void bfs(){
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[201][201];
        q.offer(new int[]{0, 0, bottle[2]});

        while(!q.isEmpty()){
            int[] now = q.poll();
            if(visited[now[0]][now[1]]) continue;

            visited[now[0]][now[1]] = true;
            if(now[0] == 0) set.add(now[2]);

            // A->B
            if(now[0] + now[1] > bottle[1]) {q.offer(new int[] {now[0]+now[1]-bottle[1], bottle[1], now[2]});}
            else {q.offer(new int[] {0, now[0]+now[1], now[2]});}

            // A->C
            if(now[0] + now[2] > bottle[2]) {q.offer(new int[] {now[0]+now[2]-bottle[2], now[1], bottle[2]});}
            else {q.offer(new int[] {0, now[1], now[0]+now[2]});}

            // B->A
            if(now[0] + now[1] > bottle[0]) {q.offer(new int[] {bottle[0], now[0]+now[1]-bottle[0], now[2]});}
            else {q.offer(new int[] {now[0]+now[1], 0, now[2]});}

            // B->C
            if(now[1] + now[2] > bottle[2]) {q.offer(new int[] {now[0], now[1]+now[2]-bottle[2], bottle[2]});}
            else {q.offer(new int[] {now[0], 0, now[1]+now[2]});}

            // C->A
            if(now[0] + now[2] > bottle[0]) {q.offer(new int[] {bottle[0], now[1], now[0]+now[2]-bottle[0]});}
            else {q.offer(new int[] {now[0]+now[2], now[1], 0});}

            // C->B
            if(now[1] + now[2] > bottle[1]) {q.offer(new int[] {now[0], bottle[1], now[1]+now[2]-bottle[1]});}
            else {q.offer(new int[] {now[0], now[1]+now[2], 0});}
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < bottle.length; i++){
            bottle[i] = Integer.parseInt(st.nextToken());
        }

        bfs();
        StringBuilder sb = new StringBuilder();
        for(int x: set){
            sb.append(x).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}